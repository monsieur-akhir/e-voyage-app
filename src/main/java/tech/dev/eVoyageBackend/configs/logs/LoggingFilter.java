package tech.dev.eVoyageBackend.configs.logs;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import tech.dev.eVoyageBackend.business.LogsBusiness;
import tech.dev.eVoyageBackend.dao.entity.Logs;
import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.dao.repository.LogsRepository;
import tech.dev.eVoyageBackend.dao.repository.UserRepository;
import tech.dev.eVoyageBackend.utils.CustomEntityNotFoundException;
import tech.dev.eVoyageBackend.utils.ParamsUtils;
import tech.dev.eVoyageBackend.utils.Utilities;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LogsRepository logsRepository;

	@Autowired
	ParamsUtils paramsUtils;
	
	private static final Gson gson = new Gson();

	private Locale locale;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

	public static final String EFFECTUER = "SUCCES";
	public static final String ECHOUER = "FAILED";
	public static final String SUCCESS = "SUCCESS";

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		log.info("info -------------------------------------------------------------------------------------");
		log.info("host {}, adressIp {}, remoteUser {}", InetAddress.getLocalHost().getHostName(),
				InetAddress.getLocalHost().getHostAddress(), request.getRemoteUser());
		log.info("end -------------------------------------------------------------------------------------");

		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

		String requestID = UUID.randomUUID().toString();
		requestWrapper.setAttribute("requestID", requestID);

		filterChain.doFilter(requestWrapper, responseWrapper);

		String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

		String ipAddress = InetAddress.getLocalHost().getHostAddress();
		String hostName = InetAddress.getLocalHost().getHostName();

		requestBody = removePasswordFromRequestBody(requestBody);

		Logs logsDto = createLogsDto(request, requestBody, responseBody, ipAddress, hostName, request.getRequestURI());
		saveLog(logsDto);

		responseWrapper.copyBodyToResponse();
	}
	
	@SneakyThrows
	private Logs createLogsDto(HttpServletRequest request, String requestBody, String responseBody, String ipAddress,
	        String hostName, String requestURI) {
	    Gson gson = new Gson();
	    Map<String, Object> requestMap = null;
	    if (requestBody != null && !requestBody.equals("null")) {
	        try {
	            requestMap = gson.fromJson(requestBody, Map.class);
	        } catch (JsonSyntaxException e) {
	            log.error("Erreur lors de la désérialisation de requestBody: {}", e.getMessage());
	        }
	    }

	    String actionEffectue = determineAction(requestURI, requestBody);
	    User user = null;
	    String login = null;
	    Integer userId = null;
	    Map<String, Object> responseAttributes = null;

	    try {
	        responseAttributes = gson.fromJson(responseBody, Map.class);
	    } catch (Exception e) {
	        log.error("Erreur lors de la désérialisation de responseBody: {}", e.getMessage());
	    }

	    // Logique pour extraire les informations de l'utilisateur...

		// Traitement spécifique pour l'URI de validation OTP
		if (requestURI.equals("/user/validationOtp")) {
			List<Map<String, Object>> items = null;
			if (responseAttributes != null && responseAttributes.containsKey("items")) {
				items = (List<Map<String, Object>>) responseAttributes.get("items");
			}
			if (items != null && !items.isEmpty()) {
				Map<String, Object> userDetails = items.get(0); // Prendre le premier élément
				if (userDetails != null && userDetails.containsKey("id")) {
					int userIdI = ((Number) userDetails.get("id")).intValue();
					user = userRepository.findOne(userIdI, false);
					if (user == null) {
						throw new CustomEntityNotFoundException("401", "l'utilisateur n'existe pas", Boolean.TRUE);
					}
					userId = userIdI;
				}
			}
		}

		// Vérifier d'abord si l'ID de l'utilisateur est directement dans la requête
		if (user == null && requestMap != null && requestMap.containsKey("user")) {
			try {
				userId = ((Number) requestMap.get("user")).intValue();
				user = getUserById(userId);
			} catch (ClassCastException e) {
				log.error("Erreur lors de la récupération de l'ID utilisateur : {}", e.getMessage());
			}
		}

		// Si l'utilisateur n'est pas trouvé par ID, vérifier dans l'objet "data"
		if (user == null && requestMap != null && requestMap.containsKey("data")) {
			Map<String, Object> data = (Map<String, Object>) requestMap.get("data");
			if (data != null && data.containsKey("login")) {
				login = (String) data.get("login");
				user = getUserByLogin(login);
			}
		}


	    // Ajout de logs pour le débogage
	    log.debug("RequestMap: {}", requestMap);
	    log.debug("ResponseAttributes: {}", responseAttributes);
	    log.debug("User found: {}", user);

	    Logs logs = new Logs();
	    logs.setActionService(actionEffectue);
	    logs.setRequest(requestBody);
	    logs.setNom(user != null ? user.getFirstName() : "");
	    logs.setPrenom(user != null ? user.getLastName() : "");
	    // logs.setResponse(responseBody);
		if(requestURI.contains("export")){
			logs.setResponse("Fichier excel ou csv");
		}else{
			logs.setResponse(responseBody);
		}
	    logs.setLogin(login != null ? login : (user != null ? user.getLogin() : ""));
	    logs.setIsDeleted(false);
	    logs.setCreatedAt(Utilities.getCurrentDate());
	    logs.setCreatedBy(userId != null ? userId : (user != null ? user.getId() : null));
	    logs.setIsConnexion(requestURI.equalsIgnoreCase("/user/validationOtp"));
	    logs.setMachine(hostName);
	    logs.setIpadress(ipAddress);
	    logs.setUri(requestURI);
	    logs.setStatusConnection(determineStatusConnection(responseBody));

	    return logs;
	}
	

	private String determineAction(String requestURI, String requestBody) {
		if (requestURI.contains("/connexion")) {
			return "Tentative de connexion";
		} else if (requestURI.contains("/validateNumberCodeOtp")) {
			return "Validation OTP";
		} else if (requestURI.contains("/create")) {
			return "Création";
		} else if (requestURI.contains("/addBlacklistLite")) {
			return "Blaklistage";
		} else if (requestURI.contains("/getByCriteria")) {
			return "Recherche";
		} else if (requestURI.contains("/blockUser")) {
			return "Bloqué utilisateur";
		} else if (requestURI.contains("/unBlockUser")) {
			return "Debloqué utilisateur";
		} else if (requestURI.contains("/logOut")) {
			return "Deconnexion";
		} else if (requestURI.contains("/unBlacklistLite")) {
			return "Deblaklistage";
		} else if (requestURI.contains("/uploadLiteBacklistAndUnBlacklist")) {
			return "Importation";
		} else if (requestURI.contains("/extraction")) {
			return "Exportation";
		} else if (requestURI.contains("/update")) {
			return "Modification";
		} else if (requestURI.contains("/delete")) {
			return "Suppression";
		}
		return "Action non spécifiée";
	}

	@SneakyThrows
	private User getUser(String requestBody) {
		if (requestBody == null || requestBody.isEmpty()) {
			log.info("Le corps de la requête est null ou vide.");
			return null;
		}

		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(requestBody, Map.class);

		if (map == null || !map.containsKey("user") || map.get("user") == null) {
			log.info("La clé 'user' est manquante ou la valeur est null.");
			return null;
		}

		try {
			int userId = ((Number) map.get("user")).intValue();
			User user = userRepository.findOne(userId, Boolean.FALSE);
			if (user == null) {
				log.info("Aucun utilisateur trouvé avec cet ID.");
				return null;
			}
			if (user.getIsLocked() == Boolean.TRUE) {
				throw new CustomEntityNotFoundException("401", "Vous êtes bloqué, contactez un administrateur",
						Boolean.TRUE);
			}
			return user;
		} catch (NumberFormatException e) {
			log.info("Erreur de format : La valeur de 'user' n'est pas un nombre valide.");
			return null;
		}
	}

	private User getUserById(Integer userId) {
		if (userId == null) {
			log.info("L'ID de l'utilisateur est null.");
			return null;
		}

		User user = userRepository.findOne(userId, false);
		if (user == null) {
			log.info("Aucun utilisateur trouvé avec cet ID : {}", userId);
			return null;
		}

		if (user.getIsLocked() != null && user.getIsLocked().equals(true)) {
			log.warn("L'utilisateur avec l'ID {} est bloqué.", userId);
		}

		return user;
	}

	private User getUserByLogin(String login) {
		if (login == null || login.isEmpty()) {
			log.info("Le login est null ou vide.");
			return null;
		}

		User user = userRepository.findByLogin(login, false);
		if (user == null) {
			log.info("Aucun utilisateur trouvé avec ce login.");
			return null;
		}

		if (user.getIsLocked() != null && user.getIsLocked().equals(true)) {
			log.warn("L'utilisateur avec le login {} est bloqué.", login);
		}

		return user;
	}
	
	 public String determineStatusConnection(String responseBody) {
	        if (responseBody == null || responseBody.isEmpty()) {
	            log.warn("ResponseBody is null or empty");
	            return "ECHOUER";
	        }

	        try {
				

				StringReader stringReader = new StringReader(responseBody);
				JsonReader jsonReader = new JsonReader(stringReader);
				jsonReader.setLenient(true); // Allow lenient parsing

				JsonElement jsonElement = JsonParser.parseReader(jsonReader);


	            // JsonElement jsonElement = JsonParser.parseReader(new StringReader(responseBody));

	            if (jsonElement.isJsonObject()) {
	                JsonObject jsonObject = jsonElement.getAsJsonObject();
	                if (jsonObject.has("hasError")) {
	                    boolean hasError = jsonObject.get("hasError").getAsBoolean();
	                    return hasError ? "ECHOUER" : "SUCCESS";
	                }
	            } else if (jsonElement.isJsonPrimitive()) {
	                log.warn("Received a primitive response: {}", responseBody);
	                return "ECHOUER";
	            }

	            log.warn("Unexpected response structure: {}", responseBody);
	            return "ECHOUER";
	        } catch (JsonSyntaxException e) {
	            log.error("Error parsing JSON response: {}", responseBody, e);
	            return "ECHOUER";
	        } catch (Exception e) {
	            log.error("Unexpected error while processing response: {}", responseBody, e);
	            return "ECHOUER";
	        }
	    }

	private void saveLog(Logs logsDto) {
		logsRepository.save(logsDto);
	}

	public static String removePasswordFromRequestBody(String requestBody) {
		if (requestBody == null) {
			log.error("Le corps de la requête est null");
			return null;
		}

		Gson gson = new Gson();
		try {
			Map<String, Object> requestMap = gson.fromJson(requestBody, Map.class);
			if (requestMap != null && requestMap.containsKey("data")) {
				Map<String, Object> data = (Map<String, Object>) requestMap.get("data");
				if (data != null && data.containsKey("password")) {
					data.remove("password");
					requestMap.put("data", data);
				}
			}
			return gson.toJson(requestMap);
		} catch (JsonSyntaxException e) {
			log.error("Erreur lors de la désérialisation du corps de la requête", e);
			return requestBody;
		}
	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		if (characterEncoding == null || characterEncoding.isEmpty()) {
			characterEncoding = "UTF-8";
		}
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			log.error("Erreur lors de la conversion du corps en chaîne", e);
		}
		return "";
	}
	
	
}

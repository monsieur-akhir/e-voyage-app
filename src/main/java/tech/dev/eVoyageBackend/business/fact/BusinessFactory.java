
/*
 * Created on 2024-02-19 ( Time 10:54:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

 package tech.dev.eVoyageBackend.business.fact;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.configs.UtilesFonctions;
import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.entity.RolePermissions;
import tech.dev.eVoyageBackend.dao.entity.Roles;
import tech.dev.eVoyageBackend.dao.repository.FonctionaliteRepository;
import tech.dev.eVoyageBackend.dao.repository.RolePermissionsRepository;
import tech.dev.eVoyageBackend.dao.repository.RolesRepository;
import tech.dev.eVoyageBackend.dao.repository.UserRepository;
import tech.dev.eVoyageBackend.factory.RedisUser;
import tech.dev.eVoyageBackend.utils.ExceptionUtils;
import tech.dev.eVoyageBackend.utils.FunctionalError;
import tech.dev.eVoyageBackend.utils.TokenService;
import tech.dev.eVoyageBackend.utils.Validate;
import tech.dev.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.UserDto;
import tech.dev.eVoyageBackend.utils.dto.UsersDto;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


 
 /**
  * BUSINESS factory
  *
  * @author Geo
  */
 @Log
 @Component
 public class BusinessFactory<DTO> {
 
 //	@Autowired
 //	private UserRepository userRepository;
	 @Autowired
	 private FunctionalError functionalError;
	 @Autowired
	 private ExceptionUtils exceptionUtils;
 
	 @Autowired
	 private RedisUser redisUser;

	 @Autowired private UserRepository userRepository;

	 @Autowired 
     private UtilesFonctions utilesFonctions;

	 @Autowired
	 private FonctionaliteRepository fonctionaliteRepository;

	 @Autowired
	 private RolePermissionsRepository rolePermissionsRepository;

	 @Autowired
	 private RolesRepository rolesRepository;

	 @Autowired
	 private TokenService tokenService;

 
	 /**
	  * create entity by using dto as object.
	  *
	  * @param request
	  * @return response
	  */
	 @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	 public Response<DTO> create(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			 FunctionalityEnum functionalityEnum, Locale locale) {
		 Response<DTO> response = new Response<DTO>();
		 try {
			 checkUserAccess(request, functionalityEnum, locale);
			 response = iBasicBusiness.create(request, locale);
		 } catch (PermissionDeniedDataAccessException e) {
			 exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (DataAccessResourceFailureException e) {
			 exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		 } catch (DataAccessException e) {
			 exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (RuntimeException e) {
			 exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		 } catch (Exception e) {
			 exceptionUtils.EXCEPTION(response, locale, e);
		 } finally {
			 if (response.isHasError() && response.getStatus() != null) {
				 log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						 response.getStatus().getMessage()));
				 throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			 }
		 }
		 return response;
	 }
	 
	 
	 @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	 public Response<DTO> mandatsNotification(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			 FunctionalityEnum functionalityEnum, Locale locale) {
		 Response<DTO> response = new Response<DTO>();
		 try {
			 checkUserAccess(request, functionalityEnum, locale);
			 response = iBasicBusiness.create(request, locale);
		 } catch (PermissionDeniedDataAccessException e) {
			 exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (DataAccessResourceFailureException e) {
			 exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		 } catch (DataAccessException e) {
			 exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (RuntimeException e) {
			 exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		 } catch (Exception e) {
			 exceptionUtils.EXCEPTION(response, locale, e);
		 } finally {
			 if (response.isHasError() && response.getStatus() != null) {
				 log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						 response.getStatus().getMessage()));
				 throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			 }
		 }
		 return response;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
 
	 /**
	  * update entity by using dto as object.
	  *
	  * @param request
	  * @return response
	  */
	 @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	 public Response<DTO> update(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			 FunctionalityEnum functionalityEnum, Locale locale) {
		 Response<DTO> response = new Response<DTO>();
		 try {
			 checkUserAccess(request, functionalityEnum, locale);
			 response = iBasicBusiness.update(request, locale);
		 } catch (PermissionDeniedDataAccessException e) {
			 exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (DataAccessResourceFailureException e) {
			 exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		 } catch (DataAccessException e) {
			 exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (RuntimeException e) {
			 exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		 } catch (Exception e) {
			 exceptionUtils.EXCEPTION(response, locale, e);
		 } finally {
			 if (response.isHasError() && response.getStatus() != null) {
				 log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						 response.getStatus().getMessage()));
				 throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			 }
		 }
		 return response;
	 }
 
	 /**
	  * delete entity by using dto as object.
	  *
	  * @param request
	  * @return response
	  */
	 @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	 public Response<DTO> delete(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			 FunctionalityEnum functionalityEnum, Locale locale) {
		 Response<DTO> response = new Response<DTO>();
		 try {
			 checkUserAccess(request, functionalityEnum, locale);
			 response = iBasicBusiness.delete(request, locale);
		 } catch (PermissionDeniedDataAccessException e) {
			 exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (DataAccessResourceFailureException e) {
			 exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		 } catch (DataAccessException e) {
			 exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (RuntimeException e) {
			 exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		 } catch (Exception e) {
			 exceptionUtils.EXCEPTION(response, locale, e);
		 } finally {
			 if (response.isHasError() && response.getStatus() != null) {
				 log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						 response.getStatus().getMessage()));
				 throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			 }
		 }
		 return response;
	 }
 
	 /**
	  * delete entity by using dto as object.
	  *
	  * @param request
	  * @return response
	  */
	 @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	 public Response<DTO> getByCriteria(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			 FunctionalityEnum functionalityEnum, Locale locale) {
		 Response<DTO> response = new Response<DTO>();
		 try {
			 checkUserAccess(request, functionalityEnum, locale);
			 response = iBasicBusiness.getByCriteria(request, locale);
		 } catch (PermissionDeniedDataAccessException e) {
			 exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (DataAccessResourceFailureException e) {
			 exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		 } catch (DataAccessException e) {
			 exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		 } catch (RuntimeException e) {
			 exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		 } catch (Exception e) {
			 exceptionUtils.EXCEPTION(response, locale, e);
		 } finally {
			 if (response.isHasError() && response.getStatus() != null) {
				 log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						 response.getStatus().getMessage()));
				 throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			 }
		 }
		 return response;
	 }
 
	 public void checkUserAccessOld(Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
		 Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
		 fieldsToVerifyUser.put("user", request.getUser());
		 if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
			 throw new RuntimeException(
					 functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale).getMessage());
		 }
 
		 UserDto userDto = redisUser.get(request.getToken());
		 if (userDto == null) {
			utilesFonctions.tokenExpiration(request.getToken(), request.getUser(), locale);

			//  throw new RuntimeException(functionalError.SESSION_EXPIRER("",locale).getMessage());
		 }
 
 
	 }

	 public void checkUserAccess(Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
		 // Vérification des paramètres obligatoires
		 if (request == null || (request.getToken() == null && request.getRefreshToken() == null)) {
			 throw new RuntimeException(functionalError.INVALID_TOKEN(Validate.getValidate().getField(), locale).getMessage());
		 }

		 // Extraire l'utilisateur depuis le token ou le refreshToken
		 UsersDto usersDto = getUserFromTokens(request.getToken(), request.getRefreshToken(), locale);

		 // Vérifier si l'utilisateur est actif
		 if (!"ACTIVE".equalsIgnoreCase(usersDto.getStatus())) {
			 throw new RuntimeException(functionalError.UNAUTHORIZED_ACCESS(Validate.getValidate().getField(), locale).getMessage());
		 }

		 // Vérification des permissions associées au rôle de l'utilisateur
		 List<RolePermissions> rolePermissions = rolePermissionsRepository.findByRolesIdAndStatus(usersDto.getRoleId(), "ACTIVE");
		 if (rolePermissions.isEmpty()) {
			 throw new RuntimeException(functionalError.PERMISSION_DENIED(Validate.getValidate().getField(), locale).getMessage());
		 }

		 // Vérifier si la fonctionnalité demandée est autorisée
		 boolean hasAccess = rolePermissions.stream()
				 .anyMatch(permission -> permission.getPermissions().getName().equalsIgnoreCase(functionalityEnum.getValue()));

		 if (!hasAccess) {
			 throw new RuntimeException(functionalError.PERMISSION_DENIED(Validate.getValidate().getField(), locale).getMessage());
		 }
	 }

	 /**
	  * Extraire l'utilisateur depuis le token ou le refreshToken.
	  */
	 private UsersDto getUserFromTokens(String token, String refreshToken, Locale locale) {
		 UsersDto usersDto = null;

		 // Essayer de récupérer l'utilisateur depuis l'access token
		 if (token != null) {
			 usersDto = tokenService.getUserFromToken(token);
		 }

		 // Si l'access token est invalide, essayer avec le refresh token
		 if (usersDto == null && refreshToken != null) {
			 usersDto = tokenService.getUserFromToken(refreshToken);
		 }

		 // Si aucun token valide n'est trouvé, lever une exception
		 if (usersDto == null) {
			 throw new RuntimeException(functionalError.INVALID_TOKEN(Validate.getValidate().getField(), locale).getMessage());
		 }

		 return usersDto;
	 }

	 //  public void tokenExpiration(String token, Locale locale) {
	// 		 List<User> users = userRepository.findByToken(token, false);
	 
	// 		 if (users.isEmpty()) {
	// 			 throw new RuntimeException("Token invalide");
	// 		 }	
	// 		 User user = users.get(0);
	// 		 LocalDateTime currentTime = LocalDateTime.now();
	 
	// 		 // Convertir Date en LocalDateTime
	// 		 LocalDateTime tokenExpireAt = user.getTokenExpireAt()
	// 										   .toInstant()
	// 										   .atZone(ZoneId.systemDefault())
	// 										   .toLocalDateTime();
 
	// 		 // Vérifier si le token a expiré
	// 		 if (currentTime.isAfter(tokenExpireAt)) {
	// 			 throw new RuntimeException("Session expirée");
	// 		 } else {
	// 			 System.out.println("Le token est encore valide.");
	// 		 }	
		 
	//  }	 
 
 }
 
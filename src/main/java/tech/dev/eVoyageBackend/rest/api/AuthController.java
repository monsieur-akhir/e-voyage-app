package tech.dev.eVoyageBackend.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import lombok.extern.java.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.dev.eVoyageBackend.dao.repository.UsersRepository;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.transformer.UsersTransformer;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.dao.entity.*;

import java.text.ParseException;
import java.util.Locale;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.servlet.http.HttpServletRequest;



@Log
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsersBusiness usersBusiness;

    @Autowired
    private FunctionalError functionalError;

    @Autowired
    private AuthBusiness authBusiness;
    @Autowired
    private HttpServletRequest requestBasic;

    @Autowired
    private ExceptionUtils exceptionUtils;

    private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
    /**
     * Connexion de l'utilisateur.
     */
    @PostMapping("/login")
    @Operation(summary = "Connexion d'un utilisateur", description = "Authentifie l'utilisateur et retourne les Access/Refresh Tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie", content = @Content(schema = @Schema(implementation = UsersDto.class))),
            @ApiResponse(responseCode = "401", description = "Identifiants invalides")
    })
    public Response<UsersDto> login(@RequestBody Request<LoginDto> request, Locale locale) throws ParseException {
        Response<UsersDto> response = new Response<>();

        if (request == null || request.getData() == null) {
            response.setStatus(functionalError.DATA_NOT_EXIST("Login data", locale));
            response.setHasError(true);
            return response;
        }

        LoginDto loginDto = request.getData();
        Users user = usersRepository.findByEmailOrPhone(loginDto.getEmailOrPhone());

        if (user == null) {
            response.setStatus(functionalError.DATA_NOT_EXIST("User", locale));
            response.setHasError(true);
            return response;
        }

        if (!Utilities.matchPassword(loginDto.getPassword(), user.getPassword())) {
            response.setStatus(functionalError.INVALID_VALUE("Password", locale));
            response.setHasError(true);
            return response;
        }

        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        tokenService.storeTokensInRedis(Long.valueOf(user.getId()), accessToken, refreshToken);

        UsersDto usersDto = UsersTransformer.INSTANCE.toDto(user);
        usersDto.setToken(accessToken);
        usersDto.setRefreshToken(refreshToken);

        response.setItem(usersDto);
        response.setHasError(false);
        return response;
    }

    /**
     * Renouvellement des tokens d'accès.
     */
    @PostMapping("/refresh-token")
    @Operation(summary = "Renouvellement des tokens d'accès", description = "Renouvelle l'Access Token à l'aide du Refresh Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token renouvelé avec succès", content = @Content(schema = @Schema(implementation = UsersDto.class))),
            @ApiResponse(responseCode = "401", description = "Refresh Token invalide")
    })
    public Response<UsersDto> refreshAccessToken(@RequestBody RefreshTokenDto request, Locale locale) throws ParseException {
        Response<UsersDto> response = new Response<>();

        String storedRefreshToken = tokenService.getRefreshTokenFromRedis(request.getUserId());

        if (storedRefreshToken == null || !storedRefreshToken.equals(request.getRefreshToken())) {
            response.setStatus(functionalError.INVALID_VALUE("Refresh Token", locale));
            response.setHasError(true);
            return response;
        }

        Users user = usersRepository.findById(Math.toIntExact(request.getUserId())).orElse(null);

        if (user == null) {
            response.setStatus(functionalError.DATA_NOT_EXIST("User", locale));
            response.setHasError(true);
            return response;
        }

        String newAccessToken = tokenService.generateAccessToken(user);
        String newRefreshToken = tokenService.generateRefreshToken(user);

        tokenService.storeTokensInRedis(Long.valueOf(user.getId()), newAccessToken, newRefreshToken);

        UsersDto usersDto = UsersTransformer.INSTANCE.toDto(user);
        usersDto.setToken(newAccessToken);
        usersDto.setRefreshToken(newRefreshToken);

        response.setItem(usersDto);
        response.setHasError(false);
        return response;
    }

    /**
     * Déconnexion de l'utilisateur.
     */

    @RequestMapping(value = "/logOut", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
            "application/json" })
    public Response<UsersDto> logOut(@RequestBody Request<UsersDto> request) {
        log.info("start method /user/logOut");
        Response<UsersDto> response = new Response<UsersDto>();
        String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");
        try {
            response = Validate.validateObject(request, response, functionalError, locale);
            if (!response.isHasError()) {
                response = authBusiness.logOut(request, locale);
            } else {
                slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage());
                return response;
            }
            if (!response.isHasError()) {
                slf4jLogger.info("end method logOut");
                slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
            } else {
                slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage());
            }
        } catch (CannotCreateTransactionException e) {
            exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
        } catch (TransactionSystemException e) {
            exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
        slf4jLogger.info("end method /user/logOut");
        return response;
    }

    /**
     * Inscription d'un voyageur
     */
    @PostMapping("/register")
    @Operation(summary = "Inscription d'un voyageur", description = "Permet d'inscrire un voyageur et de lui attribuer un rôle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voyageur inscrit avec succès", content = @Content(schema = @Schema(implementation = UsersDto.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "409", description = "L'utilisateur existe déjà")
    })
    public Response<UsersDto> registerTraveller(@RequestBody Request<UsersDto> request, Locale locale) throws ParseException {
        return authBusiness.registerTraveller(request, locale);
    }
}
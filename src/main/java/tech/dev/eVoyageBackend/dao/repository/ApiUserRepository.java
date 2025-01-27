package tech.dev.eVoyageBackend.dao.repository;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.customize._ApiUserRepository;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;

/**
 * Repository : ApiUser.
 */
@Repository
public interface ApiUserRepository extends JpaRepository<ApiUser, Integer>, _ApiUserRepository {
	/**
	 * Finds ApiUser by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object ApiUser whose id is equals to the given id. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.id = :id and e.isDeleted = :isDeleted")
	ApiUser findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds ApiUser by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object ApiUser whose createdAt is equals to the given createdAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object ApiUser whose createdBy is equals to the given createdBy. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<ApiUser> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using dateSendCodeOtpAt as a search criteria.
	 * 
	 * @param dateSendCodeOtpAt
	 * @return An Object ApiUser whose dateSendCodeOtpAt is equals to the given dateSendCodeOtpAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.dateSendCodeOtpAt = :dateSendCodeOtpAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByDateSendCodeOtpAt(@Param("dateSendCodeOtpAt")Date dateSendCodeOtpAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object ApiUser whose email is equals to the given email. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.email = :email and e.isDeleted = :isDeleted")
	ApiUser findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using firstConnection as a search criteria.
	 * 
	 * @param firstConnection
	 * @return An Object ApiUser whose firstConnection is equals to the given firstConnection. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.firstConnection = :firstConnection and e.isDeleted = :isDeleted")
	List<ApiUser> findByFirstConnection(@Param("firstConnection")Date firstConnection, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isActive as a search criteria.
	 * 
	 * @param isActive
	 * @return An Object ApiUser whose isActive is equals to the given isActive. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isActive = :isActive and e.isDeleted = :isDeleted")
	List<ApiUser> findByIsActive(@Param("isActive")Boolean isActive, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isConnected as a search criteria.
	 * 
	 * @param isConnected
	 * @return An Object ApiUser whose isConnected is equals to the given isConnected. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isConnected = :isConnected and e.isDeleted = :isDeleted")
	List<ApiUser> findByIsConnected(@Param("isConnected")Boolean isConnected, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isDefaultPassword as a search criteria.
	 * 
	 * @param isDefaultPassword
	 * @return An Object ApiUser whose isDefaultPassword is equals to the given isDefaultPassword. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isDefaultPassword = :isDefaultPassword and e.isDeleted = :isDeleted")
	List<ApiUser> findByIsDefaultPassword(@Param("isDefaultPassword")Boolean isDefaultPassword, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object ApiUser whose isDeleted is equals to the given isDeleted. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isDeleted = :isDeleted")
	List<ApiUser> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object ApiUser whose isLocked is equals to the given isLocked. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<ApiUser> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isValidPassCode as a search criteria.
	 * 
	 * @param isValidPassCode
	 * @return An Object ApiUser whose isValidPassCode is equals to the given isValidPassCode. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isValidPassCode = :isValidPassCode and e.isDeleted = :isDeleted")
	List<ApiUser> findByIsValidPassCode(@Param("isValidPassCode")Boolean isValidPassCode, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using isValidToken as a search criteria.
	 * 
	 * @param isValidToken
	 * @return An Object ApiUser whose isValidToken is equals to the given isValidToken. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.isValidToken = :isValidToken and e.isDeleted = :isDeleted")
	List<ApiUser> findByIsValidToken(@Param("isValidToken")String isValidToken, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using lastActivityDate as a search criteria.
	 * 
	 * @param lastActivityDate
	 * @return An Object ApiUser whose lastActivityDate is equals to the given lastActivityDate. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.lastActivityDate = :lastActivityDate and e.isDeleted = :isDeleted")
	List<ApiUser> findByLastActivityDate(@Param("lastActivityDate")Date lastActivityDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using lastConnectionDate as a search criteria.
	 * 
	 * @param lastConnectionDate
	 * @return An Object ApiUser whose lastConnectionDate is equals to the given lastConnectionDate. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.lastConnectionDate = :lastConnectionDate and e.isDeleted = :isDeleted")
	List<ApiUser> findByLastConnectionDate(@Param("lastConnectionDate")Date lastConnectionDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using lastLockDate as a search criteria.
	 * 
	 * @param lastLockDate
	 * @return An Object ApiUser whose lastLockDate is equals to the given lastLockDate. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.lastLockDate = :lastLockDate and e.isDeleted = :isDeleted")
	List<ApiUser> findByLastLockDate(@Param("lastLockDate")Date lastLockDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using login as a search criteria.
	 * 
	 * @param login
	 * @return An Object ApiUser whose login is equals to the given login. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.login = :login and e.isDeleted = :isDeleted")
	ApiUser findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using loginAttempts as a search criteria.
	 * 
	 * @param loginAttempts
	 * @return An Object ApiUser whose loginAttempts is equals to the given loginAttempts. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.loginAttempts = :loginAttempts and e.isDeleted = :isDeleted")
	List<ApiUser> findByLoginAttempts(@Param("loginAttempts")Integer loginAttempts, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using otpCode as a search criteria.
	 * 
	 * @param otpCode
	 * @return An Object ApiUser whose otpCode is equals to the given otpCode. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.otpCode = :otpCode and e.isDeleted = :isDeleted")
	List<ApiUser> findByOtpCode(@Param("otpCode")String otpCode, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using passCode as a search criteria.
	 * 
	 * @param passCode
	 * @return An Object ApiUser whose passCode is equals to the given passCode. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.passCode = :passCode and e.isDeleted = :isDeleted")
	List<ApiUser> findByPassCode(@Param("passCode")String passCode, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using passCodeCreatedAt as a search criteria.
	 * 
	 * @param passCodeCreatedAt
	 * @return An Object ApiUser whose passCodeCreatedAt is equals to the given passCodeCreatedAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.passCodeCreatedAt = :passCodeCreatedAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByPassCodeCreatedAt(@Param("passCodeCreatedAt")Date passCodeCreatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using passCodeExpireAt as a search criteria.
	 * 
	 * @param passCodeExpireAt
	 * @return An Object ApiUser whose passCodeExpireAt is equals to the given passCodeExpireAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.passCodeExpireAt = :passCodeExpireAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByPassCodeExpireAt(@Param("passCodeExpireAt")Date passCodeExpireAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using password as a search criteria.
	 * 
	 * @param password
	 * @return An Object ApiUser whose password is equals to the given password. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.password = :password and e.isDeleted = :isDeleted")
	List<ApiUser> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using searchString as a search criteria.
	 * 
	 * @param searchString
	 * @return An Object ApiUser whose searchString is equals to the given searchString. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.searchString = :searchString and e.isDeleted = :isDeleted")
	List<ApiUser> findBySearchString(@Param("searchString")String searchString, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using telephone as a search criteria.
	 * 
	 * @param telephone
	 * @return An Object ApiUser whose telephone is equals to the given telephone. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.telephone = :telephone and e.isDeleted = :isDeleted")
	List<ApiUser> findByTelephone(@Param("telephone")String telephone, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using token as a search criteria.
	 * 
	 * @param token
	 * @return An Object ApiUser whose token is equals to the given token. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.token = :token and e.isDeleted = :isDeleted")
	List<ApiUser> findByToken(@Param("token")String token, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using tokenCreatedAt as a search criteria.
	 * 
	 * @param tokenCreatedAt
	 * @return An Object ApiUser whose tokenCreatedAt is equals to the given tokenCreatedAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.tokenCreatedAt = :tokenCreatedAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByTokenCreatedAt(@Param("tokenCreatedAt")Date tokenCreatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using tokenExpireAt as a search criteria.
	 * 
	 * @param tokenExpireAt
	 * @return An Object ApiUser whose tokenExpireAt is equals to the given tokenExpireAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.tokenExpireAt = :tokenExpireAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByTokenExpireAt(@Param("tokenExpireAt")Date tokenExpireAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object ApiUser whose updatedAt is equals to the given updatedAt. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<ApiUser> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object ApiUser whose updatedBy is equals to the given updatedBy. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<ApiUser> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object ApiUser whose roleId is equals to the given roleId. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.roleId = :roleId and e.isDeleted = :isDeleted")
	List<ApiUser> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ApiUser by using type as a search criteria.
	 * 
	 * @param type
	 * @return An Object ApiUser whose type is equals to the given type. If
	 *         no ApiUser is found, this method returns null.
	 */
	@Query("select e from ApiUser e where e.type = :type and e.isDeleted = :isDeleted")
	List<ApiUser> findByType(@Param("type")String type, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of ApiUser by using apiUserDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of ApiUser
	 * @throws DataAccessException,ParseException
	 */
	default List<ApiUser> getByCriteria(Request<ApiUserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from ApiUser e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<ApiUser> query = em.createQuery(req, ApiUser.class);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of ApiUser by using apiUserDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of ApiUser
	 * 
	 */
	default Long count(Request<ApiUserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from ApiUser e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.id desc";
		javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) query.getResultList().get(0);
		return count;
	}

	/**
	 * get where expression
	 * @param request
	 * @param param
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String getWhereExpression(Request<ApiUserDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		ApiUserDto dto = request.getData() != null ? request.getData() : new ApiUserDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ApiUserDto elt : request.getDatas()) {
				elt.setIsDeleted(false);
				String eltReq = generateCriteria(elt, param, index, locale);
				if (request.getIsAnd() != null && request.getIsAnd()) {
					othersReq += "and (" + eltReq + ") ";
				} else {
					othersReq += "or (" + eltReq + ") ";
				}
				index++;
			}
		}
		String req = "";
		if (!mainReq.isEmpty()) {
			req += " and (" + mainReq + ") ";
		}
		req += othersReq;
		return req;
	}

	/**
	 * generate sql query for dto
	 * @param dto
	 * @param param
	 * @param index
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String generateCriteria(ApiUserDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDateSendCodeOtpAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateSendCodeOtpAt", dto.getDateSendCodeOtpAt(), "e.dateSendCodeOtpAt", "Date", dto.getDateSendCodeOtpAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmail())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFirstConnection())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("firstConnection", dto.getFirstConnection(), "e.firstConnection", "Date", dto.getFirstConnectionParam(), param, index, locale));
			}
			if (dto.getIsActive()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
			}
			if (dto.getIsConnected()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isConnected", dto.getIsConnected(), "e.isConnected", "Boolean", dto.getIsConnectedParam(), param, index, locale));
			}
			if (dto.getIsDefaultPassword()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDefaultPassword", dto.getIsDefaultPassword(), "e.isDefaultPassword", "Boolean", dto.getIsDefaultPasswordParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getIsLocked()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isLocked", dto.getIsLocked(), "e.isLocked", "Boolean", dto.getIsLockedParam(), param, index, locale));
			}
			if (dto.getIsValidPassCode()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isValidPassCode", dto.getIsValidPassCode(), "e.isValidPassCode", "Boolean", dto.getIsValidPassCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getIsValidToken())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isValidToken", dto.getIsValidToken(), "e.isValidToken", "String", dto.getIsValidTokenParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastActivityDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastActivityDate", dto.getLastActivityDate(), "e.lastActivityDate", "Date", dto.getLastActivityDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastConnectionDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastConnectionDate", dto.getLastConnectionDate(), "e.lastConnectionDate", "Date", dto.getLastConnectionDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastLockDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastLockDate", dto.getLastLockDate(), "e.lastLockDate", "Date", dto.getLastLockDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLogin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("login", dto.getLogin(), "e.login", "String", dto.getLoginParam(), param, index, locale));
			}
			if (dto.getLoginAttempts()!= null && dto.getLoginAttempts() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("loginAttempts", dto.getLoginAttempts(), "e.loginAttempts", "Integer", dto.getLoginAttemptsParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getOtpCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("otpCode", dto.getOtpCode(), "e.otpCode", "String", dto.getOtpCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passCode", dto.getPassCode(), "e.passCode", "String", dto.getPassCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassCodeCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passCodeCreatedAt", dto.getPassCodeCreatedAt(), "e.passCodeCreatedAt", "Date", dto.getPassCodeCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassCodeExpireAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passCodeExpireAt", dto.getPassCodeExpireAt(), "e.passCodeExpireAt", "Date", dto.getPassCodeExpireAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("password", dto.getPassword(), "e.password", "String", dto.getPasswordParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSearchString())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("searchString", dto.getSearchString(), "e.searchString", "String", dto.getSearchStringParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTelephone())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("telephone", dto.getTelephone(), "e.telephone", "String", dto.getTelephoneParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getToken())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("token", dto.getToken(), "e.token", "String", dto.getTokenParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTokenCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tokenCreatedAt", dto.getTokenCreatedAt(), "e.tokenCreatedAt", "Date", dto.getTokenCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTokenExpireAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tokenExpireAt", dto.getTokenExpireAt(), "e.tokenExpireAt", "Date", dto.getTokenExpireAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.roleId", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getType())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

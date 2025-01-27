package tech.dev.eVoyageBackend.dao.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.dao.repository.customize._UserRepository;
import tech.dev.eVoyageBackend.utils.CriteriaUtils;
import tech.dev.eVoyageBackend.utils.Utilities;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.dto.UserDto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.util.*;

/**
 * Repository : User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, _UserRepository {
	/**
	 * Finds User by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object User whose id is equals to the given id. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.id = :id and e.isDeleted = :isDeleted")
	User findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds User by using login as a search criteria.
	 * 
	 * @param login
	 * @return An Object User whose login is equals to the given login. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.login = :login and e.isDeleted = :isDeleted")
	User findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using password as a search criteria.
	 * 
	 * @param password
	 * @return An Object User whose password is equals to the given password. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.password = :password and e.isDeleted = :isDeleted")
	List<User> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using firstName as a search criteria.
	 * 
	 * @param firstName
	 * @return An Object User whose firstName is equals to the given firstName. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.firstName = :firstName and e.isDeleted = :isDeleted")
	List<User> findByFirstName(@Param("firstName")String firstName, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using lastName as a search criteria.
	 * 
	 * @param lastName
	 * @return An Object User whose lastName is equals to the given lastName. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.lastName = :lastName and e.isDeleted = :isDeleted")
	List<User> findByLastName(@Param("lastName")String lastName, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using fonction as a search criteria.
	 * 
	 * @param fonction
	 * @return An Object User whose fonction is equals to the given fonction. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.fonction = :fonction and e.isDeleted = :isDeleted")
	List<User> findByFonction(@Param("fonction")String fonction, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using lieuFonction as a search criteria.
	 * 
	 * @param lieuFonction
	 * @return An Object User whose lieuFonction is equals to the given lieuFonction. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.lieuFonction = :lieuFonction and e.isDeleted = :isDeleted")
	List<User> findByLieuFonction(@Param("lieuFonction")String lieuFonction, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object User whose email is equals to the given email. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.email = :email and e.isDeleted = :isDeleted")
	User findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using bornOn as a search criteria.
	 * 
	 * @param bornOn
	 * @return An Object User whose bornOn is equals to the given bornOn. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.bornOn = :bornOn and e.isDeleted = :isDeleted")
	List<User> findByBornOn(@Param("bornOn")Date bornOn, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using telephone as a search criteria.
	 * 
	 * @param telephone
	 * @return An Object User whose telephone is equals to the given telephone. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.telephone = :telephone and e.isDeleted = :isDeleted")
	User findByTelephone(@Param("telephone")String telephone, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isDefaultPassword as a search criteria.
	 * 
	 * @param isDefaultPassword
	 * @return An Object User whose isDefaultPassword is equals to the given isDefaultPassword. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isDefaultPassword = :isDefaultPassword and e.isDeleted = :isDeleted")
	List<User> findByIsDefaultPassword(@Param("isDefaultPassword")Boolean isDefaultPassword, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isConnected as a search criteria.
	 * 
	 * @param isConnected
	 * @return An Object User whose isConnected is equals to the given isConnected. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isConnected = :isConnected and e.isDeleted = :isDeleted")
	List<User> findByIsConnected(@Param("isConnected")Boolean isConnected, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object User whose isLocked is equals to the given isLocked. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<User> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using lastConnectionDate as a search criteria.
	 * 
	 * @param lastConnectionDate
	 * @return An Object User whose lastConnectionDate is equals to the given lastConnectionDate. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.lastConnectionDate = :lastConnectionDate and e.isDeleted = :isDeleted")
	List<User> findByLastConnectionDate(@Param("lastConnectionDate")Date lastConnectionDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using lastLockDate as a search criteria.
	 * 
	 * @param lastLockDate
	 * @return An Object User whose lastLockDate is equals to the given lastLockDate. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.lastLockDate = :lastLockDate and e.isDeleted = :isDeleted")
	List<User> findByLastLockDate(@Param("lastLockDate")Date lastLockDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using passCode as a search criteria.
	 * 
	 * @param passCode
	 * @return An Object User whose passCode is equals to the given passCode. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.passCode = :passCode and e.isDeleted = :isDeleted")
	List<User> findByPassCode(@Param("passCode")String passCode, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isValidPassCode as a search criteria.
	 * 
	 * @param isValidPassCode
	 * @return An Object User whose isValidPassCode is equals to the given isValidPassCode. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isValidPassCode = :isValidPassCode and e.isDeleted = :isDeleted")
	List<User> findByIsValidPassCode(@Param("isValidPassCode")Boolean isValidPassCode, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using passCodeExpireAt as a search criteria.
	 * 
	 * @param passCodeExpireAt
	 * @return An Object User whose passCodeExpireAt is equals to the given passCodeExpireAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.passCodeExpireAt = :passCodeExpireAt and e.isDeleted = :isDeleted")
	List<User> findByPassCodeExpireAt(@Param("passCodeExpireAt")Date passCodeExpireAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using passCodeCreatedAt as a search criteria.
	 * 
	 * @param passCodeCreatedAt
	 * @return An Object User whose passCodeCreatedAt is equals to the given passCodeCreatedAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.passCodeCreatedAt = :passCodeCreatedAt and e.isDeleted = :isDeleted")
	List<User> findByPassCodeCreatedAt(@Param("passCodeCreatedAt")Date passCodeCreatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using token as a search criteria.
	 * 
	 * @param token
	 * @return An Object User whose token is equals to the given token. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.token = :token and e.isDeleted = :isDeleted")
	List<User> findByToken(@Param("token")String token, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isValidToken as a search criteria.
	 * 
	 * @param isValidToken
	 * @return An Object User whose isValidToken is equals to the given isValidToken. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isValidToken = :isValidToken and e.isDeleted = :isDeleted")
	List<User> findByIsValidToken(@Param("isValidToken")String isValidToken, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using tokenCreatedAt as a search criteria.
	 * 
	 * @param tokenCreatedAt
	 * @return An Object User whose tokenCreatedAt is equals to the given tokenCreatedAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.tokenCreatedAt = :tokenCreatedAt and e.isDeleted = :isDeleted")
	List<User> findByTokenCreatedAt(@Param("tokenCreatedAt")Date tokenCreatedAt, @Param("isDeleted")Boolean isDeleted);
	
	/**
	 * Finds User by using tokenExpireAt as a search criteria.
	 * 
	 * @param tokenExpireAt
	 * @return An Object User whose tokenExpireAt is equals to the given tokenExpireAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.tokenExpireAt = :tokenExpireAt and e.isDeleted = :isDeleted")
	List<User> findByTokenExpireAt(@Param("tokenExpireAt")Date tokenExpireAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object User whose createdAt is equals to the given createdAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<User> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object User whose createdBy is equals to the given createdBy. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<User> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object User whose updatedAt is equals to the given updatedAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<User> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object User whose updatedBy is equals to the given updatedBy. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<User> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isActive as a search criteria.
	 * 
	 * @param isActive
	 * @return An Object User whose isActive is equals to the given isActive. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isActive = :isActive and e.isDeleted = :isDeleted")
	List<User> findByIsActive(@Param("isActive")Boolean isActive, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using searchString as a search criteria.
	 * 
	 * @param searchString
	 * @return An Object User whose searchString is equals to the given searchString. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.searchString = :searchString and e.isDeleted = :isDeleted")
	List<User> findBySearchString(@Param("searchString")String searchString, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object User whose isDeleted is equals to the given isDeleted. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isDeleted = :isDeleted")
	List<User> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds User by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object User whose roleId is equals to the given roleId. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<User> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of User by using userDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of User
	 * @throws DataAccessException,ParseException
	 */
	default List<User> getByCriteria(Request<UserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from User e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<User> query = em.createQuery(req, User.class);
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
	 * Finds count of User by using userDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of User
	 * 
	 */
	default Long count(Request<UserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from User e where e IS NOT NULL";
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
	default String getWhereExpression(Request<UserDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		UserDto dto = request.getData() != null ? request.getData() : new UserDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (UserDto elt : request.getDatas()) {
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
	default String generateCriteria(UserDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLogin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("login", dto.getLogin(), "e.login", "String", dto.getLoginParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("password", dto.getPassword(), "e.password", "String", dto.getPasswordParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFirstName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("firstName", dto.getFirstName(), "e.firstName", "String", dto.getFirstNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastName", dto.getLastName(), "e.lastName", "String", dto.getLastNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFonction())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonction", dto.getFonction(), "e.fonction", "String", dto.getFonctionParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLieuFonction())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lieuFonction", dto.getLieuFonction(), "e.lieuFonction", "String", dto.getLieuFonctionParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmail())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getBornOn())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("bornOn", dto.getBornOn(), "e.bornOn", "Date", dto.getBornOnParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTelephone())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("telephone", dto.getTelephone(), "e.telephone", "String", dto.getTelephoneParam(), param, index, locale));
			}
			if (dto.getIsDefaultPassword()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDefaultPassword", dto.getIsDefaultPassword(), "e.isDefaultPassword", "Boolean", dto.getIsDefaultPasswordParam(), param, index, locale));
			}
			if (dto.getIsConnected()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isConnected", dto.getIsConnected(), "e.isConnected", "Boolean", dto.getIsConnectedParam(), param, index, locale));
			}
			if (dto.getIsLocked()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isLocked", dto.getIsLocked(), "e.isLocked", "Boolean", dto.getIsLockedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastConnectionDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastConnectionDate", dto.getLastConnectionDate(), "e.lastConnectionDate", "Date", dto.getLastConnectionDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastLockDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastLockDate", dto.getLastLockDate(), "e.lastLockDate", "Date", dto.getLastLockDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passCode", dto.getPassCode(), "e.passCode", "String", dto.getPassCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getOtpCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("otpCode", dto.getOtpCode(), "e.otpCode", "String", dto.getOtpCodeParam(), param, index, locale));
			}
			if (dto.getIsValidPassCode()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isValidPassCode", dto.getIsValidPassCode(), "e.isValidPassCode", "Boolean", dto.getIsValidPassCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassCodeExpireAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passCodeExpireAt", dto.getPassCodeExpireAt(), "e.passCodeExpireAt", "Date", dto.getPassCodeExpireAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassCodeCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passCodeCreatedAt", dto.getPassCodeCreatedAt(), "e.passCodeCreatedAt", "Date", dto.getPassCodeCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDateSendCodeOtpAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateSendCodeOtpAt", dto.getDateSendCodeOtpAt(), "e.dateSendCodeOtpAt", "Date", dto.getDateSendCodeOtpAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getToken())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateSendCodeOtpAt", dto.getToken(), "e.dateSendCodeOtpAt", "String", dto.getTokenParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getIsValidToken())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isValidToken", dto.getIsValidToken(), "e.isValidToken", "String", dto.getIsValidTokenParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTokenCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tokenCreatedAt", dto.getTokenCreatedAt(), "e.tokenCreatedAt", "Date", dto.getTokenCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTokenExpireAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tokenExpireAt", dto.getTokenExpireAt(), "e.tokenExpireAt", "Date", dto.getTokenExpireAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getIsActive()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSearchString())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("searchString", dto.getSearchString(), "e.searchString", "String", dto.getSearchStringParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.role.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRoleLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleLibelle", dto.getRoleLibelle(), "e.role.libelle", "String", dto.getRoleLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFirstConnection())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("firstConnection", dto.getFirstConnection(), "e.firstConnection", "Date", dto.getFirstConnectionParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

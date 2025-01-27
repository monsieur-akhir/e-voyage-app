package tech.synelia.eVoyageBackend.dao.repository;

import java.util.Date;
import java.util.List;
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

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.customize._UsersRepository;

/**
 * Repository : Users.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>, _UsersRepository {
	/**
	 * Finds Users by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Users whose id is equals to the given id. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.id = :id and e.isDeleted = :isDeleted")
	Users findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Users by using name as a search criteria.
	 * 
	 * @param name
	 * @return An Object Users whose name is equals to the given name. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.name = :name and e.isDeleted = :isDeleted")
	Users findByName(@Param("name")String name, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object Users whose email is equals to the given email. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.email = :email and e.isDeleted = :isDeleted")
	Users findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using phone as a search criteria.
	 * 
	 * @param phone
	 * @return An Object Users whose phone is equals to the given phone. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.phone = :phone and e.isDeleted = :isDeleted")
	List<Users> findByPhone(@Param("phone")String phone, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using password as a search criteria.
	 * 
	 * @param password
	 * @return An Object Users whose password is equals to the given password. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.password = :password and e.isDeleted = :isDeleted")
	List<Users> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object Users whose roleId is equals to the given roleId. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.roleId = :roleId and e.isDeleted = :isDeleted")
	List<Users> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Users whose createdAt is equals to the given createdAt. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Users> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Users whose createdBy is equals to the given createdBy. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Users> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Users whose updatedAt is equals to the given updatedAt. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Users> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Users whose updatedBy is equals to the given updatedBy. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Users> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Users whose isDeleted is equals to the given isDeleted. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.isDeleted = :isDeleted")
	List<Users> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Users whose deletedAt is equals to the given deletedAt. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Users> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Users whose deletedBy is equals to the given deletedBy. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Users> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Users by using usersDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Users
	 * @throws DataAccessException,ParseException
	 */
	default List<Users> getByCriteria(Request<UsersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Users e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Users> query = em.createQuery(req, Users.class);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of Users by using usersDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Users
	 * 
	 */
	default Long count(Request<UsersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Users e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.id desc";
		javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
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
	default String getWhereExpression(Request<UsersDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		UsersDto dto = request.getData() != null ? request.getData() : new UsersDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (UsersDto elt : request.getDatas()) {
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
	default String generateCriteria(UsersDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("name", dto.getName(), "e.name", "String", dto.getNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmail())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPhone())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("phone", dto.getPhone(), "e.phone", "String", dto.getPhoneParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("password", dto.getPassword(), "e.password", "String", dto.getPasswordParam(), param, index, locale));
			}
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.roleId", "Integer", dto.getRoleIdParam(), param, index, locale));
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
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

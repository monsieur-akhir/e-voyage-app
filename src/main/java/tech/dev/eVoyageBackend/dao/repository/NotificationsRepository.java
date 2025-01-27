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

import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.customize._NotificationsRepository;

/**
 * Repository : Notifications.
 */
@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Integer>, _NotificationsRepository {
	/**
	 * Finds Notifications by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Notifications whose id is equals to the given id. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.id = :id and e.isDeleted = :isDeleted")
	Notifications findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Notifications by using type as a search criteria.
	 * 
	 * @param type
	 * @return An Object Notifications whose type is equals to the given type. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.type = :type and e.isDeleted = :isDeleted")
	List<Notifications> findByType(@Param("type")String type, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using content as a search criteria.
	 * 
	 * @param content
	 * @return An Object Notifications whose content is equals to the given content. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.content = :content and e.isDeleted = :isDeleted")
	List<Notifications> findByContent(@Param("content")String content, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Notifications whose status is equals to the given status. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.status = :status and e.isDeleted = :isDeleted")
	List<Notifications> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Notifications whose createdAt is equals to the given createdAt. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Notifications> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Notifications whose createdBy is equals to the given createdBy. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Notifications> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Notifications whose updatedAt is equals to the given updatedAt. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Notifications> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Notifications whose updatedBy is equals to the given updatedBy. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Notifications> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Notifications whose isDeleted is equals to the given isDeleted. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.isDeleted = :isDeleted")
	List<Notifications> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Notifications whose deletedAt is equals to the given deletedAt. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Notifications> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Notifications by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Notifications whose deletedBy is equals to the given deletedBy. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Notifications> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Notifications by using userId as a search criteria.
	 * 
	 * @param userId
	 * @return An Object Notifications whose userId is equals to the given userId. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.users.id = :userId and e.isDeleted = :isDeleted")
	List<Notifications> findByUserId(@Param("userId")Integer userId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Notifications by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object Notifications whose companyId is equals to the given companyId. If
	 *         no Notifications is found, this method returns null.
	 */
	@Query("select e from Notifications e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<Notifications> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Notifications by using notificationsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Notifications
	 * @throws DataAccessException,ParseException
	 */
	default List<Notifications> getByCriteria(Request<NotificationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Notifications e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Notifications> query = em.createQuery(req, Notifications.class);
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
	 * Finds count of Notifications by using notificationsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Notifications
	 * 
	 */
	default Long count(Request<NotificationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Notifications e where e IS NOT NULL";
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
	default String getWhereExpression(Request<NotificationsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		NotificationsDto dto = request.getData() != null ? request.getData() : new NotificationsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (NotificationsDto elt : request.getDatas()) {
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
	default String generateCriteria(NotificationsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getType())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getContent())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("content", dto.getContent(), "e.content", "String", dto.getContentParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatus())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
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
			if (dto.getUserId()!= null && dto.getUserId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("userId", dto.getUserId(), "e.users.id", "Integer", dto.getUserIdParam(), param, index, locale));
			}
			if (dto.getCompanyId()!= null && dto.getCompanyId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companyId", dto.getCompanyId(), "e.companies.id", "Integer", dto.getCompanyIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUsersName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("usersName", dto.getUsersName(), "e.users.name", "String", dto.getUsersNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCompaniesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companiesName", dto.getCompaniesName(), "e.companies.name", "String", dto.getCompaniesNameParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

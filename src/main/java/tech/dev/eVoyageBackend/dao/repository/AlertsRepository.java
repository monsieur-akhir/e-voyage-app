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
import tech.dev.eVoyageBackend.dao.repository.customize._AlertsRepository;

/**
 * Repository : Alerts.
 */
@Repository
public interface AlertsRepository extends JpaRepository<Alerts, Integer>, _AlertsRepository {
	/**
	 * Finds Alerts by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Alerts whose id is equals to the given id. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.id = :id and e.isDeleted = :isDeleted")
	Alerts findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Alerts by using type as a search criteria.
	 * 
	 * @param type
	 * @return An Object Alerts whose type is equals to the given type. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.type = :type and e.isDeleted = :isDeleted")
	List<Alerts> findByType(@Param("type")String type, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using message as a search criteria.
	 * 
	 * @param message
	 * @return An Object Alerts whose message is equals to the given message. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.message = :message and e.isDeleted = :isDeleted")
	List<Alerts> findByMessage(@Param("message")String message, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Alerts whose status is equals to the given status. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.status = :status and e.isDeleted = :isDeleted")
	List<Alerts> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Alerts whose createdAt is equals to the given createdAt. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Alerts> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Alerts whose createdBy is equals to the given createdBy. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Alerts> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Alerts whose updatedAt is equals to the given updatedAt. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Alerts> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Alerts whose updatedBy is equals to the given updatedBy. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Alerts> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Alerts whose isDeleted is equals to the given isDeleted. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.isDeleted = :isDeleted")
	List<Alerts> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Alerts whose deletedAt is equals to the given deletedAt. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Alerts> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Alerts by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Alerts whose deletedBy is equals to the given deletedBy. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Alerts> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Alerts by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object Alerts whose companyId is equals to the given companyId. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<Alerts> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Alerts by using routeId as a search criteria.
	 * 
	 * @param departId
	 * @return An Object Alerts whose routeId is equals to the given routeId. If
	 *         no Alerts is found, this method returns null.
	 */
	@Query("select e from Alerts e where e.departs.id = :departId and e.isDeleted = :isDeleted")
	List<Alerts> findByRouteId(@Param("departId")Integer departId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Alerts by using alertsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Alerts
	 * @throws DataAccessException,ParseException
	 */
	default List<Alerts> getByCriteria(Request<AlertsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Alerts e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Alerts> query = em.createQuery(req, Alerts.class);
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
	 * Finds count of Alerts by using alertsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Alerts
	 * 
	 */
	default Long count(Request<AlertsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Alerts e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AlertsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		AlertsDto dto = request.getData() != null ? request.getData() : new AlertsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AlertsDto elt : request.getDatas()) {
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
	default String generateCriteria(AlertsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getType())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMessage())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("message", dto.getMessage(), "e.message", "String", dto.getMessageParam(), param, index, locale));
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
			if (dto.getCompanyId()!= null && dto.getCompanyId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companyId", dto.getCompanyId(), "e.companies.id", "Integer", dto.getCompanyIdParam(), param, index, locale));
			}
			if (dto.getDepartId()!= null && dto.getDepartId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("DepartId", dto.getDepartId(), "e.routes.id", "Integer", dto.getDepartIdParam(), param, index, locale));
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

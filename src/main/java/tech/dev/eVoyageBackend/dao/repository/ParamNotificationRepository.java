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

import tech.dev.eVoyageBackend.dao.repository.customize._ParamNotificationRepository;
import tech.dev.eVoyageBackend.utils.CriteriaUtils;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;

/**
 * Repository : ParamNotification.
 */
@Repository
public interface ParamNotificationRepository extends JpaRepository<tech.dev.eVoyageBackend.dao.entity.ParamNotification, Integer>, _ParamNotificationRepository {
	/**
	 * Finds ParamNotification by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object ParamNotification whose id is equals to the given id. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.id = :id and e.isDeleted = :isDeleted")
    tech.dev.eVoyageBackend.dao.entity.ParamNotification findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	@Query("select e from ParamNotification e where e.type = :type and e.lang = :lang and e.isDeleted = :isDeleted")
    tech.dev.eVoyageBackend.dao.entity.ParamNotification findByLangAndNotifType(@Param("type")String type, @Param("lang")String lang, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds ParamNotification by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object ParamNotification whose createdAt is equals to the given createdAt. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object ParamNotification whose createdBy is equals to the given createdBy. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object ParamNotification whose isDeleted is equals to the given isDeleted. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using lang as a search criteria.
	 * 
	 * @param lang
	 * @return An Object ParamNotification whose lang is equals to the given lang. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.lang = :lang and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByLang(@Param("lang")String lang, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using message as a search criteria.
	 * 
	 * @param message
	 * @return An Object ParamNotification whose message is equals to the given message. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.message = :message and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByMessage(@Param("message")String message, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using type as a search criteria.
	 * 
	 * @param type
	 * @return An Object ParamNotification whose type is equals to the given type. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.type = :type and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByType(@Param("type")String type, @Param("isDeleted")Boolean isDeleted);

	@Query("select e from ParamNotification e where e.type = :type and e.lang = :lang and e.isDeleted = :isDeleted")
    tech.dev.eVoyageBackend.dao.entity.ParamNotification findByNotificationType(@Param("type")String type, @Param("lang")String lang, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object ParamNotification whose updatedAt is equals to the given updatedAt. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParamNotification by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object ParamNotification whose updatedBy is equals to the given updatedBy. If
	 *         no ParamNotification is found, this method returns null.
	 */
	@Query("select e from ParamNotification e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of ParamNotification by using paramNotificationDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of ParamNotification
	 * @throws DataAccessException,ParseException
	 */
	default List<tech.dev.eVoyageBackend.dao.entity.ParamNotification> getByCriteria(tech.dev.eVoyageBackend.utils.contract.Request<tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from ParamNotification e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<tech.dev.eVoyageBackend.dao.entity.ParamNotification> query = em.createQuery(req, tech.dev.eVoyageBackend.dao.entity.ParamNotification.class);
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
	 * Finds count of ParamNotification by using paramNotificationDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of ParamNotification
	 * 
	 */
	default Long count(tech.dev.eVoyageBackend.utils.contract.Request<tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from ParamNotification e where e IS NOT NULL";
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
	default String getWhereExpression(Request<tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto dto = request.getData() != null ? request.getData() : new tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto elt : request.getDatas()) {
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
	default String generateCriteria(ParamNotificationDto dto, HashMap<String, Object> param, Integer index, Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (tech.dev.eVoyageBackend.utils.Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (tech.dev.eVoyageBackend.utils.Utilities.notBlank(dto.getLang())) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("lang", dto.getLang(), "e.lang", "String", dto.getLangParam(), param, index, locale));
			}
			if (tech.dev.eVoyageBackend.utils.Utilities.notBlank(dto.getMessage())) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("message", dto.getMessage(), "e.message", "String", dto.getMessageParam(), param, index, locale));
			}
			if (tech.dev.eVoyageBackend.utils.Utilities.notBlank(dto.getType())) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
			}
			if (tech.dev.eVoyageBackend.utils.Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(tech.dev.eVoyageBackend.utils.CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (tech.dev.eVoyageBackend.utils.Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

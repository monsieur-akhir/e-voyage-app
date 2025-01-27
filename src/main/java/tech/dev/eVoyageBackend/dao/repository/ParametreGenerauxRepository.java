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
import tech.dev.eVoyageBackend.dao.repository.customize._ParametreGenerauxRepository;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;

/**
 * Repository : ParametreGeneraux.
 */
@Repository
public interface ParametreGenerauxRepository extends JpaRepository<ParametreGeneraux, Integer>, _ParametreGenerauxRepository {
	/**
	 * Finds ParametreGeneraux by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object ParametreGeneraux whose id is equals to the given id. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.id = :id and e.isDeleted = :isDeleted")
	ParametreGeneraux findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds ParametreGeneraux by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object ParametreGeneraux whose createdAt is equals to the given createdAt. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object ParametreGeneraux whose createdBy is equals to the given createdBy. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object ParametreGeneraux whose deletedAt is equals to the given deletedAt. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object ParametreGeneraux whose deletedBy is equals to the given deletedBy. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object ParametreGeneraux whose isDeleted is equals to the given isDeleted. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using paramName as a search criteria.
	 * 
	 * @param paramName
	 * @return An Object ParametreGeneraux whose paramName is equals to the given paramName. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.paramName = :paramName and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByParamName(@Param("paramName")String paramName, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using paramValue as a search criteria.
	 * 
	 * @param paramValue
	 * @return An Object ParametreGeneraux whose paramValue is equals to the given paramValue. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.paramValue = :paramValue and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByParamValue(@Param("paramValue")String paramValue, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object ParametreGeneraux whose updatedAt is equals to the given updatedAt. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds ParametreGeneraux by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object ParametreGeneraux whose updatedBy is equals to the given updatedBy. If
	 *         no ParametreGeneraux is found, this method returns null.
	 */
	@Query("select e from ParametreGeneraux e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<ParametreGeneraux> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of ParametreGeneraux by using parametreGenerauxDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of ParametreGeneraux
	 * @throws DataAccessException,ParseException
	 */
	default List<ParametreGeneraux> getByCriteria(Request<ParametreGenerauxDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from ParametreGeneraux e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<ParametreGeneraux> query = em.createQuery(req, ParametreGeneraux.class);
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
	 * Finds count of ParametreGeneraux by using parametreGenerauxDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of ParametreGeneraux
	 * 
	 */
	default Long count(Request<ParametreGenerauxDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from ParametreGeneraux e where e IS NOT NULL";
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
	default String getWhereExpression(Request<ParametreGenerauxDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		ParametreGenerauxDto dto = request.getData() != null ? request.getData() : new ParametreGenerauxDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ParametreGenerauxDto elt : request.getDatas()) {
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
	default String generateCriteria(ParametreGenerauxDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getParamName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("paramName", dto.getParamName(), "e.paramName", "String", dto.getParamNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getParamValue())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("paramValue", dto.getParamValue(), "e.paramValue", "String", dto.getParamValueParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

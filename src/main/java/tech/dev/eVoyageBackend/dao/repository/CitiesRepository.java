package tech.dev.eVoyageBackend.dao.repository;

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

import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.customize._CitiesRepository;

/**
 * Repository : Cities.
 */
@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer>, _CitiesRepository {
	/**
	 * Finds Cities by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Cities whose id is equals to the given id. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.id = :id and e.isDeleted = :isDeleted")
	Cities findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Cities by using name as a search criteria.
	 * 
	 * @param name
	 * @return An Object Cities whose name is equals to the given name. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.name = :name and e.isDeleted = :isDeleted")
	Cities findByName(@Param("name")String name, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using isAvailable as a search criteria.
	 * 
	 * @param isAvailable
	 * @return An Object Cities whose isAvailable is equals to the given isAvailable. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.isAvailable = :isAvailable and e.isDeleted = :isDeleted")
	List<Cities> findByIsAvailable(@Param("isAvailable")Boolean isAvailable, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Cities whose createdAt is equals to the given createdAt. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Cities> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Cities whose createdBy is equals to the given createdBy. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Cities> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Cities whose updatedAt is equals to the given updatedAt. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Cities> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Cities whose updatedBy is equals to the given updatedBy. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Cities> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Cities whose isDeleted is equals to the given isDeleted. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.isDeleted = :isDeleted")
	List<Cities> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Cities whose deletedAt is equals to the given deletedAt. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Cities> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Cities by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Cities whose deletedBy is equals to the given deletedBy. If
	 *         no Cities is found, this method returns null.
	 */
	@Query("select e from Cities e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Cities> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Cities by using citiesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Cities
	 * @throws DataAccessException,ParseException
	 */
	default List<Cities> getByCriteria(Request<CitiesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Cities e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Cities> query = em.createQuery(req, Cities.class);
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
	 * Finds count of Cities by using citiesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Cities
	 * 
	 */
	default Long count(Request<CitiesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Cities e where e IS NOT NULL";
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
	default String getWhereExpression(Request<CitiesDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		CitiesDto dto = request.getData() != null ? request.getData() : new CitiesDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (CitiesDto elt : request.getDatas()) {
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
	default String generateCriteria(CitiesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("name", dto.getName(), "e.name", "String", dto.getNameParam(), param, index, locale));
			}
			if (dto.getIsAvailable()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isAvailable", dto.getIsAvailable(), "e.isAvailable", "Boolean", dto.getIsAvailableParam(), param, index, locale));
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

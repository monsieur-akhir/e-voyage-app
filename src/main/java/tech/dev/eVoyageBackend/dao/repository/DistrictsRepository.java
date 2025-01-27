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
import tech.dev.eVoyageBackend.dao.repository.customize._DistrictsRepository;

/**
 * Repository : Districts.
 */
@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Integer>, _DistrictsRepository {
	/**
	 * Finds Districts by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Districts whose id is equals to the given id. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.id = :id and e.isDeleted = :isDeleted")
	Districts findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Districts by using name as a search criteria.
	 * 
	 * @param name
	 * @return An Object Districts whose name is equals to the given name. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.name = :name and e.isDeleted = :isDeleted")
	Districts findByName(@Param("name")String name, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using isAvailable as a search criteria.
	 * 
	 * @param isAvailable
	 * @return An Object Districts whose isAvailable is equals to the given isAvailable. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.isAvailable = :isAvailable and e.isDeleted = :isDeleted")
	List<Districts> findByIsAvailable(@Param("isAvailable")Boolean isAvailable, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Districts whose createdAt is equals to the given createdAt. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Districts> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Districts whose createdBy is equals to the given createdBy. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Districts> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Districts whose updatedAt is equals to the given updatedAt. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Districts> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Districts whose updatedBy is equals to the given updatedBy. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Districts> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Districts whose isDeleted is equals to the given isDeleted. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.isDeleted = :isDeleted")
	List<Districts> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Districts whose deletedAt is equals to the given deletedAt. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Districts> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Districts by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Districts whose deletedBy is equals to the given deletedBy. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Districts> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Districts by using cityId as a search criteria.
	 * 
	 * @param cityId
	 * @return An Object Districts whose cityId is equals to the given cityId. If
	 *         no Districts is found, this method returns null.
	 */
	@Query("select e from Districts e where e.cities.id = :cityId and e.isDeleted = :isDeleted")
	List<Districts> findByCityId(@Param("cityId")Integer cityId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Districts by using districtsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Districts
	 * @throws DataAccessException,ParseException
	 */
	default List<Districts> getByCriteria(Request<DistrictsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Districts e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Districts> query = em.createQuery(req, Districts.class);
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
	 * Finds count of Districts by using districtsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Districts
	 * 
	 */
	default Long count(Request<DistrictsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Districts e where e IS NOT NULL";
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
	default String getWhereExpression(Request<DistrictsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		DistrictsDto dto = request.getData() != null ? request.getData() : new DistrictsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (DistrictsDto elt : request.getDatas()) {
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
	default String generateCriteria(DistrictsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
			if (dto.getCityId()!= null && dto.getCityId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("cityId", dto.getCityId(), "e.cities.id", "Integer", dto.getCityIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCitiesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("citiesName", dto.getCitiesName(), "e.cities.name", "String", dto.getCitiesNameParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

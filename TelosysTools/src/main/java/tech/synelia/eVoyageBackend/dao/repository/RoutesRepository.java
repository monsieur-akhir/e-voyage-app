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
import tech.synelia.eVoyageBackend.dao.repository.customize._RoutesRepository;

/**
 * Repository : Routes.
 */
@Repository
public interface RoutesRepository extends JpaRepository<Routes, Integer>, _RoutesRepository {
	/**
	 * Finds Routes by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Routes whose id is equals to the given id. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.id = :id and e.isDeleted = :isDeleted")
	Routes findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Routes by using origin as a search criteria.
	 * 
	 * @param origin
	 * @return An Object Routes whose origin is equals to the given origin. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.origin = :origin and e.isDeleted = :isDeleted")
	List<Routes> findByOrigin(@Param("origin")String origin, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using destination as a search criteria.
	 * 
	 * @param destination
	 * @return An Object Routes whose destination is equals to the given destination. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.destination = :destination and e.isDeleted = :isDeleted")
	List<Routes> findByDestination(@Param("destination")String destination, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using duration as a search criteria.
	 * 
	 * @param duration
	 * @return An Object Routes whose duration is equals to the given duration. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.duration = :duration and e.isDeleted = :isDeleted")
	List<Routes> findByDuration(@Param("duration")Integer duration, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using price as a search criteria.
	 * 
	 * @param price
	 * @return An Object Routes whose price is equals to the given price. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.price = :price and e.isDeleted = :isDeleted")
	List<Routes> findByPrice(@Param("price")Double price, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Routes whose createdAt is equals to the given createdAt. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Routes> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Routes whose createdBy is equals to the given createdBy. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Routes> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Routes whose updatedAt is equals to the given updatedAt. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Routes> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Routes whose updatedBy is equals to the given updatedBy. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Routes> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Routes whose isDeleted is equals to the given isDeleted. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.isDeleted = :isDeleted")
	List<Routes> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Routes whose deletedAt is equals to the given deletedAt. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Routes> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Routes whose deletedBy is equals to the given deletedBy. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Routes> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Routes by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object Routes whose companyId is equals to the given companyId. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<Routes> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Routes by using routesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Routes
	 * @throws DataAccessException,ParseException
	 */
	default List<Routes> getByCriteria(Request<RoutesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Routes e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Routes> query = em.createQuery(req, Routes.class);
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
	 * Finds count of Routes by using routesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Routes
	 * 
	 */
	default Long count(Request<RoutesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Routes e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RoutesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		RoutesDto dto = request.getData() != null ? request.getData() : new RoutesDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RoutesDto elt : request.getDatas()) {
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
	default String generateCriteria(RoutesDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getOrigin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("origin", dto.getOrigin(), "e.origin", "String", dto.getOriginParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDestination())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("destination", dto.getDestination(), "e.destination", "String", dto.getDestinationParam(), param, index, locale));
			}
			if (dto.getDuration()!= null && dto.getDuration() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("duration", dto.getDuration(), "e.duration", "Integer", dto.getDurationParam(), param, index, locale));
			}
			if (dto.getPrice()!= null && dto.getPrice() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("price", dto.getPrice(), "e.price", "Double", dto.getPriceParam(), param, index, locale));
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

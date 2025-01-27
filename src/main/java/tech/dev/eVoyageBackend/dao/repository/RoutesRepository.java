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
import tech.dev.eVoyageBackend.dao.repository.customize._RoutesRepository;

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
	 * Finds Routes by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Routes whose status is equals to the given status. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.status = :status and e.isDeleted = :isDeleted")
	List<Routes> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Routes by using rating as a search criteria.
	 * 
	 * @param rating
	 * @return An Object Routes whose rating is equals to the given rating. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.rating = :rating and e.isDeleted = :isDeleted")
	List<Routes> findByRating(@Param("rating")Double rating, @Param("isDeleted")Boolean isDeleted);
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
	 * Finds Routes by using originCityId as a search criteria.
	 * 
	 * @param originCityId
	 * @return An Object Routes whose originCityId is equals to the given originCityId. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.cities.id = :originCityId and e.isDeleted = :isDeleted")
	List<Routes> findByOriginCityId(@Param("originCityId")Integer originCityId, @Param("isDeleted")Boolean isDeleted);

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
	 * Finds Routes by using destinationCityId as a search criteria.
	 * 
	 * @param destinationCityId
	 * @return An Object Routes whose destinationCityId is equals to the given destinationCityId. If
	 *         no Routes is found, this method returns null.
	 */
	@Query("select e from Routes e where e.cities2.id = :destinationCityId and e.isDeleted = :isDeleted")
	List<Routes> findByDestinationCityId(@Param("destinationCityId")Integer destinationCityId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Routes by using routesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Routes
	 * @throws DataAccessException,ParseException
	 */
	default List<Routes> getByCriteria(Request<RoutesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Routes e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Routes> query = em.createQuery(req, Routes.class);
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
	 * Finds count of Routes by using routesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Routes
	 * 
	 */
	default Long count(Request<RoutesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Routes e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RoutesDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
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
	default String generateCriteria(RoutesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getDuration()!= null && dto.getDuration() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("duration", dto.getDuration(), "e.duration", "Integer", dto.getDurationParam(), param, index, locale));
			}
			if (dto.getPrice()!= null && dto.getPrice() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("price", dto.getPrice(), "e.price", "Double", dto.getPriceParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatus())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
			}
			if (dto.getRating()!= null && dto.getRating() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("rating", dto.getRating(), "e.rating", "Double", dto.getRatingParam(), param, index, locale));
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
			if (dto.getOriginCityId()!= null && dto.getOriginCityId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("originCityId", dto.getOriginCityId(), "e.cities.id", "Integer", dto.getOriginCityIdParam(), param, index, locale));
			}
			if (dto.getCompanyId()!= null && dto.getCompanyId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companyId", dto.getCompanyId(), "e.companies.id", "Integer", dto.getCompanyIdParam(), param, index, locale));
			}
			if (dto.getDestinationCityId()!= null && dto.getDestinationCityId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("destinationCityId", dto.getDestinationCityId(), "e.cities2.id", "Integer", dto.getDestinationCityIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCitiesNameOrigin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("citiesName", dto.getCitiesNameOrigin(), "e.cities.name", "String", dto.getCitiesNameOriginParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCitiesCodeOrigin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("citiesCode", dto.getCitiesCodeOrigin(), "e.cities.code", "String", dto.getCitiesCodeOriginParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCompaniesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companiesName", dto.getCompaniesName(), "e.companies.name", "String", dto.getCompaniesNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCitiesNameDestination())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("citiesName", dto.getCitiesNameDestination(), "e.cities2.name", "String", dto.getCitiesNameDestinationParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCitiesCodeDestination())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("citiesCode", dto.getCitiesCodeDestination(), "e.cities2.code", "String", dto.getCitiesCodeDestinationParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

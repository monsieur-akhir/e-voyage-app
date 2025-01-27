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
import tech.dev.eVoyageBackend.dao.repository.customize._TravelSchedulesRepository;

/**
 * Repository : TravelSchedules.
 */
@Repository
public interface TravelSchedulesRepository extends JpaRepository<TravelSchedules, Integer>, _TravelSchedulesRepository {
	/**
	 * Finds TravelSchedules by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object TravelSchedules whose id is equals to the given id. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.id = :id")
	TravelSchedules findOne(@Param("id")Integer id);

	/**
	 * Finds TravelSchedules by using departureStation as a search criteria.
	 * 
	 * @param departureStation
	 * @return An Object TravelSchedules whose departureStation is equals to the given departureStation. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.departureStation = :departureStation")
	List<TravelSchedules> findByDepartureStation(@Param("departureStation")String departureStation);
	/**
	 * Finds TravelSchedules by using arrivalStation as a search criteria.
	 * 
	 * @param arrivalStation
	 * @return An Object TravelSchedules whose arrivalStation is equals to the given arrivalStation. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.arrivalStation = :arrivalStation")
	List<TravelSchedules> findByArrivalStation(@Param("arrivalStation")String arrivalStation);
	/**
	 * Finds TravelSchedules by using departureTime as a search criteria.
	 * 
	 * @param departureTime
	 * @return An Object TravelSchedules whose departureTime is equals to the given departureTime. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.departureTime = :departureTime")
	List<TravelSchedules> findByDepartureTime(@Param("departureTime")Date departureTime);
	/**
	 * Finds TravelSchedules by using arrivalTime as a search criteria.
	 * 
	 * @param arrivalTime
	 * @return An Object TravelSchedules whose arrivalTime is equals to the given arrivalTime. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.arrivalTime = :arrivalTime")
	List<TravelSchedules> findByArrivalTime(@Param("arrivalTime")Date arrivalTime);
	/**
	 * Finds TravelSchedules by using travelDate as a search criteria.
	 * 
	 * @param travelDate
	 * @return An Object TravelSchedules whose travelDate is equals to the given travelDate. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.travelDate = :travelDate")
	List<TravelSchedules> findByTravelDate(@Param("travelDate")Date travelDate);
	/**
	 * Finds TravelSchedules by using price as a search criteria.
	 * 
	 * @param price
	 * @return An Object TravelSchedules whose price is equals to the given price. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.price = :price")
	List<TravelSchedules> findByPrice(@Param("price")Double price);
	/**
	 * Finds TravelSchedules by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object TravelSchedules whose status is equals to the given status. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.status = :status")
	List<TravelSchedules> findByStatus(@Param("status")String status);
	/**
	 * Finds TravelSchedules by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object TravelSchedules whose createdAt is equals to the given createdAt. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.createdAt = :createdAt")
	List<TravelSchedules> findByCreatedAt(@Param("createdAt")Date createdAt);
	/**
	 * Finds TravelSchedules by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object TravelSchedules whose createdBy is equals to the given createdBy. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.createdBy = :createdBy")
	List<TravelSchedules> findByCreatedBy(@Param("createdBy")Integer createdBy);
	/**
	 * Finds TravelSchedules by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object TravelSchedules whose updatedAt is equals to the given updatedAt. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.updatedAt = :updatedAt")
	List<TravelSchedules> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
	/**
	 * Finds TravelSchedules by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object TravelSchedules whose updatedBy is equals to the given updatedBy. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.updatedBy = :updatedBy")
	List<TravelSchedules> findByUpdatedBy(@Param("updatedBy")Integer updatedBy);

	/**
	 * Finds TravelSchedules by using routeId as a search criteria.
	 * 
	 * @param routeId
	 * @return An Object TravelSchedules whose routeId is equals to the given routeId. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.routes.id = :routeId")
	List<TravelSchedules> findByRouteId(@Param("routeId")Integer routeId);

	/**
	 * Finds TravelSchedules by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object TravelSchedules whose companyId is equals to the given companyId. If
	 *         no TravelSchedules is found, this method returns null.
	 */
	@Query("select e from TravelSchedules e where e.companies.id = :companyId")
	List<TravelSchedules> findByCompanyId(@Param("companyId")Integer companyId);

	/**
	 * Finds List of TravelSchedules by using travelSchedulesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of TravelSchedules
	 * @throws DataAccessException,ParseException
	 */
	default List<TravelSchedules> getByCriteria(Request<TravelSchedulesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from TravelSchedules e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<TravelSchedules> query = em.createQuery(req, TravelSchedules.class);
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
	 * Finds count of TravelSchedules by using travelSchedulesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of TravelSchedules
	 * 
	 */
	default Long count(Request<TravelSchedulesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from TravelSchedules e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TravelSchedulesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		TravelSchedulesDto dto = request.getData() != null ? request.getData() : new TravelSchedulesDto();
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TravelSchedulesDto elt : request.getDatas()) {
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
	default String generateCriteria(TravelSchedulesDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDepartureStation())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("departureStation", dto.getDepartureStation(), "e.departureStation", "String", dto.getDepartureStationParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getArrivalStation())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("arrivalStation", dto.getArrivalStation(), "e.arrivalStation", "String", dto.getArrivalStationParam(), param, index, locale));
			}
			if (dto.getDepartureTime()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("departureTime", dto.getDepartureTime(), "e.departureTime", "Date", dto.getDepartureTimeParam(), param, index, locale));
			}
			if (dto.getArrivalTime()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("arrivalTime", dto.getArrivalTime(), "e.arrivalTime", "Date", dto.getArrivalTimeParam(), param, index, locale));
			}
			if (dto.getTravelDate()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("travelDate", dto.getTravelDate(), "e.travelDate", "Date", dto.getTravelDateParam(), param, index, locale));
			}
			if (dto.getPrice()!= null && dto.getPrice() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("price", dto.getPrice(), "e.price", "Double", dto.getPriceParam(), param, index, locale));
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
			if (dto.getRouteId()!= null && dto.getRouteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("routeId", dto.getRouteId(), "e.routes.id", "Integer", dto.getRouteIdParam(), param, index, locale));
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

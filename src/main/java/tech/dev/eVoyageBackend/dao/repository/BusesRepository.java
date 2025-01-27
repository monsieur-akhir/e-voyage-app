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
import tech.dev.eVoyageBackend.dao.repository.customize._BusesRepository;

/**
 * Repository : Buses.
 */
@Repository
public interface BusesRepository extends JpaRepository<Buses, Integer>, _BusesRepository {
	/**
	 * Finds Buses by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Buses whose id is equals to the given id. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.id = :id and e.isDeleted = :isDeleted")
	Buses findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Buses by using busNumber as a search criteria.
	 * 
	 * @param busNumber
	 * @return An Object Buses whose busNumber is equals to the given busNumber. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.busNumber = :busNumber and e.isDeleted = :isDeleted")
	List<Buses> findByBusNumber(@Param("busNumber")String busNumber, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using capacity as a search criteria.
	 * 
	 * @param capacity
	 * @return An Object Buses whose capacity is equals to the given capacity. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.capacity = :capacity and e.isDeleted = :isDeleted")
	List<Buses> findByCapacity(@Param("capacity")Integer capacity, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Buses by using available seats as a search criteria.
	 */

	@Query("select e from Buses e where e.availableSeats=:availableSeats and e.isDeleted = :isDeleted")
	List<Buses> findByAvailableSeats(@Param("availableSeats")Integer availableSeats, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Buses by using seatNumbers as a search criteria.
	 * 
	 * @param seatNumbers
	 * @return An Object Buses whose seatNumbers is equals to the given seatNumbers. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.seatNumbers = :seatNumbers and e.isDeleted = :isDeleted")
	List<Buses> findBySeatNumbers(@Param("seatNumbers")String seatNumbers, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Buses whose status is equals to the given status. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.status = :status and e.isDeleted = :isDeleted")
	List<Buses> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Buses whose createdAt is equals to the given createdAt. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Buses> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Buses whose createdBy is equals to the given createdBy. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Buses> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Buses whose updatedAt is equals to the given updatedAt. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Buses> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Buses whose updatedBy is equals to the given updatedBy. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Buses> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Buses whose isDeleted is equals to the given isDeleted. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.isDeleted = :isDeleted")
	List<Buses> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Buses whose deletedAt is equals to the given deletedAt. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Buses> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Buses by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Buses whose deletedBy is equals to the given deletedBy. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Buses> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Buses by using routeId as a search criteria.
	 * 
	 * @param routeId
	 * @return An Object Buses whose routeId is equals to the given routeId. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.routes.id = :routeId and e.isDeleted = :isDeleted")
	List<Buses> findByRouteId(@Param("routeId")Integer routeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Buses by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object Buses whose companyId is equals to the given companyId. If
	 *         no Buses is found, this method returns null.
	 */
	@Query("select e from Buses e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<Buses> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Buses by using busesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Buses
	 * @throws DataAccessException,ParseException
	 */
	default List<Buses> getByCriteria(Request<BusesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Buses e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Buses> query = em.createQuery(req, Buses.class);
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
	 * Finds count of Buses by using busesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Buses
	 * 
	 */
	default Long count(Request<BusesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Buses e where e IS NOT NULL";
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
	default String getWhereExpression(Request<BusesDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		BusesDto dto = request.getData() != null ? request.getData() : new BusesDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (BusesDto elt : request.getDatas()) {
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
	default String generateCriteria(BusesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getBusNumber())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("busNumber", dto.getBusNumber(), "e.busNumber", "String", dto.getBusNumberParam(), param, index, locale));
			}
			if (dto.getCapacity()!= null && dto.getCapacity() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("capacity", dto.getCapacity(), "e.capacity", "Integer", dto.getCapacityParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSeatNumbers())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("seatNumbers", dto.getSeatNumbers(), "e.seatNumbers", "String", dto.getSeatNumbersParam(), param, index, locale));
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

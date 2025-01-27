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
import tech.dev.eVoyageBackend.dao.repository.customize._BookingsRepository;

/**
 * Repository : Bookings.
 */
@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer>, _BookingsRepository {
	/**
	 * Finds Bookings by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Bookings whose id is equals to the given id. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.id = :id and e.isDeleted = :isDeleted")
	Bookings findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using seatNumber as a search criteria.
	 * 
	 * @param seatNumber
	 * @return An Object Bookings whose seatNumber is equals to the given seatNumber. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.seatNumber = :seatNumber and e.isDeleted = :isDeleted")
	List<Bookings> findBySeatNumber(@Param("seatNumber")String seatNumber, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using numberOfSeats as a search criteria.
	 * 
	 * @param numberOfSeats
	 * @return An Object Bookings whose numberOfSeats is equals to the given numberOfSeats. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.numberOfSeats = :numberOfSeats and e.isDeleted = :isDeleted")
	List<Bookings> findByNumberOfSeats(@Param("numberOfSeats")Integer numberOfSeats, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Bookings whose status is equals to the given status. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.status = :status and e.isDeleted = :isDeleted")
	List<Bookings> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Bookings whose createdAt is equals to the given createdAt. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Bookings> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Bookings whose createdBy is equals to the given createdBy. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Bookings> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Bookings whose updatedAt is equals to the given updatedAt. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Bookings> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Bookings whose updatedBy is equals to the given updatedBy. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Bookings> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Bookings whose isDeleted is equals to the given isDeleted. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.isDeleted = :isDeleted")
	List<Bookings> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Bookings whose deletedAt is equals to the given deletedAt. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Bookings> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Bookings by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Bookings whose deletedBy is equals to the given deletedBy. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Bookings> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using userId as a search criteria.
	 * 
	 * @param userId
	 * @return An Object Bookings whose userId is equals to the given userId. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.users.id = :userId and e.isDeleted = :isDeleted")
	List<Bookings> findByUserId(@Param("userId")Integer userId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using busId as a search criteria.
	 * 
	 * @param busId
	 * @return An Object Bookings whose busId is equals to the given busId. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.buses.id = :busId and e.isDeleted = :isDeleted")
	List<Bookings> findByBusId(@Param("busId")Integer busId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using destinationStationId as a search criteria.
	 * 
	 * @param destinationStationId
	 * @return An Object Bookings whose destinationStationId is equals to the given destinationStationId. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.stations2.id = :destinationStationId and e.isDeleted = :isDeleted")
	List<Bookings> findByDestinationStationId(@Param("destinationStationId")Integer destinationStationId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using departureId as a search criteria.
	 * 
	 * @param departureId
	 * @return An Object Bookings whose departureId is equals to the given departureId. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.departs.id = :departureId and e.isDeleted = :isDeleted")
	List<Bookings> findByDepartureId(@Param("departureId")Integer departureId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using originStationId as a search criteria.
	 * 
	 * @param originStationId
	 * @return An Object Bookings whose originStationId is equals to the given originStationId. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.stations.id = :originStationId and e.isDeleted = :isDeleted")
	List<Bookings> findByOriginStationId(@Param("originStationId")Integer originStationId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Bookings by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object Bookings whose companyId is equals to the given companyId. If
	 *         no Bookings is found, this method returns null.
	 */
	@Query("select e from Bookings e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<Bookings> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Bookings by using bookingsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Bookings
	 * @throws DataAccessException,ParseException
	 */
	default List<Bookings> getByCriteria(Request<BookingsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Bookings e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Bookings> query = em.createQuery(req, Bookings.class);
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
	 * Finds count of Bookings by using bookingsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Bookings
	 * 
	 */
	default Long count(Request<BookingsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Bookings e where e IS NOT NULL";
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
	default String getWhereExpression(Request<BookingsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		BookingsDto dto = request.getData() != null ? request.getData() : new BookingsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (BookingsDto elt : request.getDatas()) {
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
	default String generateCriteria(BookingsDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSeatNumber())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("seatNumber", dto.getSeatNumber(), "e.seatNumber", "String", dto.getSeatNumberParam(), param, index, locale));
			}
			if (dto.getNumberOfSeats()!= null && dto.getNumberOfSeats() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("numberOfSeats", dto.getNumberOfSeats(), "e.numberOfSeats", "Integer", dto.getNumberOfSeatsParam(), param, index, locale));
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
			if (dto.getBusId()!= null && dto.getBusId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("busId", dto.getBusId(), "e.buses.id", "Integer", dto.getBusIdParam(), param, index, locale));
			}
			if (dto.getDestinationStationId()!= null && dto.getDestinationStationId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("destinationStationId", dto.getDestinationStationId(), "e.stations2.id", "Integer", dto.getDestinationStationIdParam(), param, index, locale));
			}
			if (dto.getDepartureId()!= null && dto.getDepartureId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("departureId", dto.getDepartureId(), "e.departs.id", "Integer", dto.getDepartureIdParam(), param, index, locale));
			}
			if (dto.getOriginStationId()!= null && dto.getOriginStationId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("originStationId", dto.getOriginStationId(), "e.stations.id", "Integer", dto.getOriginStationIdParam(), param, index, locale));
			}
			if (dto.getCompanyId()!= null && dto.getCompanyId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companyId", dto.getCompanyId(), "e.companies.id", "Integer", dto.getCompanyIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUsersName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("usersName", dto.getUsersName(), "e.users.name", "String", dto.getUsersNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStationsName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stationsName", dto.getStationsName(), "e.stations2.name", "String", dto.getStationsNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStationsName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stationsName", dto.getStationsName(), "e.stations.name", "String", dto.getStationsNameParam(), param, index, locale));
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

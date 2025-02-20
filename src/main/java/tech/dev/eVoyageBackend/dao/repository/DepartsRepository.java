package tech.dev.eVoyageBackend.dao.repository;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import tech.dev.eVoyageBackend.dao.repository.customize._DepartsRepository;

/**
 * Repository : Departs.
 */
@Repository
public interface DepartsRepository extends JpaRepository<Departs, Integer>, _DepartsRepository {
	/**
	 * Finds Departs by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Departs whose id is equals to the given id. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.id = :id and e.isDeleted = :isDeleted")
	Departs findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Departs by using departureDate as a search criteria.
	 * 
	 * @param departureDate
	 * @return An Object Departs whose departureDate is equals to the given departureDate. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.departureDate = :departureDate and e.isDeleted = :isDeleted")
	List<Departs> findByDepartureDate(@Param("departureDate")Date departureDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using departureTime as a search criteria.
	 * 
	 * @param departureTime
	 * @return An Object Departs whose departureTime is equals to the given departureTime. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.departureTime = :departureTime and e.isDeleted = :isDeleted")
	List<Departs> findByDepartureTime(@Param("departureTime")Date departureTime, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using price as a search criteria.
	 * 
	 * @param price
	 * @return An Object Departs whose price is equals to the given price. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.price = :price and e.isDeleted = :isDeleted")
	List<Departs> findByPrice(@Param("price")Double price, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using maxSeats as a search criteria.
	 * 
	 * @param maxSeats
	 * @return An Object Departs whose maxSeats is equals to the given maxSeats. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.maxSeats = :maxSeats and e.isDeleted = :isDeleted")
	List<Departs> findByMaxSeats(@Param("maxSeats")Integer maxSeats, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using availableSeats as a search criteria.
	 * 
	 * @param availableSeats
	 * @return An Object Departs whose availableSeats is equals to the given availableSeats. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.availableSeats = :availableSeats and e.isDeleted = :isDeleted")
	List<Departs> findByAvailableSeats(@Param("availableSeats")Integer availableSeats, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using isActive as a search criteria.
	 * 
	 * @param isActive
	 * @return An Object Departs whose isActive is equals to the given isActive. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.isActive = :isActive and e.isDeleted = :isDeleted")
	List<Departs> findByIsActive(@Param("isActive")Boolean isActive, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Departs whose createdAt is equals to the given createdAt. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Departs> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Departs whose createdBy is equals to the given createdBy. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Departs> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Departs whose updatedAt is equals to the given updatedAt. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Departs> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Departs whose updatedBy is equals to the given updatedBy. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Departs> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Departs whose isDeleted is equals to the given isDeleted. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.isDeleted = :isDeleted")
	List<Departs> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Departs whose deletedAt is equals to the given deletedAt. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Departs> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Departs by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Departs whose deletedBy is equals to the given deletedBy. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Departs> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Departs by using busId as a search criteria.
	 * 
	 * @param busId
	 * @return An Object Departs whose busId is equals to the given busId. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.buses.id = :busId and e.isDeleted = :isDeleted")
	List<Departs> findByBusId(@Param("busId")Integer busId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Departs by using destinationStationId as a search criteria.
	 * 
	 * @param destinationStationId
	 * @return An Object Departs whose destinationStationId is equals to the given destinationStationId. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.stations2.id = :destinationStationId and e.isDeleted = :isDeleted")
	List<Departs> findByDestinationStationId(@Param("destinationStationId")Integer destinationStationId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Departs by using originStationId as a search criteria.
	 * 
	 * @param originStationId
	 * @return An Object Departs whose originStationId is equals to the given originStationId. If
	 *         no Departs is found, this method returns null.
	 */
	@Query("select e from Departs e where e.stations.id = :originStationId and e.isDeleted = :isDeleted")
	List<Departs> findByOriginStationId(@Param("originStationId")Integer originStationId, @Param("isDeleted")Boolean isDeleted);

	@Query("select e from Departs e where e.buses.companies.name = :companiesName and e.isDeleted = :isDeleted")
	List<Departs> findByCompagniesName(@Param("companiesName")Integer companiesName, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Departs by using departsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Departs
	 * @throws DataAccessException,ParseException
	 */
	default List<Departs> getByCriteria(Request<DepartsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Departs e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Departs> query = em.createQuery(req, Departs.class);
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
	 * Finds count of Departs by using departsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Departs
	 * 
	 */
	default Long count(Request<DepartsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Departs e where e IS NOT NULL";
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
	default String getWhereExpression(Request<DepartsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		DepartsDto dto = request.getData() != null ? request.getData() : new DepartsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (DepartsDto elt : request.getDatas()) {
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
	default String generateCriteria(DepartsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getCompaniesId()!=null && dto.getCompaniesId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companiesId", dto.getCompaniesId(), "e.buses.companies.id", "Integer", dto.getCompaniesIdParam(), param, index, locale));
			}
			if (dto.getDepartureDate()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("departureDate", dto.getDepartureDate(), "e.departureDate", "String", dto.getDepartureDateParam(), param, index, locale));
			}
			if (dto.getDepartureTime()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("departureTime", dto.getDepartureTime(), "e.departureTime", "String", dto.getDepartureTimeParam(), param, index, locale));
			}
			if (dto.getPrice()!= null && dto.getPrice() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("price", dto.getPrice(), "e.price", "Double", dto.getPriceParam(), param, index, locale));
			}
			if (dto.getMaxSeats()!= null && dto.getMaxSeats() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("maxSeats", dto.getMaxSeats(), "e.maxSeats", "Integer", dto.getMaxSeatsParam(), param, index, locale));
			}
			if (dto.getAvailableSeats()!= null && dto.getAvailableSeats() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("availableSeats", dto.getAvailableSeats(), "e.availableSeats", "Integer", dto.getAvailableSeatsParam(), param, index, locale));
			}
			if (dto.getIsActive()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
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
			if (dto.getBusId()!= null && dto.getBusId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("busId", dto.getBusId(), "e.buses.id", "Integer", dto.getBusIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCompaniesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companiesName", dto.getCompaniesName(), "e.buses.companies.name", "String", dto.getCompaniesNameParam(), param, index, locale));
			}
			if (dto.getDestinationStationId()!= null && dto.getDestinationStationId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("destinationStationId", dto.getDestinationStationId(), "e.stations2.id", "Integer", dto.getDestinationStationIdParam(), param, index, locale));
			}
			if (dto.getOriginStationId()!= null && dto.getOriginStationId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("originStationId", dto.getOriginStationId(), "e.stations.id", "Integer", dto.getOriginStationIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStationsNameDeparture())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stationsName", dto.getStationsNameDeparture(), "e.stations.name", "String", dto.getStationsNameDepartureParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getVilleDepart()) || Utilities.notBlank(dto.getVilleArrivee())) {
				if (Utilities.notBlank(dto.getVilleDepart())) {
					listOfQuery.add(CriteriaUtils.generateCriteria(
							"villeDepart",
							dto.getVilleDepart(),
							"e.stations.cities.name",
							"String",
							dto.getVilleDepartParam(),
							param,
							index,
							locale
					));
				}
				if (Utilities.notBlank(dto.getVilleArrivee())) {
					listOfQuery.add(CriteriaUtils.generateCriteria(
							"villeArrivee",
							dto.getVilleArrivee(),
							"e.stations2.cities.name",
							"String",
							dto.getVilleArriveeParam(),
							param,
							index,
							locale
					));
				}
			}


			if (Utilities.notBlank(dto.getStationsNameArrival())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("stationsName", dto.getStationsNameArrival(), "e.stations2.name", "String", dto.getStationsNameArrivalParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

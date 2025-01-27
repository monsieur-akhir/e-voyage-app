package tech.synelia.eVoyageBackend.dao.repository;

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

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.customize._TripTrackingRepository;

/**
 * Repository : TripTracking.
 */
@Repository
public interface TripTrackingRepository extends JpaRepository<TripTracking, Integer>, _TripTrackingRepository {
	/**
	 * Finds TripTracking by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object TripTracking whose id is equals to the given id. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.id = :id and e.isDeleted = :isDeleted")
	TripTracking findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds TripTracking by using location as a search criteria.
	 * 
	 * @param location
	 * @return An Object TripTracking whose location is equals to the given location. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.location = :location and e.isDeleted = :isDeleted")
	List<TripTracking> findByLocation(@Param("location")String location, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using timestamp as a search criteria.
	 * 
	 * @param timestamp
	 * @return An Object TripTracking whose timestamp is equals to the given timestamp. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.timestamp = :timestamp and e.isDeleted = :isDeleted")
	List<TripTracking> findByTimestamp(@Param("timestamp")Date timestamp, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object TripTracking whose createdAt is equals to the given createdAt. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<TripTracking> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object TripTracking whose createdBy is equals to the given createdBy. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<TripTracking> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object TripTracking whose updatedAt is equals to the given updatedAt. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<TripTracking> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object TripTracking whose updatedBy is equals to the given updatedBy. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<TripTracking> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object TripTracking whose isDeleted is equals to the given isDeleted. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.isDeleted = :isDeleted")
	List<TripTracking> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object TripTracking whose deletedAt is equals to the given deletedAt. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<TripTracking> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TripTracking by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object TripTracking whose deletedBy is equals to the given deletedBy. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<TripTracking> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds TripTracking by using routeId as a search criteria.
	 * 
	 * @param routeId
	 * @return An Object TripTracking whose routeId is equals to the given routeId. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.routes.id = :routeId and e.isDeleted = :isDeleted")
	List<TripTracking> findByRouteId(@Param("routeId")Integer routeId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of TripTracking by using tripTrackingDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of TripTracking
	 * @throws DataAccessException,ParseException
	 */
	default List<TripTracking> getByCriteria(Request<TripTrackingDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from TripTracking e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<TripTracking> query = em.createQuery(req, TripTracking.class);
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
	 * Finds count of TripTracking by using tripTrackingDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of TripTracking
	 * 
	 */
	default Long count(Request<TripTrackingDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from TripTracking e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TripTrackingDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		TripTrackingDto dto = request.getData() != null ? request.getData() : new TripTrackingDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TripTrackingDto elt : request.getDatas()) {
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
	default String generateCriteria(TripTrackingDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLocation())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("location", dto.getLocation(), "e.location", "String", dto.getLocationParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTimestamp())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("timestamp", dto.getTimestamp(), "e.timestamp", "Date", dto.getTimestampParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

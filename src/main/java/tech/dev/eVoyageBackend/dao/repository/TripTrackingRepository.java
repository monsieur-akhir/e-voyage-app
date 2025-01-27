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
import tech.dev.eVoyageBackend.dao.repository.customize._TripTrackingRepository;

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
	@Query("select e from TripTracking e where e.id = :id")
	TripTracking findOne(@Param("id")Integer id);

	/**
	 * Finds TripTracking by using location as a search criteria.
	 * 
	 * @param location
	 * @return An Object TripTracking whose location is equals to the given location. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.location = :location")
	List<TripTracking> findByLocation(@Param("location")String location);
	/**
	 * Finds TripTracking by using timestamp as a search criteria.
	 * 
	 * @param timestamp
	 * @return An Object TripTracking whose timestamp is equals to the given timestamp. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.timestamp = :timestamp")
	List<TripTracking> findByTimestamp(@Param("timestamp")Date timestamp);
	/**
	 * Finds TripTracking by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object TripTracking whose status is equals to the given status. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.status = :status")
	List<TripTracking> findByStatus(@Param("status")String status);
	/**
	 * Finds TripTracking by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object TripTracking whose createdAt is equals to the given createdAt. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.createdAt = :createdAt")
	List<TripTracking> findByCreatedAt(@Param("createdAt")Date createdAt);
	/**
	 * Finds TripTracking by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object TripTracking whose createdBy is equals to the given createdBy. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.createdBy = :createdBy")
	List<TripTracking> findByCreatedBy(@Param("createdBy")Integer createdBy);
	/**
	 * Finds TripTracking by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object TripTracking whose updatedAt is equals to the given updatedAt. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.updatedAt = :updatedAt")
	List<TripTracking> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
	/**
	 * Finds TripTracking by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object TripTracking whose updatedBy is equals to the given updatedBy. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.updatedBy = :updatedBy")
	List<TripTracking> findByUpdatedBy(@Param("updatedBy")Integer updatedBy);

	/**
	 * Finds TripTracking by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object TripTracking whose companyId is equals to the given companyId. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.companies.id = :companyId")
	List<TripTracking> findByCompanyId(@Param("companyId")Integer companyId);

	/**
	 * Finds TripTracking by using departId as a search criteria.
	 * 
	 * @param departId
	 * @return An Object TripTracking whose departId is equals to the given departId. If
	 *         no TripTracking is found, this method returns null.
	 */
	@Query("select e from TripTracking e where e.departs.id = :departId")
	List<TripTracking> findByDepartId(@Param("departId")Integer departId);

	/**
	 * Finds List of TripTracking by using tripTrackingDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of TripTracking
	 * @throws DataAccessException,ParseException
	 */
	default List<TripTracking> getByCriteria(Request<TripTrackingDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from TripTracking e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<TripTracking> query = em.createQuery(req, TripTracking.class);
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
	 * Finds count of TripTracking by using tripTrackingDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of TripTracking
	 * 
	 */
	default Long count(Request<TripTrackingDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from TripTracking e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TripTrackingDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		TripTrackingDto dto = request.getData() != null ? request.getData() : new TripTrackingDto();
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TripTrackingDto elt : request.getDatas()) {
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
	default String generateCriteria(TripTrackingDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
			if (dto.getCompanyId()!= null && dto.getCompanyId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companyId", dto.getCompanyId(), "e.companies.id", "Integer", dto.getCompanyIdParam(), param, index, locale));
			}
			if (dto.getDepartId()!= null && dto.getDepartId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("departId", dto.getDepartId(), "e.departs.id", "Integer", dto.getDepartIdParam(), param, index, locale));
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

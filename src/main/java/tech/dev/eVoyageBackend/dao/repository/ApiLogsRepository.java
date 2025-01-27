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

import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.customize._ApiLogsRepository;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;

/**
 * Repository : ApiLogs.
 */
@Repository
public interface ApiLogsRepository extends JpaRepository<ApiLogs, Long>, _ApiLogsRepository {
	/**
	 * Finds ApiLogs by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object ApiLogs whose id is equals to the given id. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.id = :id")
	ApiLogs findOne(@Param("id")Long id);

	/**
	 * Finds ApiLogs by using requestTime as a search criteria.
	 * 
	 * @param requestTime
	 * @return An Object ApiLogs whose requestTime is equals to the given requestTime. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.requestTime = :requestTime")
	List<ApiLogs> findByRequestTime(@Param("requestTime")Date requestTime);
	/**
	 * Finds ApiLogs by using requestUrl as a search criteria.
	 * 
	 * @param requestUrl
	 * @return An Object ApiLogs whose requestUrl is equals to the given requestUrl. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.requestUrl = :requestUrl")
	List<ApiLogs> findByRequestUrl(@Param("requestUrl")String requestUrl);
	/**
	 * Finds ApiLogs by using requestMethod as a search criteria.
	 * 
	 * @param requestMethod
	 * @return An Object ApiLogs whose requestMethod is equals to the given requestMethod. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.requestMethod = :requestMethod")
	List<ApiLogs> findByRequestMethod(@Param("requestMethod")String requestMethod);
	/**
	 * Finds ApiLogs by using requestHeaders as a search criteria.
	 * 
	 * @param requestHeaders
	 * @return An Object ApiLogs whose requestHeaders is equals to the given requestHeaders. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.requestHeaders = :requestHeaders")
	List<ApiLogs> findByRequestHeaders(@Param("requestHeaders")String requestHeaders);
	/**
	 * Finds ApiLogs by using requestBody as a search criteria.
	 * 
	 * @param requestBody
	 * @return An Object ApiLogs whose requestBody is equals to the given requestBody. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.requestBody = :requestBody")
	List<ApiLogs> findByRequestBody(@Param("requestBody")String requestBody);
	/**
	 * Finds ApiLogs by using responseTime as a search criteria.
	 * 
	 * @param responseTime
	 * @return An Object ApiLogs whose responseTime is equals to the given responseTime. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.responseTime = :responseTime")
	List<ApiLogs> findByResponseTime(@Param("responseTime")Date responseTime);
	/**
	 * Finds ApiLogs by using responseStatus as a search criteria.
	 * 
	 * @param responseStatus
	 * @return An Object ApiLogs whose responseStatus is equals to the given responseStatus. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.responseStatus = :responseStatus")
	List<ApiLogs> findByResponseStatus(@Param("responseStatus")Integer responseStatus);
	/**
	 * Finds ApiLogs by using responseBody as a search criteria.
	 * 
	 * @param responseBody
	 * @return An Object ApiLogs whose responseBody is equals to the given responseBody. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.responseBody = :responseBody")
	List<ApiLogs> findByResponseBody(@Param("responseBody")String responseBody);
	/**
	 * Finds ApiLogs by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object ApiLogs whose createdBy is equals to the given createdBy. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.createdBy = :createdBy")
	List<ApiLogs> findByCreatedBy(@Param("createdBy")String createdBy);
	/**
	 * Finds ApiLogs by using createdDate as a search criteria.
	 * 
	 * @param createdDate
	 * @return An Object ApiLogs whose createdDate is equals to the given createdDate. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.createdDate = :createdDate")
	List<ApiLogs> findByCreatedDate(@Param("createdDate")Date createdDate);
	/**
	 * Finds ApiLogs by using lastModifiedBy as a search criteria.
	 * 
	 * @param lastModifiedBy
	 * @return An Object ApiLogs whose lastModifiedBy is equals to the given lastModifiedBy. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.lastModifiedBy = :lastModifiedBy")
	List<ApiLogs> findByLastModifiedBy(@Param("lastModifiedBy")String lastModifiedBy);
	/**
	 * Finds ApiLogs by using lastModifiedDate as a search criteria.
	 * 
	 * @param lastModifiedDate
	 * @return An Object ApiLogs whose lastModifiedDate is equals to the given lastModifiedDate. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.lastModifiedDate = :lastModifiedDate")
	List<ApiLogs> findByLastModifiedDate(@Param("lastModifiedDate")Date lastModifiedDate);
	/**
	 * Finds ApiLogs by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object ApiLogs whose status is equals to the given status. If
	 *         no ApiLogs is found, this method returns null.
	 */
	@Query("select e from ApiLogs e where e.status = :status")
	List<ApiLogs> findByStatus(@Param("status")String status);

	/**
	 * Finds List of ApiLogs by using apiLogsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of ApiLogs
	 * @throws DataAccessException,ParseException
	 */
	default List<ApiLogs> getByCriteria(Request<ApiLogsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from ApiLogs e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<ApiLogs> query = em.createQuery(req, ApiLogs.class);
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
	 * Finds count of ApiLogs by using apiLogsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of ApiLogs
	 * 
	 */
	default Long count(Request<ApiLogsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from ApiLogs e where e IS NOT NULL";
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
	default String getWhereExpression(Request<ApiLogsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		ApiLogsDto dto = request.getData() != null ? request.getData() : new ApiLogsDto();
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ApiLogsDto elt : request.getDatas()) {
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
	default String generateCriteria(ApiLogsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Long", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRequestTime())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("requestTime", dto.getRequestTime(), "e.requestTime", "Date", dto.getRequestTimeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRequestUrl())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("requestUrl", dto.getRequestUrl(), "e.requestUrl", "String", dto.getRequestUrlParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRequestMethod())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("requestMethod", dto.getRequestMethod(), "e.requestMethod", "String", dto.getRequestMethodParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRequestHeaders())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("requestHeaders", dto.getRequestHeaders(), "e.requestHeaders", "String", dto.getRequestHeadersParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRequestBody())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("requestBody", dto.getRequestBody(), "e.requestBody", "String", dto.getRequestBodyParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getResponseTime())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("responseTime", dto.getResponseTime(), "e.responseTime", "Date", dto.getResponseTimeParam(), param, index, locale));
			}
			if (dto.getResponseStatus()!= null && dto.getResponseStatus() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("responseStatus", dto.getResponseStatus(), "e.responseStatus", "Integer", dto.getResponseStatusParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getResponseBody())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("responseBody", dto.getResponseBody(), "e.responseBody", "String", dto.getResponseBodyParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedBy())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "String", dto.getCreatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdDate", dto.getCreatedDate(), "e.createdDate", "Date", dto.getCreatedDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastModifiedBy())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastModifiedBy", dto.getLastModifiedBy(), "e.lastModifiedBy", "String", dto.getLastModifiedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLastModifiedDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastModifiedDate", dto.getLastModifiedDate(), "e.lastModifiedDate", "Date", dto.getLastModifiedDateParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatus())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

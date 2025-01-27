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
import tech.synelia.eVoyageBackend.dao.repository.customize._FinancialReportsRepository;

/**
 * Repository : FinancialReports.
 */
@Repository
public interface FinancialReportsRepository extends JpaRepository<FinancialReports, Integer>, _FinancialReportsRepository {
	/**
	 * Finds FinancialReports by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object FinancialReports whose id is equals to the given id. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.id = :id and e.isDeleted = :isDeleted")
	FinancialReports findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds FinancialReports by using totalRevenue as a search criteria.
	 * 
	 * @param totalRevenue
	 * @return An Object FinancialReports whose totalRevenue is equals to the given totalRevenue. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.totalRevenue = :totalRevenue and e.isDeleted = :isDeleted")
	List<FinancialReports> findByTotalRevenue(@Param("totalRevenue")Double totalRevenue, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using totalBookings as a search criteria.
	 * 
	 * @param totalBookings
	 * @return An Object FinancialReports whose totalBookings is equals to the given totalBookings. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.totalBookings = :totalBookings and e.isDeleted = :isDeleted")
	List<FinancialReports> findByTotalBookings(@Param("totalBookings")Integer totalBookings, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using reportDate as a search criteria.
	 * 
	 * @param reportDate
	 * @return An Object FinancialReports whose reportDate is equals to the given reportDate. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.reportDate = :reportDate and e.isDeleted = :isDeleted")
	List<FinancialReports> findByReportDate(@Param("reportDate")Date reportDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object FinancialReports whose createdAt is equals to the given createdAt. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<FinancialReports> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object FinancialReports whose createdBy is equals to the given createdBy. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<FinancialReports> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object FinancialReports whose updatedAt is equals to the given updatedAt. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<FinancialReports> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object FinancialReports whose updatedBy is equals to the given updatedBy. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<FinancialReports> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object FinancialReports whose isDeleted is equals to the given isDeleted. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.isDeleted = :isDeleted")
	List<FinancialReports> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object FinancialReports whose deletedAt is equals to the given deletedAt. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<FinancialReports> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds FinancialReports by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object FinancialReports whose deletedBy is equals to the given deletedBy. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<FinancialReports> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds FinancialReports by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object FinancialReports whose companyId is equals to the given companyId. If
	 *         no FinancialReports is found, this method returns null.
	 */
	@Query("select e from FinancialReports e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<FinancialReports> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of FinancialReports by using financialReportsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of FinancialReports
	 * @throws DataAccessException,ParseException
	 */
	default List<FinancialReports> getByCriteria(Request<FinancialReportsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from FinancialReports e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<FinancialReports> query = em.createQuery(req, FinancialReports.class);
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
	 * Finds count of FinancialReports by using financialReportsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of FinancialReports
	 * 
	 */
	default Long count(Request<FinancialReportsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from FinancialReports e where e IS NOT NULL";
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
	default String getWhereExpression(Request<FinancialReportsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		FinancialReportsDto dto = request.getData() != null ? request.getData() : new FinancialReportsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (FinancialReportsDto elt : request.getDatas()) {
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
	default String generateCriteria(FinancialReportsDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getTotalRevenue()!= null && dto.getTotalRevenue() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("totalRevenue", dto.getTotalRevenue(), "e.totalRevenue", "Double", dto.getTotalRevenueParam(), param, index, locale));
			}
			if (dto.getTotalBookings()!= null && dto.getTotalBookings() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("totalBookings", dto.getTotalBookings(), "e.totalBookings", "Integer", dto.getTotalBookingsParam(), param, index, locale));
			}
			if (dto.getReportDate()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("reportDate", dto.getReportDate(), "e.reportDate", "Date", dto.getReportDateParam(), param, index, locale));
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

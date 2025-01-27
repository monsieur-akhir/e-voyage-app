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
import tech.dev.eVoyageBackend.dao.repository.customize._CompaniesRepository;

/**
 * Repository : Companies.
 */
@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer>, _CompaniesRepository {
	/**
	 * Finds Companies by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Companies whose id is equals to the given id. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.id = :id and e.isDeleted = :isDeleted")
	Companies findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Companies by using name as a search criteria.
	 * 
	 * @param name
	 * @return An Object Companies whose name is equals to the given name. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.name = :name and e.isDeleted = :isDeleted")
	Companies findByName(@Param("name")String name, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using address as a search criteria.
	 * 
	 * @param address
	 * @return An Object Companies whose address is equals to the given address. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.address = :address and e.isDeleted = :isDeleted")
	List<Companies> findByAddress(@Param("address")String address, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using contact as a search criteria.
	 * 
	 * @param contact
	 * @return An Object Companies whose contact is equals to the given contact. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.contact = :contact and e.isDeleted = :isDeleted")
	List<Companies> findByContact(@Param("contact")String contact, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using licenseNumber as a search criteria.
	 * 
	 * @param licenseNumber
	 * @return An Object Companies whose licenseNumber is equals to the given licenseNumber. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.licenseNumber = :licenseNumber and e.isDeleted = :isDeleted")
	List<Companies> findByLicenseNumber(@Param("licenseNumber")String licenseNumber, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using rating as a search criteria.
	 * 
	 * @param rating
	 * @return An Object Companies whose rating is equals to the given rating. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.rating = :rating and e.isDeleted = :isDeleted")
	List<Companies> findByRating(@Param("rating")Double rating, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Companies whose status is equals to the given status. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.status = :status and e.isDeleted = :isDeleted")
	List<Companies> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Companies whose createdAt is equals to the given createdAt. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Companies> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Companies whose createdBy is equals to the given createdBy. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Companies> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Companies whose updatedAt is equals to the given updatedAt. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Companies> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Companies whose updatedBy is equals to the given updatedBy. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Companies> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Companies whose isDeleted is equals to the given isDeleted. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.isDeleted = :isDeleted")
	List<Companies> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Companies whose deletedAt is equals to the given deletedAt. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Companies> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Companies by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Companies whose deletedBy is equals to the given deletedBy. If
	 *         no Companies is found, this method returns null.
	 */
	@Query("select e from Companies e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Companies> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Companies by using companiesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Companies
	 * @throws DataAccessException,ParseException
	 */
	default List<Companies> getByCriteria(Request<CompaniesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Companies e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Companies> query = em.createQuery(req, Companies.class);
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
	 * Finds count of Companies by using companiesDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Companies
	 * 
	 */
	default Long count(Request<CompaniesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Companies e where e IS NOT NULL";
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
	default String getWhereExpression(Request<CompaniesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		CompaniesDto dto = request.getData() != null ? request.getData() : new CompaniesDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (CompaniesDto elt : request.getDatas()) {
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
	default String generateCriteria(CompaniesDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("name", dto.getName(), "e.name", "String", dto.getNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAddress())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("address", dto.getAddress(), "e.address", "String", dto.getAddressParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getContact())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("contact", dto.getContact(), "e.contact", "String", dto.getContactParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLicenseNumber())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("licenseNumber", dto.getLicenseNumber(), "e.licenseNumber", "String", dto.getLicenseNumberParam(), param, index, locale));
			}
			if (dto.getRating()!= null && dto.getRating() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("rating", dto.getRating(), "e.rating", "Double", dto.getRatingParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

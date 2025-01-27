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
import tech.dev.eVoyageBackend.dao.repository.customize._StationsRepository;

/**
 * Repository : Stations.
 */
@Repository
public interface StationsRepository extends JpaRepository<Stations, Integer>, _StationsRepository {
	/**
	 * Finds Stations by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Stations whose id is equals to the given id. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.id = :id and e.isDeleted = :isDeleted")
	Stations findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Stations by using name as a search criteria.
	 * 
	 * @param name
	 * @return An Object Stations whose name is equals to the given name. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.name = :name and e.isDeleted = :isDeleted")
	Stations findByName(@Param("name")String name, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using address as a search criteria.
	 * 
	 * @param address
	 * @return An Object Stations whose address is equals to the given address. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.address = :address and e.isDeleted = :isDeleted")
	List<Stations> findByAddress(@Param("address")String address, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using isAvailable as a search criteria.
	 * 
	 * @param isAvailable
	 * @return An Object Stations whose isAvailable is equals to the given isAvailable. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.isAvailable = :isAvailable and e.isDeleted = :isDeleted")
	List<Stations> findByIsAvailable(@Param("isAvailable")Boolean isAvailable, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Stations whose createdAt is equals to the given createdAt. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Stations> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Stations whose createdBy is equals to the given createdBy. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Stations> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Stations whose updatedAt is equals to the given updatedAt. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Stations> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Stations whose updatedBy is equals to the given updatedBy. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Stations> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Stations whose isDeleted is equals to the given isDeleted. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.isDeleted = :isDeleted")
	List<Stations> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Stations whose deletedAt is equals to the given deletedAt. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Stations> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Stations by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Stations whose deletedBy is equals to the given deletedBy. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Stations> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Stations by using companyId as a search criteria.
	 * 
	 * @param companyId
	 * @return An Object Stations whose companyId is equals to the given companyId. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.companies.id = :companyId and e.isDeleted = :isDeleted")
	List<Stations> findByCompanyId(@Param("companyId")Integer companyId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Stations by using cityId as a search criteria.
	 * 
	 * @param cityId
	 * @return An Object Stations whose cityId is equals to the given cityId. If
	 *         no Stations is found, this method returns null.
	 */
	@Query("select e from Stations e where e.cities.id = :cityId and e.isDeleted = :isDeleted")
	List<Stations> findByCityId(@Param("cityId")Integer cityId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Stations by using stationsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Stations
	 * @throws DataAccessException,ParseException
	 */
	default List<Stations> getByCriteria(Request<StationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Stations e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Stations> query = em.createQuery(req, Stations.class);
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
	 * Finds count of Stations by using stationsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Stations
	 * 
	 */
	default Long count(Request<StationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Stations e where e IS NOT NULL";
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
	default String getWhereExpression(Request<StationsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		StationsDto dto = request.getData() != null ? request.getData() : new StationsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (StationsDto elt : request.getDatas()) {
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
	default String generateCriteria(StationsDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
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
			if (dto.getIsAvailable()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isAvailable", dto.getIsAvailable(), "e.isAvailable", "Boolean", dto.getIsAvailableParam(), param, index, locale));
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
			if (dto.getCityId()!= null && dto.getCityId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("cityId", dto.getCityId(), "e.cities.id", "Integer", dto.getCityIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCompaniesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("companiesName", dto.getCompaniesName(), "e.companies.name", "String", dto.getCompaniesNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCitiesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("citiesName", dto.getCitiesName(), "e.cities.name", "String", dto.getCitiesNameParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

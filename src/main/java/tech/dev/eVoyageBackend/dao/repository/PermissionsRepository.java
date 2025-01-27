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
import tech.dev.eVoyageBackend.dao.repository.customize._PermissionsRepository;

/**
 * Repository : Permissions.
 */
@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Integer>, _PermissionsRepository {
	/**
	 * Finds Permissions by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Permissions whose id is equals to the given id. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.id = :id")
	Permissions findOne(@Param("id")Integer id);

	/**
	 * Finds Permissions by using name as a search criteria.
	 * 
	 * @param name
	 * @return An Object Permissions whose name is equals to the given name. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.name = :name")
	Permissions findByName(@Param("name")String name);
	/**
	 * Finds Permissions by using description as a search criteria.
	 * 
	 * @param description
	 * @return An Object Permissions whose description is equals to the given description. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.description = :description")
	List<Permissions> findByDescription(@Param("description")String description);
	/**
	 * Finds Permissions by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Permissions whose createdAt is equals to the given createdAt. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.createdAt = :createdAt")
	List<Permissions> findByCreatedAt(@Param("createdAt")Date createdAt);
	/**
	 * Finds Permissions by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Permissions whose createdBy is equals to the given createdBy. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.createdBy = :createdBy")
	List<Permissions> findByCreatedBy(@Param("createdBy")Integer createdBy);
	/**
	 * Finds Permissions by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Permissions whose updatedAt is equals to the given updatedAt. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.updatedAt = :updatedAt")
	List<Permissions> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
	/**
	 * Finds Permissions by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Permissions whose updatedBy is equals to the given updatedBy. If
	 *         no Permissions is found, this method returns null.
	 */
	@Query("select e from Permissions e where e.updatedBy = :updatedBy")
	List<Permissions> findByUpdatedBy(@Param("updatedBy")Integer updatedBy);

	/**
	 * Finds List of Permissions by using permissionsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Permissions
	 * @throws DataAccessException,ParseException
	 */
	default List<Permissions> getByCriteria(Request<PermissionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Permissions e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Permissions> query = em.createQuery(req, Permissions.class);
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
	 * Finds count of Permissions by using permissionsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Permissions
	 * 
	 */
	default Long count(Request<PermissionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Permissions e where e IS NOT NULL";
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
	default String getWhereExpression(Request<PermissionsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		PermissionsDto dto = request.getData() != null ? request.getData() : new PermissionsDto();
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (PermissionsDto elt : request.getDatas()) {
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
	default String generateCriteria(PermissionsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("name", dto.getName(), "e.name", "String", dto.getNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDescription())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

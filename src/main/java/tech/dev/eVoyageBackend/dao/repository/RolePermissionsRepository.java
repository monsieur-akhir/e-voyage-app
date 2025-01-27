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
import tech.dev.eVoyageBackend.dao.repository.customize._RolePermissionsRepository;

/**
 * Repository : RolePermissions.
 */
@Repository
public interface RolePermissionsRepository extends JpaRepository<RolePermissions, Integer>, _RolePermissionsRepository {
	/**
	 * Finds RolePermissions by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object RolePermissions whose id is equals to the given id. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.id = :id")
	RolePermissions findOne(@Param("id")Integer id);

	/**
	 * Finds RolePermissions by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object RolePermissions whose status is equals to the given status. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.status = :status")
	List<RolePermissions> findByStatus(@Param("status")String status);
	/**
	 * Finds RolePermissions by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object RolePermissions whose createdAt is equals to the given createdAt. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.createdAt = :createdAt")
	List<RolePermissions> findByCreatedAt(@Param("createdAt")Date createdAt);
	/**
	 * Finds RolePermissions by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object RolePermissions whose createdBy is equals to the given createdBy. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.createdBy = :createdBy")
	List<RolePermissions> findByCreatedBy(@Param("createdBy")Integer createdBy);
	/**
	 * Finds RolePermissions by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object RolePermissions whose updatedAt is equals to the given updatedAt. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.updatedAt = :updatedAt")
	List<RolePermissions> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
	/**
	 * Finds RolePermissions by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object RolePermissions whose updatedBy is equals to the given updatedBy. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.updatedBy = :updatedBy")
	List<RolePermissions> findByUpdatedBy(@Param("updatedBy")Integer updatedBy);

	/**
	 * Finds RolePermissions by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object RolePermissions whose roleId is equals to the given roleId. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.roles.id = :roleId")
	List<RolePermissions> findByRoleId(@Param("roleId")Integer roleId);

	/**
	 * Finds RolePermissions by using permissionId as a search criteria.
	 * 
	 * @param permissionId
	 * @return An Object RolePermissions whose permissionId is equals to the given permissionId. If
	 *         no RolePermissions is found, this method returns null.
	 */
	@Query("select e from RolePermissions e where e.permissions.id = :permissionId")
	List<RolePermissions> findByPermissionId(@Param("permissionId")Integer permissionId);

	/**
	 * Finds List of RolePermissions by using rolePermissionsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of RolePermissions
	 * @throws DataAccessException,ParseException
	 */
	default List<RolePermissions> getByCriteria(Request<RolePermissionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from RolePermissions e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<RolePermissions> query = em.createQuery(req, RolePermissions.class);
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
	 * Finds count of RolePermissions by using rolePermissionsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of RolePermissions
	 * 
	 */
	default Long count(Request<RolePermissionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from RolePermissions e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RolePermissionsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		RolePermissionsDto dto = request.getData() != null ? request.getData() : new RolePermissionsDto();
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RolePermissionsDto elt : request.getDatas()) {
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
	default String generateCriteria(RolePermissionsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
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
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.roles.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (dto.getPermissionId()!= null && dto.getPermissionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("permissionId", dto.getPermissionId(), "e.permissions.id", "Integer", dto.getPermissionIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRolesName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("rolesName", dto.getRolesName(), "e.roles.name", "String", dto.getRolesNameParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPermissionsName())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("permissionsName", dto.getPermissionsName(), "e.permissions.name", "String", dto.getPermissionsNameParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

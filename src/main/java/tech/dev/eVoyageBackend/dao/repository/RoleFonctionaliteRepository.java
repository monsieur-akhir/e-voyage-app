package tech.dev.eVoyageBackend.dao.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.dev.eVoyageBackend.dao.entity.RoleFonctionalite;
import tech.dev.eVoyageBackend.dao.repository.customize._RoleFonctionaliteRepository;
import tech.dev.eVoyageBackend.utils.CriteriaUtils;
import tech.dev.eVoyageBackend.utils.Utilities;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;

/**
 * Repository : RoleFonctionalite.
 */
@Repository
public interface RoleFonctionaliteRepository extends JpaRepository<RoleFonctionalite, Integer>, _RoleFonctionaliteRepository {
	/**
	 * Finds RoleFonctionalite by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object RoleFonctionalite whose id is equals to the given id. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.id = :id and e.isDeleted = :isDeleted")
	RoleFonctionalite findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleFonctionalite by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object RoleFonctionalite whose createdAt is equals to the given createdAt. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionalite by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object RoleFonctionalite whose updatedAt is equals to the given updatedAt. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionalite by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object RoleFonctionalite whose createdBy is equals to the given createdBy. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionalite by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object RoleFonctionalite whose updatedBy is equals to the given updatedBy. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionalite by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object RoleFonctionalite whose isDeleted is equals to the given isDeleted. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleFonctionalite by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object RoleFonctionalite whose roleId is equals to the given roleId. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleFonctionalite by using fonctionnaliteId as a search criteria.
	 * 
	 * @param fonctionnaliteId
	 * @return An Object RoleFonctionalite whose fonctionnaliteId is equals to the given fonctionnaliteId. If
	 *         no RoleFonctionalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionalite e where e.fonctionalite.id = :fonctionnaliteId and e.isDeleted = :isDeleted")
	List<RoleFonctionalite> findByFonctionnaliteId(@Param("fonctionnaliteId")Integer fonctionnaliteId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of RoleFonctionalite by using roleFonctionaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of RoleFonctionalite
	 * @throws DataAccessException,ParseException
	 */
	default List<RoleFonctionalite> getByCriteria(Request<RoleFonctionaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from RoleFonctionalite e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<RoleFonctionalite> query = em.createQuery(req, RoleFonctionalite.class);
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
	 * Finds count of RoleFonctionalite by using roleFonctionaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of RoleFonctionalite
	 * 
	 */
	default Long count(Request<RoleFonctionaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from RoleFonctionalite e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RoleFonctionaliteDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		RoleFonctionaliteDto dto = request.getData() != null ? request.getData() : new RoleFonctionaliteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RoleFonctionaliteDto elt : request.getDatas()) {
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
	default String generateCriteria(RoleFonctionaliteDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.role.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (dto.getFonctionnaliteId()!= null && dto.getFonctionnaliteId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionnaliteId", dto.getFonctionnaliteId(), "e.fonctionalite.id", "Integer", dto.getFonctionnaliteIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRoleLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleLibelle", dto.getRoleLibelle(), "e.role.libelle", "String", dto.getRoleLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFonctionaliteCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionaliteCode", dto.getFonctionaliteCode(), "e.fonctionalite.code", "String", dto.getFonctionaliteCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getFonctionaliteLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionaliteLibelle", dto.getFonctionaliteLibelle(), "e.fonctionalite.libelle", "String", dto.getFonctionaliteLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

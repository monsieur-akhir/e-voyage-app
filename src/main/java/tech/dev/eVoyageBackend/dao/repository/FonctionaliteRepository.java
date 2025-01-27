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

import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.repository.customize._FonctionaliteRepository;
import tech.dev.eVoyageBackend.utils.CriteriaUtils;
import tech.dev.eVoyageBackend.utils.Utilities;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.dto.FonctionaliteDto;



/**
 * Repository : Fonctionalite.
 */
@Repository
public interface FonctionaliteRepository extends JpaRepository<Fonctionalite, Integer>, _FonctionaliteRepository {
	/**
	 * Finds Fonctionalite by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Fonctionalite whose id is equals to the given id. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.id = :id and e.isDeleted = :isDeleted")
	Fonctionalite findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Fonctionalite by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Fonctionalite whose code is equals to the given code. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.code = :code and e.isDeleted = :isDeleted")
	Fonctionalite findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Fonctionalite whose libelle is equals to the given libelle. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Fonctionalite findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using isAvailableForUser as a search criteria.
	 * 
	 * @param isAvailableForUser
	 * @return An Object Fonctionalite whose isAvailableForUser is equals to the given isAvailableForUser. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.isAvailableForUser = :isAvailableForUser and e.isDeleted = :isDeleted")
	List<Fonctionalite> findByIsAvailableForUser(@Param("isAvailableForUser")String isAvailableForUser, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Fonctionalite whose createdAt is equals to the given createdAt. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Fonctionalite> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Fonctionalite whose createdBy is equals to the given createdBy. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Fonctionalite> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Fonctionalite whose updatedAt is equals to the given updatedAt. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Fonctionalite> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Fonctionalite whose updatedBy is equals to the given updatedBy. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Fonctionalite> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Fonctionalite by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Fonctionalite whose isDeleted is equals to the given isDeleted. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.isDeleted = :isDeleted")
	List<Fonctionalite> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Fonctionalite by using parentId as a search criteria.
	 * 
	 * @param parentId
	 * @return An Object Fonctionalite whose parentId is equals to the given parentId. If
	 *         no Fonctionalite is found, this method returns null.
	 */
	@Query("select e from Fonctionalite e where e.fonctionalite.id = :parentId and e.isDeleted = :isDeleted")
	List<Fonctionalite> findByParentId(@Param("parentId")Integer parentId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Fonctionalite by using fonctionaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Fonctionalite
	 * @throws DataAccessException,ParseException
	 */
	default List<Fonctionalite> getByCriteria(Request<FonctionaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Fonctionalite e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Fonctionalite> query = em.createQuery(req, Fonctionalite.class);
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
	 * Finds count of Fonctionalite by using fonctionaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Fonctionalite
	 * 
	 */
	default Long count(Request<FonctionaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Fonctionalite e where e IS NOT NULL";
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
	default String getWhereExpression(Request<FonctionaliteDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		FonctionaliteDto dto = request.getData() != null ? request.getData() : new FonctionaliteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (FonctionaliteDto elt : request.getDatas()) {
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
	default String generateCriteria(FonctionaliteDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getIsAvailableForUser())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isAvailableForUser", dto.getIsAvailableForUser(), "e.isAvailableForUser", "String", dto.getIsAvailableForUserParam(), param, index, locale));
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
			if (dto.getParentId()!= null && dto.getParentId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentId", dto.getParentId(), "e.fonctionalite.id", "Integer", dto.getParentIdParam(), param, index, locale));
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

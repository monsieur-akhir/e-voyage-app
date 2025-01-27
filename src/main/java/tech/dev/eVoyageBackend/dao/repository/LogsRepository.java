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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.customize._LogsRepository;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;

/**
 * Repository : Logs.
 */
@Repository
public interface LogsRepository extends JpaRepository<Logs, Integer>, _LogsRepository {
	/**
	 * Finds Logs by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Logs whose id is equals to the given id. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.id = :id and e.isDeleted = :isDeleted")
	Logs findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Logs by using actionService as a search criteria.
	 * 
	 * @param actionService
	 * @return An Object Logs whose actionService is equals to the given actionService. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.actionService = :actionService and e.isDeleted = :isDeleted")
	List<Logs> findByActionService(@Param("actionService")String actionService, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Logs whose createdAt is equals to the given createdAt. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Logs> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Logs whose createdBy is equals to the given createdBy. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Logs> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using date as a search criteria.
	 * 
	 * @param date
	 * @return An Object Logs whose date is equals to the given date. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.date = :date and e.isDeleted = :isDeleted")
	List<Logs> findByDate(@Param("date")Date date, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using idStatus as a search criteria.
	 * 
	 * @param idStatus
	 * @return An Object Logs whose idStatus is equals to the given idStatus. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.idStatus = :idStatus and e.isDeleted = :isDeleted")
	List<Logs> findByIdStatus(@Param("idStatus")Integer idStatus, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using ipadress as a search criteria.
	 * 
	 * @param ipadress
	 * @return An Object Logs whose ipadress is equals to the given ipadress. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.ipadress = :ipadress and e.isDeleted = :isDeleted")
	List<Logs> findByIpadress(@Param("ipadress")String ipadress, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using isConnexion as a search criteria.
	 * 
	 * @param isConnexion
	 * @return An Object Logs whose isConnexion is equals to the given isConnexion. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.isConnexion = :isConnexion and e.isDeleted = :isDeleted")
	List<Logs> findByIsConnexion(@Param("isConnexion")Boolean isConnexion, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Logs whose isDeleted is equals to the given isDeleted. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.isDeleted = :isDeleted")
	List<Logs> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using login as a search criteria.
	 * 
	 * @param login
	 * @return An Object Logs whose login is equals to the given login. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.login = :login and e.isDeleted = :isDeleted")
	Logs findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using machine as a search criteria.
	 * 
	 * @param machine
	 * @return An Object Logs whose machine is equals to the given machine. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.machine = :machine and e.isDeleted = :isDeleted")
	List<Logs> findByMachine(@Param("machine")String machine, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Logs whose nom is equals to the given nom. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Logs> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using prenom as a search criteria.
	 * 
	 * @param prenom
	 * @return An Object Logs whose prenom is equals to the given prenom. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.prenom = :prenom and e.isDeleted = :isDeleted")
	List<Logs> findByPrenom(@Param("prenom")String prenom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using request as a search criteria.
	 * 
	 * @param request
	 * @return An Object Logs whose request is equals to the given request. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.request = :request and e.isDeleted = :isDeleted")
	List<Logs> findByRequest(@Param("request")String request, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using response as a search criteria.
	 * 
	 * @param response
	 * @return An Object Logs whose response is equals to the given response. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.response = :response and e.isDeleted = :isDeleted")
	List<Logs> findByResponse(@Param("response")String response, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using searchString as a search criteria.
	 * 
	 * @param searchString
	 * @return An Object Logs whose searchString is equals to the given searchString. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.searchString = :searchString and e.isDeleted = :isDeleted")
	List<Logs> findBySearchString(@Param("searchString")String searchString, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using statusConnection as a search criteria.
	 * 
	 * @param statusConnection
	 * @return An Object Logs whose statusConnection is equals to the given statusConnection. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.statusConnection = :statusConnection and e.isDeleted = :isDeleted")
	List<Logs> findByStatusConnection(@Param("statusConnection")String statusConnection, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Logs whose updatedAt is equals to the given updatedAt. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Logs> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Logs whose updatedBy is equals to the given updatedBy. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Logs> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Logs by using uri as a search criteria.
	 * 
	 * @param uri
	 * @return An Object Logs whose uri is equals to the given uri. If
	 *         no Logs is found, this method returns null.
	 */
	@Query("select e from Logs e where e.uri = :uri and e.isDeleted = :isDeleted")
	List<Logs> findByUri(@Param("uri")String uri, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Logs by using logsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Logs
	 * @throws DataAccessException,ParseException
	 */
	// default List<Logs> getByCriteria(Request<LogsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
	// 	String req = "select e from Logs e where e IS NOT NULL";
	// 	HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
	// 	req += getWhereExpression(request, param, locale);
	// 	req += " order by e.id desc";
	// 	TypedQuery<Logs> query = em.createQuery(req, Logs.class);
	// 	for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
	// 		query.setParameter(entry.getKey(), entry.getValue());
	// 	}
	// 	if (request.getIndex() != null && request.getSize() != null) {
	// 		query.setFirstResult(request.getIndex() * request.getSize());
	// 		query.setMaxResults(request.getSize());
	// 	}
	// 	return query.getResultList();
	// }

	default List<Logs> getByCriteria(Request<LogsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		
		String req = "select e from Logs e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		req += getWhereExpression(request, param, locale);		
		req += " order by e.id desc";		
		TypedQuery<Logs> query = em.createQuery(req, Logs.class);		
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}		
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(Math.min(request.getSize(), 10));
		} else {
			query.setMaxResults(10);
		}		
		return query.getResultList();
	}
	
	/**
	 * Finds count of Logs by using logsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Logs
	 * 
	 */
	default Long count(Request<LogsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Logs e where e IS NOT NULL";
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
	default String getWhereExpression(Request<LogsDto> request, HashMap<String, Object> param, Locale locale) throws Exception {
		// main query
		LogsDto dto = request.getData() != null ? request.getData() : new LogsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (LogsDto elt : request.getDatas()) {
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
	default String generateCriteria(LogsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getActionService())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("actionService", dto.getActionService(), "e.actionService", "String", dto.getActionServiceParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("date", dto.getDate(), "e.date", "Date", dto.getDateParam(), param, index, locale));
			}
			if (dto.getIdStatus()!= null && dto.getIdStatus() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("idStatus", dto.getIdStatus(), "e.idStatus", "Integer", dto.getIdStatusParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getIpadress())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("ipadress", dto.getIpadress(), "e.ipadress", "String", dto.getIpadressParam(), param, index, locale));
			}
			if (dto.getIsConnexion()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isConnexion", dto.getIsConnexion(), "e.isConnexion", "Boolean", dto.getIsConnexionParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLogin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("login", dto.getLogin(), "e.login", "String", dto.getLoginParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMachine())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("machine", dto.getMachine(), "e.machine", "String", dto.getMachineParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("prenom", dto.getPrenom(), "e.prenom", "String", dto.getPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRequest())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("request", dto.getRequest(), "e.request", "String", dto.getRequestParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getResponse())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("response", dto.getResponse(), "e.response", "String", dto.getResponseParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSearchString())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("searchString", dto.getSearchString(), "e.searchString", "String", dto.getSearchStringParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getStatusConnection())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("statusConnection", dto.getStatusConnection(), "e.statusConnection", "String", dto.getStatusConnectionParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUri())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("uri", dto.getUri(), "e.uri", "String", dto.getUriParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}

	@Modifying
    @Transactional
    @Query("DELETE FROM Logs")
    void deleteAllLogs();
}

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
import tech.synelia.eVoyageBackend.dao.repository.customize._PaymentsRepository;

/**
 * Repository : Payments.
 */
@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer>, _PaymentsRepository {
	/**
	 * Finds Payments by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Payments whose id is equals to the given id. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.id = :id and e.isDeleted = :isDeleted")
	Payments findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Payments by using amount as a search criteria.
	 * 
	 * @param amount
	 * @return An Object Payments whose amount is equals to the given amount. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.amount = :amount and e.isDeleted = :isDeleted")
	List<Payments> findByAmount(@Param("amount")Double amount, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using paymentMethod as a search criteria.
	 * 
	 * @param paymentMethod
	 * @return An Object Payments whose paymentMethod is equals to the given paymentMethod. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.paymentMethod = :paymentMethod and e.isDeleted = :isDeleted")
	List<Payments> findByPaymentMethod(@Param("paymentMethod")String paymentMethod, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using status as a search criteria.
	 * 
	 * @param status
	 * @return An Object Payments whose status is equals to the given status. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.status = :status and e.isDeleted = :isDeleted")
	List<Payments> findByStatus(@Param("status")String status, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Payments whose createdAt is equals to the given createdAt. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Payments> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Payments whose createdBy is equals to the given createdBy. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Payments> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Payments whose updatedAt is equals to the given updatedAt. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Payments> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Payments whose updatedBy is equals to the given updatedBy. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Payments> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Payments whose isDeleted is equals to the given isDeleted. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.isDeleted = :isDeleted")
	List<Payments> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Payments whose deletedAt is equals to the given deletedAt. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Payments> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Payments by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Payments whose deletedBy is equals to the given deletedBy. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Payments> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Payments by using bookingId as a search criteria.
	 * 
	 * @param bookingId
	 * @return An Object Payments whose bookingId is equals to the given bookingId. If
	 *         no Payments is found, this method returns null.
	 */
	@Query("select e from Payments e where e.bookings.id = :bookingId and e.isDeleted = :isDeleted")
	List<Payments> findByBookingId(@Param("bookingId")Integer bookingId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Payments by using paymentsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Payments
	 * @throws DataAccessException,ParseException
	 */
	default List<Payments> getByCriteria(Request<PaymentsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Payments e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Payments> query = em.createQuery(req, Payments.class);
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
	 * Finds count of Payments by using paymentsDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Payments
	 * 
	 */
	default Long count(Request<PaymentsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Payments e where e IS NOT NULL";
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
	default String getWhereExpression(Request<PaymentsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		PaymentsDto dto = request.getData() != null ? request.getData() : new PaymentsDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (PaymentsDto elt : request.getDatas()) {
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
	default String generateCriteria(PaymentsDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getAmount()!= null && dto.getAmount() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("amount", dto.getAmount(), "e.amount", "Double", dto.getAmountParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPaymentMethod())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("paymentMethod", dto.getPaymentMethod(), "e.paymentMethod", "String", dto.getPaymentMethodParam(), param, index, locale));
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
			if (dto.getBookingId()!= null && dto.getBookingId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("bookingId", dto.getBookingId(), "e.bookings.id", "Integer", dto.getBookingIdParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}

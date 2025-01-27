package tech.dev.eVoyageBackend.dao.repository.customize;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.dao.entity.*;

/**
 * Repository customize : Departs.
 */
@Repository
public interface _DepartsRepository {
	default List<String> _generateCriteria(DepartsDto dto, HashMap<String, Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

	@Query("SELECT d FROM Departs d " +
			"WHERE d.buses.companies.id = :companyId " +
			"AND d.isDeleted = :isDeleted")
	List<Departs> findByCompanyId(@Param("companyId") Integer companyId, @Param("isDeleted") boolean isDeleted);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT d FROM Departs d WHERE d.id = :id")
	Departs findAndLockById(@Param("id") Integer id);


}

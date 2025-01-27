package tech.dev.eVoyageBackend.dao.repository.customize;

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

/**
 * Repository customize : Users.
 */
@Repository
public interface _UsersRepository {
	default List<String> _generateCriteria(UsersDto dto, HashMap<String, Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

	@Query("SELECT u FROM Users u WHERE (u.email = :emailOrPhone OR u.phone = :emailOrPhone) AND u.isDeleted = false")
	Users findByEmailOrPhone(@Param("emailOrPhone") String emailOrPhone);

	@Query("SELECT u FROM Users u WHERE u.email = :email AND u.isDeleted = :isDeleted")
	List<Users> findByEmail1(@Param("email") String email, @Param("isDeleted") boolean isDeleted);

	@Query("SELECT u FROM Users u WHERE u.phone = :phone AND u.isDeleted = :isDeleted")
	List<Users> findByPhone1(@Param("phone") String phone, @Param("isDeleted") boolean isDeleted);

}

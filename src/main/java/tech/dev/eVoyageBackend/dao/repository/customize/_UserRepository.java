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

import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;

/**
 * Repository customize : User.
 */
@Repository
public interface _UserRepository {
	default List<String> _generateCriteria(UserDto dto, HashMap<String, Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

	@Query("select e from User e where e.otpCode = :otpCode and e.isDeleted = :isDeleted")
	User findByOtpCode(@Param("otpCode")String otpCode, @Param("isDeleted")Boolean isDeleted);
	
	@Query("select e from User e where e.dateSendCodeOtpAt = :dateSendCodeOtpAt and e.isDeleted = :isDeleted")
	User findBydateSendCodeOtpAt(@Param("dateSendCodeOtpAt")Date dateSendCodeOtpAt, @Param("isDeleted")Boolean isDeleted);
	
	@Query("select e from User e where e.loginAttempts = :loginAttempts and e.isDeleted = :isDeleted")
	List<User> findByLoginAttempts(@Param("loginAttempts")Integer loginAttempts, @Param("isDeleted")Boolean isDeleted);
		
	@Query("select e from User e where e.firstConnection = :firstConnection and e.isDeleted = :isDeleted")
	List<User> findByFirstConnection(@Param("firstConnection")Date firstConnection, @Param("isDeleted")Boolean isDeleted);
	
	@Query("select e from User e where e.lastActivityDate = :lastActivityDate and e.isDeleted = :isDeleted")
	List<User> findByLastActivityDate(@Param("lastActivityDate") Date lastActivityDate,@Param("isDeleted") Boolean isDeleted);
}

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
 * Repository customize : RoleFonctionalite.
 */
@Repository
public interface _RoleFonctionaliteRepository {
	default List<String> _generateCriteria(RoleFonctionaliteDto dto, HashMap<String, Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

	@Query("select e.fonctionalite from RoleFonctionalite e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<Fonctionalite> findFonctionnaliteByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);
}

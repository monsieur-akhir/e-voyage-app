package tech.dev.eVoyageBackend.utils;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.stereotype.Component;

import tech.dev.eVoyageBackend.utils.ParamsUtils;
import tech.dev.eVoyageBackend.utils.PersonAttributeMapper;
import tech.dev.eVoyageBackend.utils.dto.UserDto;


@Component
public class LdapService {

	@Autowired
    ParamsUtils paramsUtils;

	private LdapContextSource ldapContextSource;
	private LdapTemplate ldapTemplate;
	private static final Logger log = LoggerFactory.getLogger(LdapService.class);

	public LdapService() {

		HashMap<String, Object> env = new HashMap<String, Object>();
		env.put("com.sun.jndi.ldap.read.timeout", "5000");
		ldapContextSource = new LdapContextSource();

		// Ajoutez 'ldap://' au début de l'URL
		ldapContextSource.setUrl("ldap://10.25.23.242:389");
		ldapContextSource.setBase("OU=Users_Proxy_Pac,OU=Users,OU=OR-CD,DC=orangerdc,DC=cd");
		ldapContextSource.setUserDn("CN=smileuser ad,OU=SpecialUsers,OU=Users,OU=OR-CD,DC=orangerdc,DC=cd");
		ldapContextSource.setPassword("Qw@ssport123");
		ldapContextSource.setBaseEnvironmentProperties(env);
		ldapContextSource.afterPropertiesSet();
		ldapTemplate = new LdapTemplate(ldapContextSource);
	}

	/**
	 * Authenticate a user
	 */

	public boolean authenticateUser(String login, String password) {
		log.info("Debut authenticateUser VIA Ldap");
		boolean t = false;
		try {
			AndFilter andFilter = new AndFilter();
			andFilter.and(new EqualsFilter("sAMAccountName", login));
			t = ldapTemplate.authenticate("", andFilter.encode(), password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Fin authenticateUser VIA Ldap");
		log.info("Response authenticateUser Ldap " + t);
		return t;
	}

	/**
	 * Search for user //
	 **/

	public List<UserDto> getAllUser(String userName) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new LikeFilter("sAMAccountName", userName));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> getUserByLogin(String userName) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new LikeFilter("sAMAccountName", userName));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> getAllUserByNom(String userName) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new LikeFilter("sn", userName));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> getAllUserByPrenom(String givenName) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new LikeFilter("givenName", givenName));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> getAllUserByTelephone(String telephone) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new LikeFilter("telephoneNumber", telephone));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> getAllUserByMatricule(String matricule) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("physicalDeliveryOfficeName", matricule));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> searchUser(String login) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("sAMAccountName", login));

		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

	public List<UserDto> searchUserByMobile(String phone) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("telephoneNumber", phone));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());
	}

	public List<UserDto> searchUserByPrenoms(String prenom) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("givenName", prenom));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());
	}

	public List<UserDto> searchUserByNom(String nom) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("sn", nom));
		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());
	}

	public List<UserDto> searchUserByMatricule(String matricule) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("physicalDeliveryOfficeName", matricule));

		return ldapTemplate.search("", andFilter.encode(), new PersonAttributeMapper());

	}

}
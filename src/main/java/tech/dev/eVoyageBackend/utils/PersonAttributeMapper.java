package tech.dev.eVoyageBackend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;

import tech.dev.eVoyageBackend.utils.dto.UserDto;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;







public class PersonAttributeMapper implements AttributesMapper<UserDto> {
	
	private Logger 		slf4jLogger = LoggerFactory.getLogger(getClass());


	
	@Override
	public UserDto mapFromAttributes(Attributes attrs) throws NamingException {
		UserDto userinfo = new UserDto();
       try {
           userinfo.setFirstName((String)attrs.get("sn").get());
           slf4jLogger.info("************* sn :"+(String)attrs.get("sn").get());
       } catch (Exception e) {
       }

       try {
           userinfo.setLastName((String)attrs.get("givenName").get());
           slf4jLogger.info("************* givenName :"+(String)attrs.get("givenName").get());

       } catch (Exception e) {
       }
       try {
           userinfo.setEmail((String)attrs.get("mail").get());
           slf4jLogger.info("************* mail :"+(String)attrs.get("mail").get());

       } catch (Exception e) {
       }
//       try {
//           userinfo.setCommonName((String)attrs.get("CN").get());
//           slf4jLogger.info("************* CN :"+(String)attrs.get("CN").get());
//
//       } catch (Exception e) {
//       }
//       try {
//           userinfo.setFonction((String)attrs.get("title").get());
//           slf4jLogger.info("************* title :"+(String)attrs.get("title").get());
//
//       } catch (Exception e) {
//       }
//
//       try {
//           userinfo.setDepartment((String)attrs.get("department").get());
//           slf4jLogger.info("************* department :"+(String)attrs.get("department").get());
//
//       } catch (Exception e) {
//       }
//       try {
//           userinfo.setMatricule((String)attrs.get("physicalDeliveryOfficeName").get());
//           slf4jLogger.info("************* physicalDeliveryOfficeName :"+(String)attrs.get("physicalDeliveryOfficeName").get());
//
//       } catch (Exception e) {
//       }
//       
//       try {
//           userinfo.setContact((String)attrs.get("telephoneNumber").get());
//           slf4jLogger.info("************* telephoneNumber :"+(String)attrs.get("telephoneNumber").get());
//
//       } catch (Exception e) {
 //      }
       
       try {
           userinfo.setLogin((String)attrs.get("sAMAccountName").get());
           slf4jLogger.info("************* sAMAccountName :"+(String)attrs.get("sAMAccountName").get());

       } catch (Exception e) {
       }
       
       
       try {
           userinfo.setTelephone((String)attrs.get("mobile").get());
           slf4jLogger.info("************* mobile :"+(String)attrs.get("mobile").get());

       } catch (Exception e) {
       }
       
       
       try {
           userinfo.setTelephone((String)attrs.get("homePhone").get());
           slf4jLogger.info("************* homePhone :"+(String)attrs.get("homePhone").get());

       } catch (Exception e) {
       }       
     
       
       try {
           userinfo.setTelephone((String)attrs.get("pager").get());
           slf4jLogger.info("************* pager :"+(String)attrs.get("pager").get());

       } catch (Exception e) {
       }
       
       
       
    
       
       
       return userinfo;
    }
}

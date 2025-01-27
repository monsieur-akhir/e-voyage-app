                                    								
/*
 * Java business for entity table role
 * Created on 2024-08-20 ( Time 10:24:21 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.RoleFonctionaliteBusiness;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.dto.transformer.*;
import tech.dev.eVoyageBackend.utils.enums.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 BUSINESS for table "role"
 *
 * @author Geo
 *
 */
@Log
@Component
public class RoleBusiness implements IBasicBusiness<Request<RoleDto>, Response<RoleDto>> {

	private Response<RoleDto> response;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FonctionaliteRepository fonctionaliteRepository;
	@Autowired
	private RoleFonctionaliteRepository roleFonctionaliteRepository;
	@Autowired
	private RoleFonctionaliteBusiness roleFonctionaliteBusiness;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public RoleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Role by using RoleDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleDto> create(Request<RoleDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();

		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("datasFonctionalites", dto.getDatasFonctionalites());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if role to insert do not exist
			Role existingEntity = null;
//			if (existingEntity != null) {
//				response.setStatus(functionalError.DATA_EXIST("role id -> " + dto.getId(), locale));
//				response.setHasError(true);
//				return response;
//			}

			// verif unique libelle in db
			existingEntity = roleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("role libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			if(Utilities.isEmpty(dto.getDatasFonctionalites())) {
				response.setStatus(functionalError.FIELD_EMPTY("Veuillez renseigner le ou les fonctionnalités du profil", locale));
				response.setHasError(true);
				return response;
			}

			Role entityToSave = null;
			entityToSave = RoleTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Role entityToSaved = roleRepository.save(entityToSave);
			if (entityToSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}

			List<RoleFonctionaliteDto> datasProfilFunctionality = new ArrayList<RoleFonctionaliteDto>();
			if(Utilities.isNotEmpty(dto.getDatasFonctionalites())) {
				dto.getDatasFonctionalites().forEach(f->{
					RoleFonctionaliteDto roleFunctionalityDto = new RoleFonctionaliteDto();
					roleFunctionalityDto.setRoleId(entityToSaved.getId());
					roleFunctionalityDto.setFonctionnaliteId(f.getId());
					datasProfilFunctionality.add(roleFunctionalityDto);
				});

				Request<RoleFonctionaliteDto> subRequest = new Request<RoleFonctionaliteDto>();
				subRequest.setDatas(datasProfilFunctionality);
				subRequest.setUser(request.getUser());
				Response<RoleFonctionaliteDto> subResponse = roleFonctionaliteBusiness.create(subRequest, locale);
				if(subResponse.isHasError()){
					response.setStatus(subResponse.getStatus());
					response.setHasError(true);
					return response;
				}
			}

			items.add(entityToSave);
		}



		if (!items.isEmpty()) {
			List<Role> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = roleRepository.saveAll((Iterable<Role>) items);
//			if (itemsSaved == null) {
//				response.setStatus(functionalError.SAVE_FAIL("role", locale));
//				response.setHasError(true);
//				return response;
//			}
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setStatus(functionalError.SUCCESS("", locale));
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end create Role-----");
		return response;
	}

	/**
	 * update Role by using RoleDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleDto> update(Request<RoleDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();

		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role entityToSave = null;
			entityToSave = roleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());


			List<RoleFonctionalite> itemsRoleFonc = new ArrayList<>();
			if (Utilities.isNotEmpty(dto.getDatasFonctionalites())) {

				List<RoleFonctionalite> currents = roleFonctionaliteRepository.findByRoleId(entityToSave.getId(), Boolean.FALSE);
				Request<RoleFonctionaliteDto> deleteRequeste = new Request<RoleFonctionaliteDto>();
				if (Utilities.isNotEmpty(currents)) {
					deleteRequeste.setUser(request.getUser());
					deleteRequeste.setDatas(RoleFonctionaliteTransformer.INSTANCE.toDtos(currents));
					Response<RoleFonctionaliteDto> deleteResponse = roleFonctionaliteBusiness.delete(deleteRequeste, locale);
					if (deleteResponse.isHasError()) {
						response.setStatus(deleteResponse.getStatus());
						response.setHasError(true);
						return response;
					}
				}
				if (Utilities.isNotEmpty(dto.getDatasFonctionalites())) {
					for (RoleFonctionaliteDto fonction : dto.getDatasFonctionalites()) {
						RoleFonctionalite newRf = new RoleFonctionalite();
						newRf.setRole(entityToSave);
						Fonctionalite child1 = fonctionaliteRepository.findOne(fonction.getId(), Boolean.FALSE);
						newRf.setFonctionalite(child1);
						newRf.setCreatedAt(Utilities.getCurrentDate());
						newRf.setCreatedBy(request.getUser());
						newRf.setIsDeleted(false);
						itemsRoleFonc.add(newRf);
					}
					roleFonctionaliteRepository.saveAll(itemsRoleFonc);
				}
			}



			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Role> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = roleRepository.saveAll((Iterable<Role>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setStatus(functionalError.SUCCESS("", locale));
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end update Role-----");
		return response;
	}

	/**
	 * delete Role by using RoleDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleDto> delete(Request<RoleDto> request, Locale locale)  {
		log.info("----begin delete Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();

		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role existingEntity = null;
			existingEntity = roleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// user
			List<User> listOfUser = userRepository.findByRoleId(existingEntity.getId(), false);
			if (listOfUser != null && !listOfUser.isEmpty()) {
				String userText = listOfUser.size() == 1 ? " utilisateur" : " utilisateurs";
				response.setStatus(functionalError.DATA_NOT_DELETABLE("" + listOfUser.size() + userText, locale));
				response.setHasError(true);
				return response;
			}

			// roleFonctionalite
//			List<RoleFonctionalite> listOfRoleFonctionalite = roleFonctionaliteRepository.findByRoleId(existingEntity.getId(), false);
//			if (listOfRoleFonctionalite != null && !listOfRoleFonctionalite.isEmpty()){
//				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoleFonctionalite.size() + " fois )", locale));
//				response.setHasError(true);
//				return response;
//			}

			List<RoleFonctionalite> listFonctionnalites = roleFonctionaliteRepository.findByRoleId(existingEntity.getId(), Boolean.FALSE);
			if (Utilities.isNotEmpty(listFonctionnalites)) {
				for (RoleFonctionalite fonction : listFonctionnalites) {
					fonction.setIsDeleted(Boolean.TRUE);
					roleFonctionaliteRepository.save(fonction);
				}
			}



			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			roleRepository.saveAll((Iterable<Role>) items);

			response.setStatus(functionalError.SUCCESS("", locale));
			response.setHasError(false);
		}

		log.info("----end delete Role-----");
		return response;
	}

	/**
	 * get Role by using RoleDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleDto> getByCriteria(Request<RoleDto> request, Locale locale)  throws Exception {
		log.info("----begin get Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role> items 			 = roleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleTransformer.INSTANCE.toLiteDtos(items) : RoleTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setCount(roleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("role", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Role-----");
		return response;
	}

	/**
	 * get full RoleDto by using Role as object.
	 *
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoleDto getFullInfos(RoleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (dto != null){
			List<Fonctionalite> datasFonctionnalite = roleFonctionaliteRepository.findFonctionnaliteByRoleId(dto.getId(), false);
			if(Utilities.isNotEmpty(datasFonctionnalite)) {
				List<FonctionaliteDto>	datasFonctionnaliteDto = null;
				datasFonctionnaliteDto = FonctionaliteTransformer.INSTANCE.toDtos(datasFonctionnalite);
				dto.setDatasFonctionalite(datasFonctionnaliteDto);
				List<Integer> listFonctionnaliteParentInteger = new ArrayList<>();
				List<Fonctionalite> listFonctionnaliteParent = new ArrayList<>();
				for(FonctionaliteDto foncDto : datasFonctionnaliteDto) {
					if (Utilities.isValidID(foncDto.getParentId())) {
						listFonctionnaliteParentInteger.add(foncDto.getParentId());
					}
				}
				//	List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
				List<Integer> distinctElements =	listFonctionnaliteParentInteger.stream().distinct().collect(Collectors.toList());
				for(Integer intee : distinctElements) {
					Fonctionalite fonc = fonctionaliteRepository.findOne(intee, false);
					listFonctionnaliteParent.add(fonc);
				}
//				if(Utilities.isNotEmpty(listFonctionnaliteParent)) {
//					for(FonctionaliteDto foncDto : datasFonctionnaliteDto) {
//						for(FonctionaliteDto foncDto2 : FonctionaliteTransformer.INSTANCE.toDtos(listFonctionnaliteParent)) {
//							if (foncDto != null && foncDto2 != null && foncDto.getId() != null && foncDto.getId().equals(foncDto2.getId())) {
//								List<Fonctionalite> listFonc = fonctionaliteRepository.findByParentId(foncDto2.getId(), false);
//								foncDto.setDatasChildren(FonctionaliteTransformer.INSTANCE.toDtos(listFonc));
//							}
//						}
//					}
//				}
			}
		}


//		List<Fonctionalite> datasFonctionnalite = roleFonctionaliteRepository.findFonctionnaliteByRoleId(dto.getId(), Boolean.FALSE);
//		if (Utilities.isNotEmpty(datasFonctionnalite)) {
//			List<FonctionaliteDto>	datasFonctionnaliteDto = null;
//			datasFonctionnaliteDto = FonctionaliteTransformer.INSTANCE.toDtos(datasFonctionnalite);
//			dto.setDatasFonctionalite(datasFonctionnaliteDto);
//			List<Integer> listFonctionnaliteParentInteger = new ArrayList<>();
//			List<Fonctionalite> listFonctionnaliteParent = new ArrayList<>();
//			for(FonctionaliteDto foncDto : datasFonctionnaliteDto) {
//				if (Utilities.isValidID(foncDto.getParentId())) {
//					listFonctionnaliteParentInteger.add(foncDto.getParentId());
//				}
//			}
//			List<Integer> distinctElements =	listFonctionnaliteParentInteger.stream().distinct().collect(Collectors.toList());
//			for(Integer intee : distinctElements) {
//				Fonctionalite fonc = fonctionaliteRepository.findOne(intee, false);
//				listFonctionnaliteParent.add(fonc);
//			}
//			if(Utilities.isNotEmpty(listFonctionnaliteParent)) {
//				for(FonctionaliteDto foncDto : datasFonctionnaliteDto) {
//					for(FonctionaliteDto foncDto2 : FonctionaliteTransformer.INSTANCE.toDtos(listFonctionnaliteParent)) {
//						if (foncDto != null && foncDto2 != null && foncDto.getId() != null && foncDto.getId().equals(foncDto2.getId())) {
//							List<Fonctionalite> listFonc = fonctionaliteRepository.findByParentId(foncDto2.getId(), false);
//							foncDto.setDatasFonctionaliteChildren(FonctionaliteTransformer.INSTANCE.toDtos(listFonc));
//						}
//					}
//				}
//			}
//		}

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}


//	public void createDefaultRoles() {
//	    log.info("----begin createDefaultRoles-----");
//
//	    Role existingRole = roleRepository.findOne(1, false);
//	    if (existingRole == null) {
//	        Role adminRole = new Role();
//	        adminRole.setId(1); // Assurez-vous que ceci est l'ID unique
//	        adminRole.setLibelle("Admin"); // Libellé du rôle
//	        adminRole.setCreatedAt(Utilities.getCurrentDate());
//	        adminRole.setIsDeleted(Boolean.FALSE);
//
//	        // Enregistrer le rôle
//	        roleRepository.save(adminRole);
//	        log.info("Le rôle administrateur a été créé avec succès.");
//	    } else {
//	        log.info("Le rôle administrateur existe déjà.");
//	    }
//
//	    log.info("----end createDefaultRoles-----");
//	}



}
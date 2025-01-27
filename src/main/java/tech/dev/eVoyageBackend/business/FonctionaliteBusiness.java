                                                											
/*
 * Java business for entity table fonctionalite 
 * Created on 2023-08-30 ( Time 17:27:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

 package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.entity.RoleFonctionalite;
import tech.dev.eVoyageBackend.dao.repository.FonctionaliteRepository;
import tech.dev.eVoyageBackend.dao.repository.RoleFonctionaliteRepository;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.FonctionaliteDto;
import tech.dev.eVoyageBackend.utils.dto.transformer.FonctionaliteTransformer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 BUSINESS for table "fonctionalite"
 *
 * @author Geo
 *
 */
@Log
@Component
public class FonctionaliteBusiness implements IBasicBusiness<Request<FonctionaliteDto>, Response<FonctionaliteDto>> {

	private Response<FonctionaliteDto> response;
	@Autowired
	private FonctionaliteRepository fonctionaliteRepository;
	@Autowired
	private RoleFonctionaliteRepository roleFonctionaliteRepository;
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

	public FonctionaliteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Fonctionalite by using FonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<FonctionaliteDto> create(Request<FonctionaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Fonctionalite-----");

		Response<FonctionaliteDto> response = new Response<FonctionaliteDto>();
		List<Fonctionalite>        items    = new ArrayList<Fonctionalite>();

		for (FonctionaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
//			fieldsToVerify.put("parentId", dto.getParentId());
//			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("libelle", dto.getLibelle());
//			fieldsToVerify.put("isAvailableForUser", dto.getIsAvailableForUser());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}


			// Verify if fonctionnalite to insert do not exist
			Fonctionalite existingEntity = null;
			// verif unique code in db
			dto.setCode("FUNC-" + Utilities.generateAlphanumericCode(4));
			// verif unique libelle in db
			existingEntity = fonctionaliteRepository.findByCode(dto.getCode(), false);
			while (existingEntity != null) {
				dto.setCode("FUNC-" + Utilities.generateAlphanumericCode(4));
			}

//			/// Verify if fonctionnalite to insert do not exist
//			Fonctionalite existingEntity = null;
//			// verif unique code in db
//			dto.setCode("FUNC-" + dto.getLibelle().substring(0,6).toUpperCase());
//			// verif unique libelle in db
//			existingEntity = fonctionaliteRepository.findByCode(dto.getCode(), false);
//			while (existingEntity != null) {
//				dto.setCode("FUNC-" + dto.getLibelle().substring(0,6).toUpperCase());
//			}

			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique libelle in db
			existingEntity = fonctionaliteRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("fonctionalite libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if fonctionalite exist
			Fonctionalite existingFonctionalite = null;
			if (dto.getParentId() != null && dto.getParentId() > 0){
				existingFonctionalite = fonctionaliteRepository.findOne(dto.getParentId(), false);
				if (existingFonctionalite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionalite parentId -> " + dto.getParentId(), locale));
					response.setHasError(true);
					return response;
				}
			}


			// Verify if parentId exist
			Fonctionalite existingParentId = null;
			if (dto.getParentId() != null && dto.getParentId() > 0) {
				existingParentId = fonctionaliteRepository.findOne(dto.getParentId(), false);
				if (existingParentId == null) {
					response.setStatus(
							functionalError.DATA_NOT_EXIST("fonctionnalite id -> " + dto.getParentId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			Fonctionalite entityToSave = null;
			entityToSave = FonctionaliteTransformer.INSTANCE.toEntity(dto, existingFonctionalite);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Fonctionalite> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = fonctionaliteRepository.saveAll((Iterable<Fonctionalite>) items);
//			if (itemsSaved == null) {
//				response.setStatus(functionalError.SAVE_FAIL("fonctionalite", locale));
//				response.setHasError(true);
//				return response;
//			}
			List<FonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : FonctionaliteTransformer.INSTANCE.toDtos(itemsSaved);

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
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end create Fonctionalite-----");
		return response;
	}

	/**
	 * update Fonctionalite by using FonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<FonctionaliteDto> update(Request<FonctionaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Fonctionalite-----");

		Response<FonctionaliteDto> response = new Response<FonctionaliteDto>();
		List<Fonctionalite>        items    = new ArrayList<Fonctionalite>();

		for (FonctionaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la fonctionalite existe
			Fonctionalite entityToSave = null;
			entityToSave = fonctionaliteRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("fonctionalite id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if fonctionalite exist
			if (dto.getParentId() != null && dto.getParentId() > 0){
				Fonctionalite existingFonctionalite = fonctionaliteRepository.findOne(dto.getParentId(), false);
				if (existingFonctionalite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionalite parentId -> " + dto.getParentId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setFonctionalite(existingFonctionalite);
			}
			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getIsAvailableForUser())) {
				entityToSave.setIsAvailableForUser(dto.getIsAvailableForUser());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Fonctionalite> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = fonctionaliteRepository.saveAll((Iterable<Fonctionalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("fonctionalite", locale));
				response.setHasError(true);
				return response;
			}
			List<FonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : FonctionaliteTransformer.INSTANCE.toDtos(itemsSaved);

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
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end update Fonctionalite-----");
		return response;
	}

	/**
	 * delete Fonctionalite by using FonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<FonctionaliteDto> delete(Request<FonctionaliteDto> request, Locale locale)  {
		log.info("----begin delete Fonctionalite-----");

		Response<FonctionaliteDto> response = new Response<FonctionaliteDto>();
		List<Fonctionalite>        items    = new ArrayList<Fonctionalite>();

		for (FonctionaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la fonctionalite existe
			Fonctionalite existingEntity = null;
			existingEntity = fonctionaliteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("fonctionalite -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// fonctionalite
			List<Fonctionalite> listOfFonctionalite = fonctionaliteRepository.findByParentId(existingEntity.getId(), false);
			if (listOfFonctionalite != null && !listOfFonctionalite.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfFonctionalite.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// roleFonctionalite
			List<RoleFonctionalite> listOfRoleFonctionalite = roleFonctionaliteRepository.findByFonctionnaliteId(existingEntity.getId(), false);
			if (listOfRoleFonctionalite != null && !listOfRoleFonctionalite.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoleFonctionalite.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			fonctionaliteRepository.saveAll((Iterable<Fonctionalite>) items);

			response.setHasError(false);
		}

		log.info("----end delete Fonctionalite-----");
		return response;
	}

	/**
	 * get Fonctionalite by using FonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
//	@Override
//	public Response<FonctionaliteDto> getByCriteria(Request<FonctionaliteDto> request, Locale locale)  throws Exception {
//		log.info("----begin get Fonctionalite-----");
//
//		Response<FonctionaliteDto> response = new Response<FonctionaliteDto>();
//		List<Fonctionalite> items 			 = fonctionaliteRepository.getByCriteria(request, em, locale);
//
//		if (items != null && !items.isEmpty()) {
//			List<FonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionaliteTransformer.INSTANCE.toLiteDtos(items) : FonctionaliteTransformer.INSTANCE.toDtos(items);
//
//			final int size = items.size();
//			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
//			itemsDto.parallelStream().forEach(dto -> {
//				try {
//					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
//				} catch (Exception e) {
//					listOfError.add(e.getMessage());
//					e.printStackTrace();
//				}
//			});
//			if (Utilities.isNotEmpty(listOfError)) {
//				Object[] objArray = listOfError.stream().distinct().toArray();
//				throw new RuntimeException(StringUtils.join(objArray, ", "));
//			}
//			response.setItems(itemsDto);
//			response.setCount(fonctionaliteRepository.count(request, em, locale));
//			response.setHasError(false);
//		} else {
//			response.setStatus(functionalError.DATA_EMPTY("fonctionalite", locale));
//			response.setHasError(false);
//			return response;
//		}
//
//		log.info("----end get Fonctionalite-----");
//		return response;
//	}


	@Override
	public Response<FonctionaliteDto> getByCriteria(Request<FonctionaliteDto> request, Locale locale)
			throws Exception {
		log.info("----begin get Fonctionnalite-----");

		Response<FonctionaliteDto> response = new Response<FonctionaliteDto>();
		List<Fonctionalite> items = null;

		items = fonctionaliteRepository.getByCriteria(request, em, locale);

		Integer index = request.getIndex();
		if (items != null && !items.isEmpty()) {
			List<FonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? FonctionaliteTransformer.INSTANCE.toLiteDtos(items)
					: FonctionaliteTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
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

			if (request.getHierarchyFormat() != null && request.getHierarchyFormat()) {
				List<FonctionaliteDto> itemsUnique = hierarchicalFormatting(itemsDto);
				if (Utilities.isNotEmpty(itemsUnique)) {

					itemsUnique.sort((e1, e2) -> e2.getId().compareTo(e1.getId()));

					final int sizeUnique = itemsUnique.size();
					List<FonctionaliteDto> itemsPaginner = Utilities.paginner(itemsUnique, index, size);
					response.setItems(itemsPaginner);
					response.setCount((long) sizeUnique);
					response.setHasError(false);
					return response;
				} else {
					response.setStatus(functionalError.DATA_EMPTY("famille", locale));
					response.setHasError(false);
					return response;
				}
			}

			response.setStatus(functionalError.SUCCESS("", locale));
			response.setItems(itemsDto);
			response.setCount(fonctionaliteRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("fonctionnalite", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Fonctionnalite-----");
		response.setActionEffectue("Vue fonctionnalite");

		return response;
	}

	/**
	 * get full FonctionaliteDto by using Fonctionalite as object.
	 *
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private FonctionaliteDto getFullInfos(FonctionaliteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}


	public static List<FonctionaliteDto> hierarchicalFormatting(List<FonctionaliteDto> itemsFonctionaliteDto) {
		boolean allDone = false;
		List<FonctionaliteDto> singletons = new ArrayList<FonctionaliteDto>();
		while (!allDone) {
			allDone = true;
			List<FonctionaliteDto> productTypesWhithoutChildren = new ArrayList<FonctionaliteDto>();
			for (FonctionaliteDto productType : itemsFonctionaliteDto) {
				boolean hasChildren = false;
				for (FonctionaliteDto otherProductType : itemsFonctionaliteDto) {
					if (productType != otherProductType) {
						if (otherProductType.getParentId() != null
								&& otherProductType.getParentId() == productType.getId()) {
							hasChildren = true;
							allDone = false;
							break;
						}
					}
				}
				if (!hasChildren) {
					productTypesWhithoutChildren.add(productType);
				}
			}
			if (!productTypesWhithoutChildren.isEmpty()) {
				itemsFonctionaliteDto.removeAll(productTypesWhithoutChildren);
				// mettre checque élément sans enfant dans son eventuel parent
				for (FonctionaliteDto productType : productTypesWhithoutChildren) {
					boolean parentFounded = false;
					for (FonctionaliteDto parent : itemsFonctionaliteDto) {
						if (parent.getId() == productType.getParentId()) {
							parentFounded = true;
							List<FonctionaliteDto> children;
							if (parent.getDatasChildren() == null
									|| parent.getDatasChildren().isEmpty())
								children = new ArrayList<FonctionaliteDto>();
							else
								children = parent.getDatasChildren();
							children.add(productType);
							parent.setDatasChildren(children);
							break;
						}
					}
					if (!parentFounded)
						singletons.add(productType);
				}
			}
		}
		return singletons;
	}
}

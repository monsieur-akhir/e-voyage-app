                                                            													
/*
 * Java business for entity table companies 
 * Created on 2025-01-11 ( Time 04:46:00 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.enums.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.utils.dto.transformer.*;
import tech.synelia.eVoyageBackend.dao.entity.Companies;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "companies"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CompaniesBusiness implements IBasicBusiness<Request<CompaniesDto>, Response<CompaniesDto>> {

	private Response<CompaniesDto> response;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private RoutesRepository routesRepository;
	@Autowired
	private FinancialReportsRepository financialReportsRepository;
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

	public CompaniesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> create(Request<CompaniesDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies>        items    = new ArrayList<Companies>();
			
		for (CompaniesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("address", dto.getAddress());
			fieldsToVerify.put("contact", dto.getContact());
			fieldsToVerify.put("licenseNumber", dto.getLicenseNumber());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if companies to insert do not exist
			Companies existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("companies id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = companiesRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("companies name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

				Companies entityToSave = null;
			entityToSave = CompaniesTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Companies> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = companiesRepository.saveAll((Iterable<Companies>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("companies", locale));
				response.setHasError(true);
				return response;
			}
			List<CompaniesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CompaniesTransformer.INSTANCE.toLiteDtos(itemsSaved) : CompaniesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Companies-----");
		return response;
	}

	/**
	 * update Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> update(Request<CompaniesDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies>        items    = new ArrayList<Companies>();
			
		for (CompaniesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la companies existe
			Companies entityToSave = null;
			entityToSave = companiesRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("companies id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getAddress())) {
				entityToSave.setAddress(dto.getAddress());
			}
			if (Utilities.notBlank(dto.getContact())) {
				entityToSave.setContact(dto.getContact());
			}
			if (Utilities.notBlank(dto.getLicenseNumber())) {
				entityToSave.setLicenseNumber(dto.getLicenseNumber());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Companies> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = companiesRepository.saveAll((Iterable<Companies>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("companies", locale));
				response.setHasError(true);
				return response;
			}
			List<CompaniesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CompaniesTransformer.INSTANCE.toLiteDtos(itemsSaved) : CompaniesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Companies-----");
		return response;
	}

	/**
	 * delete Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> delete(Request<CompaniesDto> request, Locale locale)  {
		log.info("----begin delete Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies>        items    = new ArrayList<Companies>();
			
		for (CompaniesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la companies existe
			Companies existingEntity = null;
			existingEntity = companiesRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("companies -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// routes
			List<Routes> listOfRoutes = routesRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfRoutes != null && !listOfRoutes.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoutes.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// financialReports
			List<FinancialReports> listOfFinancialReports = financialReportsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfFinancialReports != null && !listOfFinancialReports.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfFinancialReports.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			companiesRepository.saveAll((Iterable<Companies>) items);

			response.setHasError(false);
		}

		log.info("----end delete Companies-----");
		return response;
	}

	/**
	 * get Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> getByCriteria(Request<CompaniesDto> request, Locale locale)  throws Exception {
		log.info("----begin get Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies> items 			 = companiesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CompaniesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CompaniesTransformer.INSTANCE.toLiteDtos(items) : CompaniesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(companiesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("companies", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Companies-----");
		return response;
	}

	/**
	 * get full CompaniesDto by using Companies as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CompaniesDto getFullInfos(CompaniesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}

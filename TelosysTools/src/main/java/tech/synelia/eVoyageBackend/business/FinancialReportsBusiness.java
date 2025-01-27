                                                            													
/*
 * Java business for entity table financial_reports 
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
import tech.synelia.eVoyageBackend.dao.entity.FinancialReports;
import tech.synelia.eVoyageBackend.dao.entity.Companies;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "financial_reports"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class FinancialReportsBusiness implements IBasicBusiness<Request<FinancialReportsDto>, Response<FinancialReportsDto>> {

	private Response<FinancialReportsDto> response;
	@Autowired
	private FinancialReportsRepository financialReportsRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
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

	public FinancialReportsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create FinancialReports by using FinancialReportsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FinancialReportsDto> create(Request<FinancialReportsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create FinancialReports-----");

		Response<FinancialReportsDto> response = new Response<FinancialReportsDto>();
		List<FinancialReports>        items    = new ArrayList<FinancialReports>();
			
		for (FinancialReportsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("totalRevenue", dto.getTotalRevenue());
			fieldsToVerify.put("totalBookings", dto.getTotalBookings());
			fieldsToVerify.put("reportDate", dto.getReportDate());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if financialReports to insert do not exist
			FinancialReports existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("financialReports id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if companies exist
			Companies existingCompanies = null;
			if (dto.getCompanyId() != null && dto.getCompanyId() > 0){
				existingCompanies = companiesRepository.findOne(dto.getCompanyId(), false);
				if (existingCompanies == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("companies companyId -> " + dto.getCompanyId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				FinancialReports entityToSave = null;
			entityToSave = FinancialReportsTransformer.INSTANCE.toEntity(dto, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<FinancialReports> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = financialReportsRepository.saveAll((Iterable<FinancialReports>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("financialReports", locale));
				response.setHasError(true);
				return response;
			}
			List<FinancialReportsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FinancialReportsTransformer.INSTANCE.toLiteDtos(itemsSaved) : FinancialReportsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create FinancialReports-----");
		return response;
	}

	/**
	 * update FinancialReports by using FinancialReportsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FinancialReportsDto> update(Request<FinancialReportsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update FinancialReports-----");

		Response<FinancialReportsDto> response = new Response<FinancialReportsDto>();
		List<FinancialReports>        items    = new ArrayList<FinancialReports>();
			
		for (FinancialReportsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la financialReports existe
			FinancialReports entityToSave = null;
			entityToSave = financialReportsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("financialReports id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if companies exist
			if (dto.getCompanyId() != null && dto.getCompanyId() > 0){
				Companies existingCompanies = companiesRepository.findOne(dto.getCompanyId(), false);
				if (existingCompanies == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("companies companyId -> " + dto.getCompanyId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCompanies(existingCompanies);
			}
			if (dto.getTotalRevenue() != null && dto.getTotalRevenue() > 0) {
				entityToSave.setTotalRevenue(dto.getTotalRevenue());
			}
			if (dto.getTotalBookings() != null && dto.getTotalBookings() > 0) {
				entityToSave.setTotalBookings(dto.getTotalBookings());
			}
			if (dto.getReportDate() != null) {
				entityToSave.setReportDate(dto.getReportDate());
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
			List<FinancialReports> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = financialReportsRepository.saveAll((Iterable<FinancialReports>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("financialReports", locale));
				response.setHasError(true);
				return response;
			}
			List<FinancialReportsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FinancialReportsTransformer.INSTANCE.toLiteDtos(itemsSaved) : FinancialReportsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update FinancialReports-----");
		return response;
	}

	/**
	 * delete FinancialReports by using FinancialReportsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FinancialReportsDto> delete(Request<FinancialReportsDto> request, Locale locale)  {
		log.info("----begin delete FinancialReports-----");

		Response<FinancialReportsDto> response = new Response<FinancialReportsDto>();
		List<FinancialReports>        items    = new ArrayList<FinancialReports>();
			
		for (FinancialReportsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la financialReports existe
			FinancialReports existingEntity = null;
			existingEntity = financialReportsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("financialReports -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			financialReportsRepository.saveAll((Iterable<FinancialReports>) items);

			response.setHasError(false);
		}

		log.info("----end delete FinancialReports-----");
		return response;
	}

	/**
	 * get FinancialReports by using FinancialReportsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FinancialReportsDto> getByCriteria(Request<FinancialReportsDto> request, Locale locale)  throws Exception {
		log.info("----begin get FinancialReports-----");

		Response<FinancialReportsDto> response = new Response<FinancialReportsDto>();
		List<FinancialReports> items 			 = financialReportsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<FinancialReportsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FinancialReportsTransformer.INSTANCE.toLiteDtos(items) : FinancialReportsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(financialReportsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("financialReports", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get FinancialReports-----");
		return response;
	}

	/**
	 * get full FinancialReportsDto by using FinancialReports as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private FinancialReportsDto getFullInfos(FinancialReportsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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

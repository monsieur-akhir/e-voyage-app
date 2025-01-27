

/*
 * Java transformer for entity table financial_reports 
 * Created on 2025-01-11 ( Time 04:46:00 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.dao.entity.*;


/**
 * TRANSFORMER for table "financial_reports"
 * 
 * @author Geo
 *
 */
@Mapper
public interface FinancialReportsTransformer {

	FinancialReportsTransformer INSTANCE = Mappers.getMapper(FinancialReportsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
	})
	FinancialReportsDto toDto(FinancialReports entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<FinancialReportsDto> toDtos(List<FinancialReports> entities) throws ParseException;

    default FinancialReportsDto toLiteDto(FinancialReports entity) {
		if (entity == null) {
			return null;
		}
		FinancialReportsDto dto = new FinancialReportsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<FinancialReportsDto> toLiteDtos(List<FinancialReports> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<FinancialReportsDto> dtos = new ArrayList<FinancialReportsDto>();
		for (FinancialReports entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.totalRevenue", target="totalRevenue"),
		@Mapping(source="dto.totalBookings", target="totalBookings"),
		@Mapping(source="dto.reportDate", target="reportDate"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="companies", target="companies"),
	})
    FinancialReports toEntity(FinancialReportsDto dto, Companies companies) throws ParseException;

    //List<FinancialReports> toEntities(List<FinancialReportsDto> dtos) throws ParseException;

}

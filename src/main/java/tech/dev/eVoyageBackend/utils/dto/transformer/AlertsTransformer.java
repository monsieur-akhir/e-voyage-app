

/*
 * Java transformer for entity table alerts 
 * Created on 2025-01-12 ( Time 17:39:50 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.dao.entity.*;


/**
 * TRANSFORMER for table "alerts"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AlertsTransformer {

	AlertsTransformer INSTANCE = Mappers.getMapper(AlertsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
		@Mapping(source="entity.departs.id", target="departId"),
	})
	AlertsDto toDto(Alerts entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AlertsDto> toDtos(List<Alerts> entities) throws ParseException;

    default AlertsDto toLiteDto(Alerts entity) {
		if (entity == null) {
			return null;
		}
		AlertsDto dto = new AlertsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<AlertsDto> toLiteDtos(List<Alerts> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AlertsDto> dtos = new ArrayList<AlertsDto>();
		for (Alerts entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.message", target="message"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="companies", target="companies"),
		@Mapping(source="departs", target="departs"),
	})
    Alerts toEntity(AlertsDto dto, Companies companies, Departs departs) throws ParseException;

    //List<Alerts> toEntities(List<AlertsDto> dtos) throws ParseException;

}

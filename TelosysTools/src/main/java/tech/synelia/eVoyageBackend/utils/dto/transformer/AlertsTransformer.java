

/*
 * Java transformer for entity table alerts 
 * Created on 2025-01-11 ( Time 04:45:54 )
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
		@Mapping(source="entity.routes.id", target="routeId"),
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
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="routes", target="routes"),
	})
    Alerts toEntity(AlertsDto dto, Routes routes) throws ParseException;

    //List<Alerts> toEntities(List<AlertsDto> dtos) throws ParseException;

}

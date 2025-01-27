

/*
 * Java transformer for entity table travel_schedules 
 * Created on 2025-01-12 ( Time 17:39:59 )
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
 * TRANSFORMER for table "travel_schedules"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TravelSchedulesTransformer {

	TravelSchedulesTransformer INSTANCE = Mappers.getMapper(TravelSchedulesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.routes.id", target="routeId"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
	})
	TravelSchedulesDto toDto(TravelSchedules entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TravelSchedulesDto> toDtos(List<TravelSchedules> entities) throws ParseException;

    default TravelSchedulesDto toLiteDto(TravelSchedules entity) {
		if (entity == null) {
			return null;
		}
		TravelSchedulesDto dto = new TravelSchedulesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<TravelSchedulesDto> toLiteDtos(List<TravelSchedules> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TravelSchedulesDto> dtos = new ArrayList<TravelSchedulesDto>();
		for (TravelSchedules entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.departureStation", target="departureStation"),
		@Mapping(source="dto.arrivalStation", target="arrivalStation"),
		@Mapping(source="dto.departureTime", target="departureTime"),
		@Mapping(source="dto.arrivalTime", target="arrivalTime"),
		@Mapping(source="dto.travelDate", target="travelDate"),
		@Mapping(source="dto.price", target="price"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="routes", target="routes"),
		@Mapping(source="companies", target="companies"),
	})
    TravelSchedules toEntity(TravelSchedulesDto dto, Routes routes, Companies companies) throws ParseException;

    //List<TravelSchedules> toEntities(List<TravelSchedulesDto> dtos) throws ParseException;

}

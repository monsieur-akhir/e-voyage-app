

/*
 * Java transformer for entity table buses 
 * Created on 2025-01-12 ( Time 17:39:51 )
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
 * TRANSFORMER for table "buses"
 * 
 * @author Geo
 *
 */
@Mapper
public interface BusesTransformer {

	BusesTransformer INSTANCE = Mappers.getMapper(BusesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.routes.id", target="routeId"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
	})
	BusesDto toDto(Buses entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<BusesDto> toDtos(List<Buses> entities) throws ParseException;

    default BusesDto toLiteDto(Buses entity) {
		if (entity == null) {
			return null;
		}
		BusesDto dto = new BusesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<BusesDto> toLiteDtos(List<Buses> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<BusesDto> dtos = new ArrayList<BusesDto>();
		for (Buses entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.busNumber", target="busNumber"),
		@Mapping(source="dto.capacity", target="capacity"),
		@Mapping(source="dto.seatNumbers", target="seatNumbers"),
		@Mapping(source="dto.availableSeats", target="availableSeats"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="routes", target="routes"),
		@Mapping(source="companies", target="companies"),
	})
    Buses toEntity(BusesDto dto, Routes routes, Companies companies) throws ParseException;

    //List<Buses> toEntities(List<BusesDto> dtos) throws ParseException;

}



/*
 * Java transformer for entity table trip_tracking 
 * Created on 2025-01-11 ( Time 04:46:03 )
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
 * TRANSFORMER for table "trip_tracking"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TripTrackingTransformer {

	TripTrackingTransformer INSTANCE = Mappers.getMapper(TripTrackingTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.timestamp", dateFormat="dd/MM/yyyy",target="timestamp"),
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.routes.id", target="routeId"),
	})
	TripTrackingDto toDto(TripTracking entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TripTrackingDto> toDtos(List<TripTracking> entities) throws ParseException;

    default TripTrackingDto toLiteDto(TripTracking entity) {
		if (entity == null) {
			return null;
		}
		TripTrackingDto dto = new TripTrackingDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<TripTrackingDto> toLiteDtos(List<TripTracking> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TripTrackingDto> dtos = new ArrayList<TripTrackingDto>();
		for (TripTracking entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.location", target="location"),
		@Mapping(source="dto.timestamp", dateFormat="dd/MM/yyyy",target="timestamp"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="routes", target="routes"),
	})
    TripTracking toEntity(TripTrackingDto dto, Routes routes) throws ParseException;

    //List<TripTracking> toEntities(List<TripTrackingDto> dtos) throws ParseException;

}

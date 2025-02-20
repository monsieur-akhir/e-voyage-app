

/*
 * Java transformer for entity table departs 
 * Created on 2025-01-21 ( Time 18:33:54 )
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
 * TRANSFORMER for table "departs"
 * 
 * @author Geo
 *
 */
@Mapper
public interface DepartsTransformer {

	DepartsTransformer INSTANCE = Mappers.getMapper(DepartsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.buses.id", target="busId"),
		@Mapping(source="entity.buses.busNumber", target="busNumber"),
		@Mapping(source="entity.buses.companies.name", target="companiesName"),
		@Mapping(source="entity.buses.companies.id", target="companiesId"),
		@Mapping(source="entity.stations2.id", target="destinationStationId"),
		@Mapping(source="entity.stations2.name", target="stationsNameArrival"),
		@Mapping(source="entity.stations2.cities.name", target="villeArrivee"),
		@Mapping(source="entity.stations.cities.name", target="villeDepart"),
		@Mapping(source="entity.stations.id", target="originStationId"),
		@Mapping(source="entity.stations.name", target="stationsNameDeparture"),
	})
	DepartsDto toDto(Departs entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<DepartsDto> toDtos(List<Departs> entities) throws ParseException;

    default DepartsDto toLiteDto(Departs entity) {
		if (entity == null) {
			return null;
		}
		DepartsDto dto = new DepartsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<DepartsDto> toLiteDtos(List<Departs> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<DepartsDto> dtos = new ArrayList<DepartsDto>();
		for (Departs entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.departureDate", target="departureDate"),
		@Mapping(source="dto.departureTime", target="departureTime"),
		@Mapping(source="dto.price", target="price"),
		@Mapping(source="dto.maxSeats", target="maxSeats"),
		@Mapping(source="dto.availableSeats", target="availableSeats"),
		@Mapping(source="dto.rating", target="rating"),
		@Mapping(source="dto.duration", target="duration"),
		@Mapping(source="dto.isActive", target="isActive"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="buses", target="buses"),
		@Mapping(source="stations2", target="stations2"),
		@Mapping(source="stations", target="stations"),
	})
    Departs toEntity(DepartsDto dto, Buses buses, Stations stations2, Stations stations) throws ParseException;

    //List<Departs> toEntities(List<DepartsDto> dtos) throws ParseException;

}



/*
 * Java transformer for entity table bookings 
 * Created on 2025-01-11 ( Time 04:45:59 )
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
 * TRANSFORMER for table "bookings"
 * 
 * @author Geo
 *
 */
@Mapper
public interface BookingsTransformer {

	BookingsTransformer INSTANCE = Mappers.getMapper(BookingsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.name", target="usersName"),
		@Mapping(source="entity.buses.id", target="busId"),
		@Mapping(source="entity.routes.id", target="routeId"),
	})
	BookingsDto toDto(Bookings entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<BookingsDto> toDtos(List<Bookings> entities) throws ParseException;

    default BookingsDto toLiteDto(Bookings entity) {
		if (entity == null) {
			return null;
		}
		BookingsDto dto = new BookingsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<BookingsDto> toLiteDtos(List<Bookings> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<BookingsDto> dtos = new ArrayList<BookingsDto>();
		for (Bookings entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.seatNumber", target="seatNumber"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="users", target="users"),
		@Mapping(source="buses", target="buses"),
		@Mapping(source="routes", target="routes"),
	})
    Bookings toEntity(BookingsDto dto, Users users, Buses buses, Routes routes) throws ParseException;

    //List<Bookings> toEntities(List<BookingsDto> dtos) throws ParseException;

}

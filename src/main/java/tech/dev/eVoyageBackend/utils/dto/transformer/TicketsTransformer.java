

/*
 * Java transformer for entity table tickets 
 * Created on 2025-01-12 ( Time 17:39:58 )
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
 * TRANSFORMER for table "tickets"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TicketsTransformer {

	TicketsTransformer INSTANCE = Mappers.getMapper(TicketsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
		@Mapping(source="entity.bookings.id", target="bookingId"),
		@Mapping(source = "entity.bookings.users.id", target = "clientId"),
		@Mapping(source="entity.users.id", target="scannedBy"),
		@Mapping(source="entity.users.name", target="usersName"),
	})
	TicketsDto toDto(Tickets entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TicketsDto> toDtos(List<Tickets> entities) throws ParseException;

    default TicketsDto toLiteDto(Tickets entity) {
		if (entity == null) {
			return null;
		}
		TicketsDto dto = new TicketsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<TicketsDto> toLiteDtos(List<Tickets> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TicketsDto> dtos = new ArrayList<TicketsDto>();
		for (Tickets entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.qrCode", target="qrCode"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="companies", target="companies"),
		@Mapping(source="bookings", target="bookings"),
		@Mapping(source="users", target="users"),
	})
    Tickets toEntity(TicketsDto dto, Companies companies, Bookings bookings, Users users) throws ParseException;

    //List<Tickets> toEntities(List<TicketsDto> dtos) throws ParseException;

}

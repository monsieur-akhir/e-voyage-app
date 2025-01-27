

/*
 * Java transformer for entity table notifications 
 * Created on 2025-01-12 ( Time 17:39:54 )
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
 * TRANSFORMER for table "notifications"
 * 
 * @author Geo
 *
 */
@Mapper
public interface NotificationsTransformer {

	NotificationsTransformer INSTANCE = Mappers.getMapper(NotificationsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.name", target="usersName"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
	})
	NotificationsDto toDto(Notifications entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<NotificationsDto> toDtos(List<Notifications> entities) throws ParseException;

    default NotificationsDto toLiteDto(Notifications entity) {
		if (entity == null) {
			return null;
		}
		NotificationsDto dto = new NotificationsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<NotificationsDto> toLiteDtos(List<Notifications> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<NotificationsDto> dtos = new ArrayList<NotificationsDto>();
		for (Notifications entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.content", target="content"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="users", target="users"),
		@Mapping(source="companies", target="companies"),
	})
    Notifications toEntity(NotificationsDto dto, Users users, Companies companies) throws ParseException;

    //List<Notifications> toEntities(List<NotificationsDto> dtos) throws ParseException;

}

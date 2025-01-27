

/*
 * Java transformer for entity table user_roles 
 * Created on 2025-01-11 ( Time 04:46:03 )
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
 * TRANSFORMER for table "user_roles"
 * 
 * @author Geo
 *
 */
@Mapper
public interface UserRolesTransformer {

	UserRolesTransformer INSTANCE = Mappers.getMapper(UserRolesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.roles.id", target="roleId"),
		@Mapping(source="entity.roles.name", target="rolesName"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.name", target="usersName"),
	})
	UserRolesDto toDto(UserRoles entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UserRolesDto> toDtos(List<UserRoles> entities) throws ParseException;

    default UserRolesDto toLiteDto(UserRoles entity) {
		if (entity == null) {
			return null;
		}
		UserRolesDto dto = new UserRolesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<UserRolesDto> toLiteDtos(List<UserRoles> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UserRolesDto> dtos = new ArrayList<UserRolesDto>();
		for (UserRoles entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="roles", target="roles"),
		@Mapping(source="users", target="users"),
	})
    UserRoles toEntity(UserRolesDto dto, Roles roles, Users users) throws ParseException;

    //List<UserRoles> toEntities(List<UserRolesDto> dtos) throws ParseException;

}

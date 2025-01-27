

/*
 * Java transformer for entity table role_permissions 
 * Created on 2025-01-12 ( Time 17:39:56 )
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
 * TRANSFORMER for table "role_permissions"
 * 
 * @author Geo
 *
 */
@Mapper
public interface RolePermissionsTransformer {

	RolePermissionsTransformer INSTANCE = Mappers.getMapper(RolePermissionsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.roles.id", target="roleId"),
		@Mapping(source="entity.roles.name", target="rolesName"),
		@Mapping(source="entity.permissions.id", target="permissionId"),
		@Mapping(source="entity.permissions.name", target="permissionsName"),
	})
	RolePermissionsDto toDto(RolePermissions entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RolePermissionsDto> toDtos(List<RolePermissions> entities) throws ParseException;

    default RolePermissionsDto toLiteDto(RolePermissions entity) {
		if (entity == null) {
			return null;
		}
		RolePermissionsDto dto = new RolePermissionsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<RolePermissionsDto> toLiteDtos(List<RolePermissions> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RolePermissionsDto> dtos = new ArrayList<RolePermissionsDto>();
		for (RolePermissions entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="roles", target="roles"),
		@Mapping(source="permissions", target="permissions"),
	})
    RolePermissions toEntity(RolePermissionsDto dto, Roles roles, Permissions permissions) throws ParseException;

    //List<RolePermissions> toEntities(List<RolePermissionsDto> dtos) throws ParseException;

}



/*
 * Java transformer for entity table users 
 * Created on 2025-01-12 ( Time 17:40:01 )
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
 * TRANSFORMER for table "users"
 * 
 * @author Geo
 *
 */
@Mapper
public interface UsersTransformer {

	UsersTransformer INSTANCE = Mappers.getMapper(UsersTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName")

	})
	UsersDto toDto(Users entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UsersDto> toDtos(List<Users> entities) throws ParseException;

    default UsersDto toLiteDto(Users entity) {
		if (entity == null) {
			return null;
		}
		UsersDto dto = new UsersDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<UsersDto> toLiteDtos(List<Users> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UsersDto> dtos = new ArrayList<UsersDto>();
		for (Users entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.phone", target="phone"),
		@Mapping(source="dto.password", target="password"),
		@Mapping(source="dto.roleId", target="roleId"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="companies", target="companies"),
	})
    Users toEntity(UsersDto dto, Companies companies) throws ParseException;

    //List<Users> toEntities(List<UsersDto> dtos) throws ParseException;

}

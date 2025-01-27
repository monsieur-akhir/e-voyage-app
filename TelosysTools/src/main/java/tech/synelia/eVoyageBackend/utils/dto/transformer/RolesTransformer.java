

/*
 * Java transformer for entity table roles 
 * Created on 2025-01-11 ( Time 04:46:01 )
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
 * TRANSFORMER for table "roles"
 * 
 * @author Geo
 *
 */
@Mapper
public interface RolesTransformer {

	RolesTransformer INSTANCE = Mappers.getMapper(RolesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	RolesDto toDto(Roles entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RolesDto> toDtos(List<Roles> entities) throws ParseException;

    default RolesDto toLiteDto(Roles entity) {
		if (entity == null) {
			return null;
		}
		RolesDto dto = new RolesDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<RolesDto> toLiteDtos(List<Roles> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RolesDto> dtos = new ArrayList<RolesDto>();
		for (Roles entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
	})
    Roles toEntity(RolesDto dto) throws ParseException;

    //List<Roles> toEntities(List<RolesDto> dtos) throws ParseException;

}

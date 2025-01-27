

/*
 * Java transformer for entity table parametre_generaux 
 * Created on 2024-12-19 ( Time 23:36:43 )
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

import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;


/**
 * TRANSFORMER for table "parametre_generaux"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ParametreGenerauxTransformer {

	ParametreGenerauxTransformer INSTANCE = Mappers.getMapper(ParametreGenerauxTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	ParametreGenerauxDto toDto(ParametreGeneraux entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ParametreGenerauxDto> toDtos(List<ParametreGeneraux> entities) throws ParseException;

    default ParametreGenerauxDto toLiteDto(ParametreGeneraux entity) {
		if (entity == null) {
			return null;
		}
		ParametreGenerauxDto dto = new ParametreGenerauxDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ParametreGenerauxDto> toLiteDtos(List<ParametreGeneraux> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ParametreGenerauxDto> dtos = new ArrayList<ParametreGenerauxDto>();
		for (ParametreGeneraux entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.paramName", target="paramName"),
		@Mapping(source="dto.paramValue", target="paramValue"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
	})
    ParametreGeneraux toEntity(ParametreGenerauxDto dto) throws ParseException;

    //List<ParametreGeneraux> toEntities(List<ParametreGenerauxDto> dtos) throws ParseException;

}

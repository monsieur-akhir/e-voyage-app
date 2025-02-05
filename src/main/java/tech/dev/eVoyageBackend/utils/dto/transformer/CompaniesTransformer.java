

/*
 * Java transformer for entity table companies 
 * Created on 2025-01-12 ( Time 17:39:52 )
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
 * TRANSFORMER for table "companies"
 * 
 * @author Geo
 *
 */
@Mapper
public interface CompaniesTransformer {

	CompaniesTransformer INSTANCE = Mappers.getMapper(CompaniesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	CompaniesDto toDto(Companies entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CompaniesDto> toDtos(List<Companies> entities) throws ParseException;

    default CompaniesDto toLiteDto(Companies entity) {
		if (entity == null) {
			return null;
		}
		CompaniesDto dto = new CompaniesDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<CompaniesDto> toLiteDtos(List<Companies> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CompaniesDto> dtos = new ArrayList<CompaniesDto>();
		for (Companies entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.address", target="address"),
		@Mapping(source="dto.contact", target="contact"),
		@Mapping(source="dto.licenseNumber", target="licenseNumber"),
		@Mapping(source="dto.rating", target="rating"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.logoPath", target="logoPath"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
	})
    Companies toEntity(CompaniesDto dto) throws ParseException;

    //List<Companies> toEntities(List<CompaniesDto> dtos) throws ParseException;

}

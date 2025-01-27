

/*
 * Java transformer for entity table routes 
 * Created on 2025-01-11 ( Time 04:46:02 )
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
 * TRANSFORMER for table "routes"
 * 
 * @author Geo
 *
 */
@Mapper
public interface RoutesTransformer {

	RoutesTransformer INSTANCE = Mappers.getMapper(RoutesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
	})
	RoutesDto toDto(Routes entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RoutesDto> toDtos(List<Routes> entities) throws ParseException;

    default RoutesDto toLiteDto(Routes entity) {
		if (entity == null) {
			return null;
		}
		RoutesDto dto = new RoutesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<RoutesDto> toLiteDtos(List<Routes> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RoutesDto> dtos = new ArrayList<RoutesDto>();
		for (Routes entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.origin", target="origin"),
		@Mapping(source="dto.destination", target="destination"),
		@Mapping(source="dto.duration", target="duration"),
		@Mapping(source="dto.price", target="price"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="companies", target="companies"),
	})
    Routes toEntity(RoutesDto dto, Companies companies) throws ParseException;

    //List<Routes> toEntities(List<RoutesDto> dtos) throws ParseException;

}

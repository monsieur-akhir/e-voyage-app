

/*
 * Java transformer for entity table districts 
 * Created on 2025-01-21 ( Time 18:33:54 )
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
 * TRANSFORMER for table "districts"
 * 
 * @author Geo
 *
 */
@Mapper
public interface DistrictsTransformer {

	DistrictsTransformer INSTANCE = Mappers.getMapper(DistrictsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.cities.id", target="cityId"),
		@Mapping(source="entity.cities.name", target="citiesName"),
	})
	DistrictsDto toDto(Districts entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<DistrictsDto> toDtos(List<Districts> entities) throws ParseException;

    default DistrictsDto toLiteDto(Districts entity) {
		if (entity == null) {
			return null;
		}
		DistrictsDto dto = new DistrictsDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<DistrictsDto> toLiteDtos(List<Districts> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<DistrictsDto> dtos = new ArrayList<DistrictsDto>();
		for (Districts entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.isAvailable", target="isAvailable"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="cities", target="cities"),
	})
    Districts toEntity(DistrictsDto dto, Cities cities) throws ParseException;

    //List<Districts> toEntities(List<DistrictsDto> dtos) throws ParseException;

}

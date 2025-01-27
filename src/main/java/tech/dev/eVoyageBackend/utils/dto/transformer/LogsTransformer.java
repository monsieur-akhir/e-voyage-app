

/*
 * Java transformer for entity table logs 
 * Created on 2024-08-20 ( Time 10:24:21 )
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
 * TRANSFORMER for table "logs"
 * 
 * @author Geo
 *
 */
@Mapper
public interface LogsTransformer {

	LogsTransformer INSTANCE = Mappers.getMapper(LogsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.date", dateFormat="dd/MM/yyyy",target="date"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.response", target="response", ignore = true),
		@Mapping(source="entity.request", target="request", ignore = true),
	})
	LogsDto toDto(Logs entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<LogsDto> toDtos(List<Logs> entities) throws ParseException;

    default LogsDto toLiteDto(Logs entity) {
		if (entity == null) {
			return null;
		}
		LogsDto dto = new LogsDto();
		dto.setId( entity.getId() );
		dto.setLogin( entity.getLogin() );
		dto.setNom( entity.getNom() );
		dto.setPrenom( entity.getPrenom() );
		return dto;
    }

	default List<LogsDto> toLiteDtos(List<Logs> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<LogsDto> dtos = new ArrayList<LogsDto>();
		for (Logs entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.actionService", target="actionService"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.date", dateFormat="dd/MM/yyyy",target="date"),
		@Mapping(source="dto.idStatus", target="idStatus"),
		@Mapping(source="dto.ipadress", target="ipadress"),
		@Mapping(source="dto.isConnexion", target="isConnexion"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.login", target="login"),
		@Mapping(source="dto.machine", target="machine"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenom", target="prenom"),
		@Mapping(source="dto.request", target="request"),
		@Mapping(source="dto.response", target="response"),
		@Mapping(source="dto.searchString", target="searchString"),
		@Mapping(source="dto.statusConnection", target="statusConnection"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.uri", target="uri"),
	})
    Logs toEntity(LogsDto dto) throws ParseException;

    //List<Logs> toEntities(List<LogsDto> dtos) throws ParseException;

}

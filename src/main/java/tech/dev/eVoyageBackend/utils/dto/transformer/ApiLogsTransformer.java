

/*
 * Java transformer for entity table api_logs 
 * Created on 2024-11-25 ( Time 15:12:19 )
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
 * TRANSFORMER for table "api_logs"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ApiLogsTransformer {

	ApiLogsTransformer INSTANCE = Mappers.getMapper(ApiLogsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.requestTime", dateFormat="dd/MM/yyyy",target="requestTime"),
		@Mapping(source="entity.responseTime", dateFormat="dd/MM/yyyy",target="responseTime"),
		@Mapping(source="entity.createdDate", dateFormat="dd/MM/yyyy",target="createdDate"),
		@Mapping(source="entity.lastModifiedDate", dateFormat="dd/MM/yyyy",target="lastModifiedDate"),
	})
	ApiLogsDto toDto(ApiLogs entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ApiLogsDto> toDtos(List<ApiLogs> entities) throws ParseException;

    default ApiLogsDto toLiteDto(ApiLogs entity) {
		if (entity == null) {
			return null;
		}
		ApiLogsDto dto = new ApiLogsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ApiLogsDto> toLiteDtos(List<ApiLogs> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ApiLogsDto> dtos = new ArrayList<ApiLogsDto>();
		for (ApiLogs entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.requestTime", dateFormat="dd/MM/yyyy",target="requestTime"),
		@Mapping(source="dto.requestUrl", target="requestUrl"),
		@Mapping(source="dto.requestMethod", target="requestMethod"),
		@Mapping(source="dto.requestHeaders", target="requestHeaders"),
		@Mapping(source="dto.requestBody", target="requestBody"),
		@Mapping(source="dto.responseTime", dateFormat="dd/MM/yyyy",target="responseTime"),
		@Mapping(source="dto.responseStatus", target="responseStatus"),
		@Mapping(source="dto.responseBody", target="responseBody"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.createdDate", dateFormat="dd/MM/yyyy",target="createdDate"),
		@Mapping(source="dto.lastModifiedBy", target="lastModifiedBy"),
		@Mapping(source="dto.lastModifiedDate", dateFormat="dd/MM/yyyy",target="lastModifiedDate"),
		@Mapping(source="dto.status", target="status"),
	})
    ApiLogs toEntity(ApiLogsDto dto) throws ParseException;

    //List<ApiLogs> toEntities(List<ApiLogsDto> dtos) throws ParseException;

}



/*
 * Java transformer for entity table param_notification 
 * Created on 2024-09-02 ( Time 12:26:07 )
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
 * TRANSFORMER for table "param_notification"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ParamNotificationTransformer {

	ParamNotificationTransformer INSTANCE = Mappers.getMapper(ParamNotificationTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	ParamNotificationDto toDto(ParamNotification entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ParamNotificationDto> toDtos(List<ParamNotification> entities) throws ParseException;

    default ParamNotificationDto toLiteDto(ParamNotification entity) {
		if (entity == null) {
			return null;
		}
		ParamNotificationDto dto = new ParamNotificationDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ParamNotificationDto> toLiteDtos(List<ParamNotification> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ParamNotificationDto> dtos = new ArrayList<ParamNotificationDto>();
		for (ParamNotification entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.lang", target="lang"),
		@Mapping(source="dto.message", target="message"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
	})
    ParamNotification toEntity(ParamNotificationDto dto) throws ParseException;

    //List<ParamNotification> toEntities(List<ParamNotificationDto> dtos) throws ParseException;

}

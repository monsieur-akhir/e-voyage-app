

/*
 * Java transformer for entity table api_user 
 * Created on 2024-11-16 ( Time 14:33:28 )
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
 * TRANSFORMER for table "api_user"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ApiUserTransformer {

	ApiUserTransformer INSTANCE = Mappers.getMapper(ApiUserTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.dateSendCodeOtpAt", dateFormat="dd/MM/yyyy",target="dateSendCodeOtpAt"),
		@Mapping(source="entity.firstConnection", dateFormat="dd/MM/yyyy",target="firstConnection"),
		@Mapping(source="entity.lastActivityDate", dateFormat="dd/MM/yyyy",target="lastActivityDate"),
		@Mapping(source="entity.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
		@Mapping(source="entity.lastLockDate", dateFormat="dd/MM/yyyy",target="lastLockDate"),
		@Mapping(source="entity.passCodeCreatedAt", dateFormat="dd/MM/yyyy",target="passCodeCreatedAt"),
		@Mapping(source="entity.passCodeExpireAt", dateFormat="dd/MM/yyyy",target="passCodeExpireAt"),
		@Mapping(source="entity.tokenCreatedAt", dateFormat="dd/MM/yyyy",target="tokenCreatedAt"),
		@Mapping(source="entity.tokenExpireAt", dateFormat="dd/MM/yyyy",target="tokenExpireAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	ApiUserDto toDto(ApiUser entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ApiUserDto> toDtos(List<ApiUser> entities) throws ParseException;

    default ApiUserDto toLiteDto(ApiUser entity) {
		if (entity == null) {
			return null;
		}
		ApiUserDto dto = new ApiUserDto();
		dto.setId( entity.getId() );
		dto.setLogin( entity.getLogin() );
		return dto;
    }

	default List<ApiUserDto> toLiteDtos(List<ApiUser> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ApiUserDto> dtos = new ArrayList<ApiUserDto>();
		for (ApiUser entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.dateSendCodeOtpAt", dateFormat="dd/MM/yyyy",target="dateSendCodeOtpAt"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.firstConnection", dateFormat="dd/MM/yyyy",target="firstConnection"),
		@Mapping(source="dto.isActive", target="isActive"),
		@Mapping(source="dto.isConnected", target="isConnected"),
		@Mapping(source="dto.isDefaultPassword", target="isDefaultPassword"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.isLocked", target="isLocked"),
		@Mapping(source="dto.isValidPassCode", target="isValidPassCode"),
		@Mapping(source="dto.isValidToken", target="isValidToken"),
		@Mapping(source="dto.lastActivityDate", dateFormat="dd/MM/yyyy",target="lastActivityDate"),
		@Mapping(source="dto.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
		@Mapping(source="dto.lastLockDate", dateFormat="dd/MM/yyyy",target="lastLockDate"),
		@Mapping(source="dto.login", target="login"),
		@Mapping(source="dto.loginAttempts", target="loginAttempts"),
		@Mapping(source="dto.otpCode", target="otpCode"),
		@Mapping(source="dto.passCode", target="passCode"),
		@Mapping(source="dto.passCodeCreatedAt", dateFormat="dd/MM/yyyy",target="passCodeCreatedAt"),
		@Mapping(source="dto.passCodeExpireAt", dateFormat="dd/MM/yyyy",target="passCodeExpireAt"),
		@Mapping(source="dto.password", target="password"),
		@Mapping(source="dto.searchString", target="searchString"),
		@Mapping(source="dto.telephone", target="telephone"),
		@Mapping(source="dto.token", target="token"),
		@Mapping(source="dto.tokenCreatedAt", dateFormat="dd/MM/yyyy",target="tokenCreatedAt"),
		@Mapping(source="dto.tokenExpireAt", dateFormat="dd/MM/yyyy",target="tokenExpireAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.roleId", target="roleId"),
		@Mapping(source="dto.type", target="type"),
	})
    ApiUser toEntity(ApiUserDto dto) throws ParseException;

    //List<ApiUser> toEntities(List<ApiUserDto> dtos) throws ParseException;

}

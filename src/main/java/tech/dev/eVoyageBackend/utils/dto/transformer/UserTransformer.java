

/*
 * Java transformer for entity table user 
 * Created on 2023-08-30 ( Time 17:27:05 )
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

import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.utils.contract.FullTransformerQualifier;
import tech.dev.eVoyageBackend.utils.dto.UserDto;
 
 
 /**
  * TRANSFORMER for table "user"
  * 
  * @author Geo
  *
  */
 @Mapper
 public interface UserTransformer {
 
	 UserTransformer INSTANCE = Mappers.getMapper(UserTransformer.class);
 
	 @FullTransformerQualifier
	 @Mappings({
		 @Mapping(source="entity.bornOn", dateFormat="dd/MM/yyyy",target="bornOn"),
		 @Mapping(source="entity.lastConnectionDate", dateFormat="dd/MM/yyyy HH:mm:ss",target="lastConnectionDate"),
		 @Mapping(source="entity.firstConnection", dateFormat="dd/MM/yyyy HH:mm:ss",target="firstConnection"),
		 @Mapping(source="entity.lastLockDate", dateFormat="dd/MM/yyyy HH:mm:ss",target="lastLockDate"),
		 @Mapping(source="entity.passCodeExpireAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="passCodeExpireAt"),
		 @Mapping(source="entity.passCodeCreatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="passCodeCreatedAt"),
		 @Mapping(source="entity.tokenCreatedAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="tokenCreatedAt"),
		 @Mapping(source="entity.tokenExpireAt", dateFormat="dd/MM/yyyy HH:mm:ss",target="tokenExpireAt"),
		 @Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		 @Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		 @Mapping(source="entity.role.id", target="roleId"),
		 @Mapping(source="entity.role.libelle", target="roleLibelle"),
		 
		 @Mapping(source="entity.password", target="password", ignore = true),
		 @Mapping(source="entity.searchString", target="searchString", ignore = true),
	 })
	 UserDto toDto(User entity) throws ParseException;
 
	 @IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
	 List<UserDto> toDtos(List<User> entities) throws ParseException;
 
	 default UserDto toLiteDto(User entity) {
		 if (entity == null) {
			 return null;
		 }
		 UserDto dto = new UserDto();
		 dto.setId( entity.getId() );
		 dto.setLogin( entity.getLogin() );
		 dto.setFirstName( entity.getFirstName() );
		 dto.setLastName( entity.getLastName() );
		 return dto;
	 }
 
	 default List<UserDto> toLiteDtos(List<User> entities) {
		 if (entities == null || entities.stream().allMatch(o -> o == null)) {
			 return null;
		 }
		 List<UserDto> dtos = new ArrayList<UserDto>();
		 for (User entity : entities) {
			 dtos.add(toLiteDto(entity));
		 }
		 return dtos;
	 }
 
	 @Mappings({
		 @Mapping(source="dto.id", target="id"),
		 @Mapping(source="dto.login", target="login"),
		 @Mapping(source="dto.password", target="password"),
		 @Mapping(source="dto.firstName", target="firstName"),
		 @Mapping(source="dto.lastName", target="lastName"),
		 @Mapping(source="dto.fonction", target="fonction"),
		 @Mapping(source="dto.lieuFonction", target="lieuFonction"),
		 @Mapping(source="dto.email", target="email"),
		 @Mapping(source="dto.otpCode", target="otpCode"),
		 @Mapping(source="dto.bornOn", dateFormat="dd/MM/yyyy",target="bornOn"),
		 @Mapping(source="dto.telephone", target="telephone"),
		 @Mapping(source="dto.isDefaultPassword", target="isDefaultPassword"),
		 @Mapping(source="dto.isConnected", target="isConnected"),
		 @Mapping(source="dto.isLocked", target="isLocked"),
		 @Mapping(source="dto.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
		 @Mapping(source="dto.firstConnection", dateFormat="dd/MM/yyyy",target="firstConnection"),
		 @Mapping(source="dto.lastLockDate", dateFormat="dd/MM/yyyy",target="lastLockDate"),
		 @Mapping(source="dto.passCode", target="passCode"),
		 @Mapping(source="dto.isValidPassCode", target="isValidPassCode"),
		 @Mapping(source="dto.passCodeExpireAt", dateFormat="dd/MM/yyyy",target="passCodeExpireAt"),
		 @Mapping(source="dto.passCodeCreatedAt", dateFormat="dd/MM/yyyy",target="passCodeCreatedAt"),
		 @Mapping(source="dto.token", target="token"),
		 @Mapping(source="dto.isValidToken", target="isValidToken"),
		 @Mapping(source="dto.tokenCreatedAt", dateFormat="dd/MM/yyyy",target="tokenCreatedAt"),
		 @Mapping(source="dto.tokenExpireAt", dateFormat="dd/MM/yyyy",target="tokenExpireAt"),
		 @Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		 @Mapping(source="dto.createdBy", target="createdBy"),
		 @Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		 @Mapping(source="dto.updatedBy", target="updatedBy"),
		 @Mapping(source="dto.loginAttempts", target="loginAttempts"),
		 @Mapping(source="dto.isActive", target="isActive"),
		 @Mapping(source="dto.searchString", target="searchString"),
		 @Mapping(source="dto.isDeleted", target="isDeleted"),
		 @Mapping(source="role", target="role"),
		 @Mapping(source="dto.dateSendCodeOtpAt", dateFormat="dd/MM/yyyy",target="dateSendCodeOtpAt"),
		 
	 })
	 User toEntity(UserDto dto, Role role) throws ParseException;
 
	 //List<User> toEntities(List<UserDto> dtos) throws ParseException;
 
 }
 
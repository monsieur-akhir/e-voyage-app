

/*
 * Java transformer for entity table role_fonctionalite 
 * Created on 2023-08-30 ( Time 17:27:04 )
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

import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.dao.entity.RoleFonctionalite;
import tech.dev.eVoyageBackend.utils.contract.FullTransformerQualifier;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;
 
 
 /**
  * TRANSFORMER for table "role_fonctionalite"
  * 
  * @author Geo
  *
  */
 @Mapper
 public interface RoleFonctionaliteTransformer {
 
	 RoleFonctionaliteTransformer INSTANCE = Mappers.getMapper(RoleFonctionaliteTransformer.class);
	
	 @FullTransformerQualifier
	 @Mappings({
		 @Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		 @Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		 @Mapping(source="entity.role.id", target="roleId"),
		 @Mapping(source="entity.role.libelle", target="roleLibelle"),
		 @Mapping(source="entity.fonctionalite.id", target="fonctionnaliteId"),
		 @Mapping(source="entity.fonctionalite.code", target="fonctionaliteCode"),
		 @Mapping(source="entity.fonctionalite.libelle", target="fonctionaliteLibelle"),
	 })
	 RoleFonctionaliteDto toDto(RoleFonctionalite entity) throws ParseException;
 
	 @IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
	 List<RoleFonctionaliteDto> toDtos(List<RoleFonctionalite> entities) throws ParseException;
 
	 default RoleFonctionaliteDto toLiteDto(RoleFonctionalite entity) {
		 if (entity == null) {
			 return null;
		 }
		 RoleFonctionaliteDto dto = new RoleFonctionaliteDto();
		 dto.setId( entity.getId() );
		 return dto;
	 }
 
	 default List<RoleFonctionaliteDto> toLiteDtos(List<RoleFonctionalite> entities) {
		 if (entities == null || entities.stream().allMatch(o -> o == null)) {
			 return null;
		 }
		 List<RoleFonctionaliteDto> dtos = new ArrayList<RoleFonctionaliteDto>();
		 for (RoleFonctionalite entity : entities) {
			 dtos.add(toLiteDto(entity));
		 }
		 return dtos;
	 }
 
	 @Mappings({
		 @Mapping(source="dto.id", target="id"),
		 @Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		 @Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		 @Mapping(source="dto.createdBy", target="createdBy"),
		 @Mapping(source="dto.updatedBy", target="updatedBy"),
		 @Mapping(source="dto.isDeleted", target="isDeleted"),
		 @Mapping(source="role", target="role"),
		 @Mapping(source="fonctionalite", target="fonctionalite"),
	 })
	 RoleFonctionalite toEntity(RoleFonctionaliteDto dto, Role role, Fonctionalite fonctionalite) throws ParseException;
 
	 //List<RoleFonctionalite> toEntities(List<RoleFonctionaliteDto> dtos) throws ParseException;
 
 }
 
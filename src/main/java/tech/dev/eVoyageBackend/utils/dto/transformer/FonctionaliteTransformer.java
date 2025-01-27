

/*
 * Java transformer for entity table fonctionalite 
 * Created on 2023-08-30 ( Time 17:27:02 )
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
import tech.dev.eVoyageBackend.utils.contract.FullTransformerQualifier;
import tech.dev.eVoyageBackend.utils.dto.FonctionaliteDto;
 
 
 /**
  * TRANSFORMER for table "fonctionalite"
  * 
  * @author Geo
  *
  */
 @Mapper
 public interface FonctionaliteTransformer {
 
	 FonctionaliteTransformer INSTANCE = Mappers.getMapper(FonctionaliteTransformer.class);
 
	 @FullTransformerQualifier
	 @Mappings({
		 @Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		 @Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		 @Mapping(source="entity.fonctionalite.id", target="parentId"),
		 @Mapping(source="entity.fonctionalite.code", target="fonctionaliteCode"),
		 @Mapping(source="entity.fonctionalite.libelle", target="fonctionaliteLibelle"),
	 })
	 FonctionaliteDto toDto(Fonctionalite entity) throws ParseException;
 
	 @IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
	 List<FonctionaliteDto> toDtos(List<Fonctionalite> entities) throws ParseException;
 
	 default FonctionaliteDto toLiteDto(Fonctionalite entity) {
		 if (entity == null) {
			 return null;
		 }
		 FonctionaliteDto dto = new FonctionaliteDto();
		 dto.setId( entity.getId() );
		 dto.setLibelle( entity.getLibelle() );
		 return dto;
	 }
 
	 default List<FonctionaliteDto> toLiteDtos(List<Fonctionalite> entities) {
		 if (entities == null || entities.stream().allMatch(o -> o == null)) {
			 return null;
		 }
		 List<FonctionaliteDto> dtos = new ArrayList<FonctionaliteDto>();
		 for (Fonctionalite entity : entities) {
			 dtos.add(toLiteDto(entity));
		 }
		 return dtos;
	 }
 
	 @Mappings({
		 @Mapping(source="dto.id", target="id"),
		 @Mapping(source="dto.code", target="code"),
		 @Mapping(source="dto.libelle", target="libelle"),
		 @Mapping(source="dto.isAvailableForUser", target="isAvailableForUser"),
		 @Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		 @Mapping(source="dto.createdBy", target="createdBy"),
		 @Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		 @Mapping(source="dto.updatedBy", target="updatedBy"),
		 @Mapping(source="dto.isDeleted", target="isDeleted"),
		 @Mapping(source="fonctionalite", target="fonctionalite"),
	 })
	 Fonctionalite toEntity(FonctionaliteDto dto, Fonctionalite fonctionalite) throws ParseException;
 
	 //List<Fonctionalite> toEntities(List<FonctionaliteDto> dtos) throws ParseException;
 
 }
 
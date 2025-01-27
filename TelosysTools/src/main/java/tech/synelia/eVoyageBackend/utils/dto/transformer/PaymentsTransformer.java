

/*
 * Java transformer for entity table payments 
 * Created on 2025-01-11 ( Time 04:46:01 )
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
 * TRANSFORMER for table "payments"
 * 
 * @author Geo
 *
 */
@Mapper
public interface PaymentsTransformer {

	PaymentsTransformer INSTANCE = Mappers.getMapper(PaymentsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.bookings.id", target="bookingId"),
	})
	PaymentsDto toDto(Payments entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PaymentsDto> toDtos(List<Payments> entities) throws ParseException;

    default PaymentsDto toLiteDto(Payments entity) {
		if (entity == null) {
			return null;
		}
		PaymentsDto dto = new PaymentsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<PaymentsDto> toLiteDtos(List<Payments> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PaymentsDto> dtos = new ArrayList<PaymentsDto>();
		for (Payments entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.amount", target="amount"),
		@Mapping(source="dto.paymentMethod", target="paymentMethod"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="bookings", target="bookings"),
	})
    Payments toEntity(PaymentsDto dto, Bookings bookings) throws ParseException;

    //List<Payments> toEntities(List<PaymentsDto> dtos) throws ParseException;

}

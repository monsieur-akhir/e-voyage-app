

/*
 * Java transformer for entity table payments 
 * Created on 2025-01-12 ( Time 17:39:54 )
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
		@Mapping(source="entity.companies.id", target="companyId"),
		@Mapping(source="entity.companies.name", target="companiesName"),
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
		@Mapping(source="dto.reference", target="reference"),
		@Mapping(source="dto.transactionId", target="transactionId"),
		@Mapping(source="dto.currency", target="currency"),
		@Mapping(source="dto.numeroTel", target="numeroTel"),
		@Mapping(source="dto.cardCredit", target ="cardCredit" ),
		@Mapping(source="dto.cardType", target ="cardType" ),
		@Mapping(source="dto.cardExpire", target ="cardExpire"),
		@Mapping(source="dto.cardHolder", target ="cardHolder"),
		@Mapping(source="dto.cardCvv", target="cardCvv"),
		@Mapping(source="dto.cardIssuer",target="cardIssuer"),
		@Mapping(source="dto.cardCountry",target="cardCountry"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="bookings", target="bookings"),
		@Mapping(source="companies", target="companies"),
	})
    Payments toEntity(PaymentsDto dto, Bookings bookings, Companies companies) throws ParseException;

    //List<Payments> toEntities(List<PaymentsDto> dtos) throws ParseException;

}

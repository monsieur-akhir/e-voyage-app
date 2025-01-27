package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Bookings;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Payments;
import tech.dev.eVoyageBackend.utils.dto.PaymentsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:54+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class PaymentsTransformerImpl implements PaymentsTransformer {

    @Override
    public PaymentsDto toDto(Payments entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        PaymentsDto paymentsDto = new PaymentsDto();

        if ( entity.getCreatedAt() != null ) {
            paymentsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            paymentsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            paymentsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        paymentsDto.setBookingId( entityBookingsId( entity ) );
        paymentsDto.setCompanyId( entityCompaniesId( entity ) );
        paymentsDto.setCompaniesName( entityCompaniesName( entity ) );
        paymentsDto.setId( entity.getId() );
        paymentsDto.setAmount( entity.getAmount() );
        paymentsDto.setPaymentMethod( entity.getPaymentMethod() );
        paymentsDto.setStatus( entity.getStatus() );
        paymentsDto.setReference( entity.getReference() );
        paymentsDto.setTransactionId( entity.getTransactionId() );
        paymentsDto.setNumeroTel( entity.getNumeroTel() );
        paymentsDto.setCardCredit( entity.getCardCredit() );
        paymentsDto.setCardType( entity.getCardType() );
        paymentsDto.setCardExpire( entity.getCardExpire() );
        paymentsDto.setCardCvv( entity.getCardCvv() );
        paymentsDto.setCardHolder( entity.getCardHolder() );
        paymentsDto.setCardIssuer( entity.getCardIssuer() );
        paymentsDto.setCardCountry( entity.getCardCountry() );
        paymentsDto.setCurrency( entity.getCurrency() );
        paymentsDto.setCreatedBy( entity.getCreatedBy() );
        paymentsDto.setUpdatedBy( entity.getUpdatedBy() );
        paymentsDto.setIsDeleted( entity.getIsDeleted() );
        paymentsDto.setDeletedBy( entity.getDeletedBy() );

        return paymentsDto;
    }

    @Override
    public List<PaymentsDto> toDtos(List<Payments> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<PaymentsDto> list = new ArrayList<PaymentsDto>( entities.size() );
        for ( Payments payments : entities ) {
            list.add( toDto( payments ) );
        }

        return list;
    }

    @Override
    public Payments toEntity(PaymentsDto dto, Bookings bookings, Companies companies) throws ParseException {
        if ( dto == null && bookings == null && companies == null ) {
            return null;
        }

        Payments payments = new Payments();

        if ( dto != null ) {
            payments.setId( dto.getId() );
            payments.setAmount( dto.getAmount() );
            payments.setPaymentMethod( dto.getPaymentMethod() );
            payments.setStatus( dto.getStatus() );
            payments.setReference( dto.getReference() );
            payments.setTransactionId( dto.getTransactionId() );
            payments.setCurrency( dto.getCurrency() );
            payments.setNumeroTel( dto.getNumeroTel() );
            payments.setCardCredit( dto.getCardCredit() );
            payments.setCardType( dto.getCardType() );
            payments.setCardExpire( dto.getCardExpire() );
            payments.setCardHolder( dto.getCardHolder() );
            payments.setCardCvv( dto.getCardCvv() );
            payments.setCardIssuer( dto.getCardIssuer() );
            payments.setCardCountry( dto.getCardCountry() );
            if ( dto.getCreatedAt() != null ) {
                payments.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            payments.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                payments.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            payments.setUpdatedBy( dto.getUpdatedBy() );
            payments.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                payments.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            payments.setDeletedBy( dto.getDeletedBy() );
        }
        payments.setBookings( bookings );
        payments.setCompanies( companies );

        return payments;
    }

    private Integer entityBookingsId(Payments payments) {
        if ( payments == null ) {
            return null;
        }
        Bookings bookings = payments.getBookings();
        if ( bookings == null ) {
            return null;
        }
        Integer id = bookings.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityCompaniesId(Payments payments) {
        if ( payments == null ) {
            return null;
        }
        Companies companies = payments.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Payments payments) {
        if ( payments == null ) {
            return null;
        }
        Companies companies = payments.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

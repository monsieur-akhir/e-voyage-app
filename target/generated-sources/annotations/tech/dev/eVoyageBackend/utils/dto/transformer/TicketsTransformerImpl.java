package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Bookings;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Tickets;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.TicketsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T09:52:10+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class TicketsTransformerImpl implements TicketsTransformer {

    @Override
    public TicketsDto toDto(Tickets entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        TicketsDto ticketsDto = new TicketsDto();

        if ( entity.getCreatedAt() != null ) {
            ticketsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            ticketsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            ticketsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        ticketsDto.setCompanyId( entityCompaniesId( entity ) );
        ticketsDto.setCompaniesName( entityCompaniesName( entity ) );
        ticketsDto.setBookingId( entityBookingsId( entity ) );
        ticketsDto.setClientId( entityBookingsUsersId( entity ) );
        ticketsDto.setScannedBy( entityUsersId( entity ) );
        ticketsDto.setUsersName( entityUsersName( entity ) );
        ticketsDto.setId( entity.getId() );
        ticketsDto.setQrCode( entity.getQrCode() );
        ticketsDto.setStatus( entity.getStatus() );
        ticketsDto.setCreatedBy( entity.getCreatedBy() );
        ticketsDto.setUpdatedBy( entity.getUpdatedBy() );
        ticketsDto.setIsDeleted( entity.getIsDeleted() );
        ticketsDto.setDeletedBy( entity.getDeletedBy() );

        return ticketsDto;
    }

    @Override
    public List<TicketsDto> toDtos(List<Tickets> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<TicketsDto> list = new ArrayList<TicketsDto>( entities.size() );
        for ( Tickets tickets : entities ) {
            list.add( toDto( tickets ) );
        }

        return list;
    }

    @Override
    public Tickets toEntity(TicketsDto dto, Companies companies, Bookings bookings, Users users) throws ParseException {
        if ( dto == null && companies == null && bookings == null && users == null ) {
            return null;
        }

        Tickets tickets = new Tickets();

        if ( dto != null ) {
            tickets.setId( dto.getId() );
            tickets.setQrCode( dto.getQrCode() );
            tickets.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                tickets.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            tickets.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                tickets.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            tickets.setUpdatedBy( dto.getUpdatedBy() );
            tickets.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                tickets.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            tickets.setDeletedBy( dto.getDeletedBy() );
        }
        tickets.setCompanies( companies );
        tickets.setBookings( bookings );
        tickets.setUsers( users );

        return tickets;
    }

    private Integer entityCompaniesId(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }
        Companies companies = tickets.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }
        Companies companies = tickets.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityBookingsId(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }
        Bookings bookings = tickets.getBookings();
        if ( bookings == null ) {
            return null;
        }
        Integer id = bookings.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityBookingsUsersId(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }
        Bookings bookings = tickets.getBookings();
        if ( bookings == null ) {
            return null;
        }
        Users users = bookings.getUsers();
        if ( users == null ) {
            return null;
        }
        Integer id = users.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityUsersId(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }
        Users users = tickets.getUsers();
        if ( users == null ) {
            return null;
        }
        Integer id = users.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityUsersName(Tickets tickets) {
        if ( tickets == null ) {
            return null;
        }
        Users users = tickets.getUsers();
        if ( users == null ) {
            return null;
        }
        String name = users.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

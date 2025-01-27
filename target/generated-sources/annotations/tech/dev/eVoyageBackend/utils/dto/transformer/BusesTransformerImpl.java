package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Buses;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Routes;
import tech.dev.eVoyageBackend.utils.dto.BusesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class BusesTransformerImpl implements BusesTransformer {

    @Override
    public BusesDto toDto(Buses entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        BusesDto busesDto = new BusesDto();

        if ( entity.getCreatedAt() != null ) {
            busesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            busesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            busesDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        busesDto.setRouteId( entityRoutesId( entity ) );
        busesDto.setCompanyId( entityCompaniesId( entity ) );
        busesDto.setCompaniesName( entityCompaniesName( entity ) );
        busesDto.setId( entity.getId() );
        busesDto.setBusNumber( entity.getBusNumber() );
        busesDto.setCapacity( entity.getCapacity() );
        busesDto.setAvailableSeats( entity.getAvailableSeats() );
        busesDto.setSeatNumbers( entity.getSeatNumbers() );
        busesDto.setStatus( entity.getStatus() );
        busesDto.setCreatedBy( entity.getCreatedBy() );
        busesDto.setUpdatedBy( entity.getUpdatedBy() );
        busesDto.setIsDeleted( entity.getIsDeleted() );
        busesDto.setDeletedBy( entity.getDeletedBy() );

        return busesDto;
    }

    @Override
    public List<BusesDto> toDtos(List<Buses> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<BusesDto> list = new ArrayList<BusesDto>( entities.size() );
        for ( Buses buses : entities ) {
            list.add( toDto( buses ) );
        }

        return list;
    }

    @Override
    public Buses toEntity(BusesDto dto, Routes routes, Companies companies) throws ParseException {
        if ( dto == null && routes == null && companies == null ) {
            return null;
        }

        Buses buses = new Buses();

        if ( dto != null ) {
            buses.setId( dto.getId() );
            buses.setBusNumber( dto.getBusNumber() );
            buses.setCapacity( dto.getCapacity() );
            buses.setSeatNumbers( dto.getSeatNumbers() );
            buses.setAvailableSeats( dto.getAvailableSeats() );
            buses.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                buses.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            buses.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                buses.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            buses.setUpdatedBy( dto.getUpdatedBy() );
            buses.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                buses.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            buses.setDeletedBy( dto.getDeletedBy() );
        }
        buses.setRoutes( routes );
        buses.setCompanies( companies );

        return buses;
    }

    private Integer entityRoutesId(Buses buses) {
        if ( buses == null ) {
            return null;
        }
        Routes routes = buses.getRoutes();
        if ( routes == null ) {
            return null;
        }
        Integer id = routes.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityCompaniesId(Buses buses) {
        if ( buses == null ) {
            return null;
        }
        Companies companies = buses.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Buses buses) {
        if ( buses == null ) {
            return null;
        }
        Companies companies = buses.getCompanies();
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

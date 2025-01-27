package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Routes;
import tech.dev.eVoyageBackend.dao.entity.TravelSchedules;
import tech.dev.eVoyageBackend.utils.dto.TravelSchedulesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class TravelSchedulesTransformerImpl implements TravelSchedulesTransformer {

    @Override
    public TravelSchedulesDto toDto(TravelSchedules entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        TravelSchedulesDto travelSchedulesDto = new TravelSchedulesDto();

        if ( entity.getCreatedAt() != null ) {
            travelSchedulesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            travelSchedulesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        travelSchedulesDto.setRouteId( entityRoutesId( entity ) );
        travelSchedulesDto.setCompanyId( entityCompaniesId( entity ) );
        travelSchedulesDto.setCompaniesName( entityCompaniesName( entity ) );
        travelSchedulesDto.setId( entity.getId() );
        travelSchedulesDto.setDepartureStation( entity.getDepartureStation() );
        travelSchedulesDto.setArrivalStation( entity.getArrivalStation() );
        travelSchedulesDto.setDepartureTime( entity.getDepartureTime() );
        travelSchedulesDto.setArrivalTime( entity.getArrivalTime() );
        travelSchedulesDto.setTravelDate( entity.getTravelDate() );
        travelSchedulesDto.setPrice( entity.getPrice() );
        travelSchedulesDto.setStatus( entity.getStatus() );
        travelSchedulesDto.setCreatedBy( entity.getCreatedBy() );
        travelSchedulesDto.setUpdatedBy( entity.getUpdatedBy() );

        return travelSchedulesDto;
    }

    @Override
    public List<TravelSchedulesDto> toDtos(List<TravelSchedules> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<TravelSchedulesDto> list = new ArrayList<TravelSchedulesDto>( entities.size() );
        for ( TravelSchedules travelSchedules : entities ) {
            list.add( toDto( travelSchedules ) );
        }

        return list;
    }

    @Override
    public TravelSchedules toEntity(TravelSchedulesDto dto, Routes routes, Companies companies) throws ParseException {
        if ( dto == null && routes == null && companies == null ) {
            return null;
        }

        TravelSchedules travelSchedules = new TravelSchedules();

        if ( dto != null ) {
            travelSchedules.setId( dto.getId() );
            travelSchedules.setDepartureStation( dto.getDepartureStation() );
            travelSchedules.setArrivalStation( dto.getArrivalStation() );
            travelSchedules.setDepartureTime( dto.getDepartureTime() );
            travelSchedules.setArrivalTime( dto.getArrivalTime() );
            travelSchedules.setTravelDate( dto.getTravelDate() );
            travelSchedules.setPrice( dto.getPrice() );
            travelSchedules.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                travelSchedules.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            travelSchedules.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                travelSchedules.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            travelSchedules.setUpdatedBy( dto.getUpdatedBy() );
        }
        travelSchedules.setRoutes( routes );
        travelSchedules.setCompanies( companies );

        return travelSchedules;
    }

    private Integer entityRoutesId(TravelSchedules travelSchedules) {
        if ( travelSchedules == null ) {
            return null;
        }
        Routes routes = travelSchedules.getRoutes();
        if ( routes == null ) {
            return null;
        }
        Integer id = routes.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityCompaniesId(TravelSchedules travelSchedules) {
        if ( travelSchedules == null ) {
            return null;
        }
        Companies companies = travelSchedules.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(TravelSchedules travelSchedules) {
        if ( travelSchedules == null ) {
            return null;
        }
        Companies companies = travelSchedules.getCompanies();
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

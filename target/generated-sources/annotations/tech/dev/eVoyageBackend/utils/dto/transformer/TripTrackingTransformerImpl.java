package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Departs;
import tech.dev.eVoyageBackend.dao.entity.TripTracking;
import tech.dev.eVoyageBackend.utils.dto.TripTrackingDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:54+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class TripTrackingTransformerImpl implements TripTrackingTransformer {

    @Override
    public TripTrackingDto toDto(TripTracking entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        TripTrackingDto tripTrackingDto = new TripTrackingDto();

        if ( entity.getTimestamp() != null ) {
            tripTrackingDto.setTimestamp( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getTimestamp() ) );
        }
        if ( entity.getCreatedAt() != null ) {
            tripTrackingDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            tripTrackingDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        tripTrackingDto.setCompanyId( entityCompaniesId( entity ) );
        tripTrackingDto.setCompaniesName( entityCompaniesName( entity ) );
        tripTrackingDto.setDepartId( entityDepartsId( entity ) );
        tripTrackingDto.setId( entity.getId() );
        tripTrackingDto.setLocation( entity.getLocation() );
        tripTrackingDto.setStatus( entity.getStatus() );
        tripTrackingDto.setCreatedBy( entity.getCreatedBy() );
        tripTrackingDto.setUpdatedBy( entity.getUpdatedBy() );

        return tripTrackingDto;
    }

    @Override
    public List<TripTrackingDto> toDtos(List<TripTracking> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<TripTrackingDto> list = new ArrayList<TripTrackingDto>( entities.size() );
        for ( TripTracking tripTracking : entities ) {
            list.add( toDto( tripTracking ) );
        }

        return list;
    }

    @Override
    public TripTracking toEntity(TripTrackingDto dto, Companies companies, Departs departs) throws ParseException {
        if ( dto == null && companies == null && departs == null ) {
            return null;
        }

        TripTracking tripTracking = new TripTracking();

        if ( dto != null ) {
            tripTracking.setId( dto.getId() );
            tripTracking.setLocation( dto.getLocation() );
            if ( dto.getTimestamp() != null ) {
                tripTracking.setTimestamp( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getTimestamp() ) );
            }
            tripTracking.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                tripTracking.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            tripTracking.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                tripTracking.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            tripTracking.setUpdatedBy( dto.getUpdatedBy() );
        }
        tripTracking.setCompanies( companies );
        tripTracking.setDeparts( departs );

        return tripTracking;
    }

    private Integer entityCompaniesId(TripTracking tripTracking) {
        if ( tripTracking == null ) {
            return null;
        }
        Companies companies = tripTracking.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(TripTracking tripTracking) {
        if ( tripTracking == null ) {
            return null;
        }
        Companies companies = tripTracking.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityDepartsId(TripTracking tripTracking) {
        if ( tripTracking == null ) {
            return null;
        }
        Departs departs = tripTracking.getDeparts();
        if ( departs == null ) {
            return null;
        }
        Integer id = departs.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

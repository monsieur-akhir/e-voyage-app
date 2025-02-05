package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Alerts;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Departs;
import tech.dev.eVoyageBackend.utils.dto.AlertsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:23+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class AlertsTransformerImpl implements AlertsTransformer {

    @Override
    public AlertsDto toDto(Alerts entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        AlertsDto alertsDto = new AlertsDto();

        if ( entity.getCreatedAt() != null ) {
            alertsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            alertsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            alertsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        alertsDto.setCompanyId( entityCompaniesId( entity ) );
        alertsDto.setCompaniesName( entityCompaniesName( entity ) );
        alertsDto.setDepartId( entityDepartsId( entity ) );
        alertsDto.setId( entity.getId() );
        alertsDto.setType( entity.getType() );
        alertsDto.setMessage( entity.getMessage() );
        alertsDto.setStatus( entity.getStatus() );
        alertsDto.setCreatedBy( entity.getCreatedBy() );
        alertsDto.setUpdatedBy( entity.getUpdatedBy() );
        alertsDto.setIsDeleted( entity.getIsDeleted() );
        alertsDto.setDeletedBy( entity.getDeletedBy() );

        return alertsDto;
    }

    @Override
    public List<AlertsDto> toDtos(List<Alerts> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<AlertsDto> list = new ArrayList<AlertsDto>( entities.size() );
        for ( Alerts alerts : entities ) {
            list.add( toDto( alerts ) );
        }

        return list;
    }

    @Override
    public Alerts toEntity(AlertsDto dto, Companies companies, Departs departs) throws ParseException {
        if ( dto == null && companies == null && departs == null ) {
            return null;
        }

        Alerts alerts = new Alerts();

        if ( dto != null ) {
            alerts.setId( dto.getId() );
            alerts.setType( dto.getType() );
            alerts.setMessage( dto.getMessage() );
            alerts.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                alerts.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            alerts.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                alerts.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            alerts.setUpdatedBy( dto.getUpdatedBy() );
            alerts.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                alerts.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            alerts.setDeletedBy( dto.getDeletedBy() );
        }
        alerts.setCompanies( companies );
        alerts.setDeparts( departs );

        return alerts;
    }

    private Integer entityCompaniesId(Alerts alerts) {
        if ( alerts == null ) {
            return null;
        }
        Companies companies = alerts.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Alerts alerts) {
        if ( alerts == null ) {
            return null;
        }
        Companies companies = alerts.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityDepartsId(Alerts alerts) {
        if ( alerts == null ) {
            return null;
        }
        Departs departs = alerts.getDeparts();
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

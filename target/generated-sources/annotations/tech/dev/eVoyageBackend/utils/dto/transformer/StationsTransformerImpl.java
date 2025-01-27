package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.utils.dto.StationsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class StationsTransformerImpl implements StationsTransformer {

    @Override
    public StationsDto toDto(Stations entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        StationsDto stationsDto = new StationsDto();

        if ( entity.getCreatedAt() != null ) {
            stationsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            stationsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            stationsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        stationsDto.setCompanyId( entityCompaniesId( entity ) );
        stationsDto.setCompaniesName( entityCompaniesName( entity ) );
        stationsDto.setCityId( entityCitiesId( entity ) );
        stationsDto.setCitiesName( entityCitiesName( entity ) );
        stationsDto.setId( entity.getId() );
        stationsDto.setName( entity.getName() );
        stationsDto.setAddress( entity.getAddress() );
        stationsDto.setIsAvailable( entity.getIsAvailable() );
        stationsDto.setCreatedBy( entity.getCreatedBy() );
        stationsDto.setUpdatedBy( entity.getUpdatedBy() );
        stationsDto.setIsDeleted( entity.getIsDeleted() );
        stationsDto.setDeletedBy( entity.getDeletedBy() );

        return stationsDto;
    }

    @Override
    public List<StationsDto> toDtos(List<Stations> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<StationsDto> list = new ArrayList<StationsDto>( entities.size() );
        for ( Stations stations : entities ) {
            list.add( toDto( stations ) );
        }

        return list;
    }

    @Override
    public Stations toEntity(StationsDto dto, Companies companies, Cities cities) throws ParseException {
        if ( dto == null && companies == null && cities == null ) {
            return null;
        }

        Stations stations = new Stations();

        if ( dto != null ) {
            stations.setId( dto.getId() );
            stations.setName( dto.getName() );
            stations.setAddress( dto.getAddress() );
            stations.setIsAvailable( dto.getIsAvailable() );
            if ( dto.getCreatedAt() != null ) {
                stations.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            stations.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                stations.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            stations.setUpdatedBy( dto.getUpdatedBy() );
            stations.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                stations.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            stations.setDeletedBy( dto.getDeletedBy() );
        }
        stations.setCompanies( companies );
        stations.setCities( cities );

        return stations;
    }

    private Integer entityCompaniesId(Stations stations) {
        if ( stations == null ) {
            return null;
        }
        Companies companies = stations.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Stations stations) {
        if ( stations == null ) {
            return null;
        }
        Companies companies = stations.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityCitiesId(Stations stations) {
        if ( stations == null ) {
            return null;
        }
        Cities cities = stations.getCities();
        if ( cities == null ) {
            return null;
        }
        Integer id = cities.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCitiesName(Stations stations) {
        if ( stations == null ) {
            return null;
        }
        Cities cities = stations.getCities();
        if ( cities == null ) {
            return null;
        }
        String name = cities.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Routes;
import tech.dev.eVoyageBackend.utils.dto.RoutesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class RoutesTransformerImpl implements RoutesTransformer {

    @Override
    public RoutesDto toDto(Routes entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        RoutesDto routesDto = new RoutesDto();

        if ( entity.getCreatedAt() != null ) {
            routesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            routesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            routesDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        routesDto.setOriginCityId( entityCitiesId( entity ) );
        routesDto.setCitiesNameOrigin( entityCitiesName( entity ) );
        routesDto.setCompanyId( entityCompaniesId( entity ) );
        routesDto.setCompaniesName( entityCompaniesName( entity ) );
        routesDto.setDestinationCityId( entityCities2Id( entity ) );
        routesDto.setCitiesNameDestination( entityCities2Name( entity ) );
        routesDto.setId( entity.getId() );
        routesDto.setDuration( entity.getDuration() );
        routesDto.setPrice( entity.getPrice() );
        routesDto.setStatus( entity.getStatus() );
        routesDto.setRating( entity.getRating() );
        routesDto.setCreatedBy( entity.getCreatedBy() );
        routesDto.setUpdatedBy( entity.getUpdatedBy() );
        routesDto.setIsDeleted( entity.getIsDeleted() );
        routesDto.setDeletedBy( entity.getDeletedBy() );

        return routesDto;
    }

    @Override
    public List<RoutesDto> toDtos(List<Routes> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<RoutesDto> list = new ArrayList<RoutesDto>( entities.size() );
        for ( Routes routes : entities ) {
            list.add( toDto( routes ) );
        }

        return list;
    }

    @Override
    public Routes toEntity(RoutesDto dto, Cities cities, Companies companies, Cities cities2) throws ParseException {
        if ( dto == null && cities == null && companies == null && cities2 == null ) {
            return null;
        }

        Routes routes = new Routes();

        if ( dto != null ) {
            routes.setId( dto.getId() );
            routes.setDuration( dto.getDuration() );
            routes.setPrice( dto.getPrice() );
            routes.setStatus( dto.getStatus() );
            routes.setRating( dto.getRating() );
            if ( dto.getCreatedAt() != null ) {
                routes.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            routes.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                routes.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            routes.setUpdatedBy( dto.getUpdatedBy() );
            routes.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                routes.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            routes.setDeletedBy( dto.getDeletedBy() );
        }
        routes.setCities( cities );
        routes.setCompanies( companies );
        routes.setCities2( cities2 );

        return routes;
    }

    private Integer entityCitiesId(Routes routes) {
        if ( routes == null ) {
            return null;
        }
        Cities cities = routes.getCities();
        if ( cities == null ) {
            return null;
        }
        Integer id = cities.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCitiesName(Routes routes) {
        if ( routes == null ) {
            return null;
        }
        Cities cities = routes.getCities();
        if ( cities == null ) {
            return null;
        }
        String name = cities.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityCompaniesId(Routes routes) {
        if ( routes == null ) {
            return null;
        }
        Companies companies = routes.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Routes routes) {
        if ( routes == null ) {
            return null;
        }
        Companies companies = routes.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityCities2Id(Routes routes) {
        if ( routes == null ) {
            return null;
        }
        Cities cities2 = routes.getCities2();
        if ( cities2 == null ) {
            return null;
        }
        Integer id = cities2.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCities2Name(Routes routes) {
        if ( routes == null ) {
            return null;
        }
        Cities cities2 = routes.getCities2();
        if ( cities2 == null ) {
            return null;
        }
        String name = cities2.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

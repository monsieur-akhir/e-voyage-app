package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Buses;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Departs;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.utils.dto.DepartsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:54+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class DepartsTransformerImpl implements DepartsTransformer {

    @Override
    public DepartsDto toDto(Departs entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        DepartsDto departsDto = new DepartsDto();

        if ( entity.getCreatedAt() != null ) {
            departsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            departsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            departsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        departsDto.setBusId( entityBusesId( entity ) );
        departsDto.setBusNumber( entityBusesBusNumber( entity ) );
        departsDto.setCompaniesName( entityBusesCompaniesName( entity ) );
        departsDto.setDestinationStationId( entityStations2Id( entity ) );
        departsDto.setStationsNameArrival( entityStations2Name( entity ) );
        departsDto.setVilleArrivee( entityStations2CitiesName( entity ) );
        departsDto.setVilleDepart( entityStationsCitiesName( entity ) );
        departsDto.setOriginStationId( entityStationsId( entity ) );
        departsDto.setStationsNameDeparture( entityStationsName( entity ) );
        departsDto.setId( entity.getId() );
        departsDto.setDepartureDate( entity.getDepartureDate() );
        departsDto.setDepartureTime( entity.getDepartureTime() );
        departsDto.setPrice( entity.getPrice() );
        departsDto.setMaxSeats( entity.getMaxSeats() );
        departsDto.setAvailableSeats( entity.getAvailableSeats() );
        departsDto.setRating( entity.getRating() );
        departsDto.setDuration( entity.getDuration() );
        departsDto.setIsActive( entity.getIsActive() );
        departsDto.setCreatedBy( entity.getCreatedBy() );
        departsDto.setUpdatedBy( entity.getUpdatedBy() );
        departsDto.setIsDeleted( entity.getIsDeleted() );
        departsDto.setDeletedBy( entity.getDeletedBy() );

        return departsDto;
    }

    @Override
    public List<DepartsDto> toDtos(List<Departs> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<DepartsDto> list = new ArrayList<DepartsDto>( entities.size() );
        for ( Departs departs : entities ) {
            list.add( toDto( departs ) );
        }

        return list;
    }

    @Override
    public Departs toEntity(DepartsDto dto, Buses buses, Stations stations2, Stations stations) throws ParseException {
        if ( dto == null && buses == null && stations2 == null && stations == null ) {
            return null;
        }

        Departs departs = new Departs();

        if ( dto != null ) {
            departs.setId( dto.getId() );
            departs.setDepartureDate( dto.getDepartureDate() );
            departs.setDepartureTime( dto.getDepartureTime() );
            departs.setPrice( dto.getPrice() );
            departs.setMaxSeats( dto.getMaxSeats() );
            departs.setAvailableSeats( dto.getAvailableSeats() );
            departs.setRating( dto.getRating() );
            departs.setDuration( dto.getDuration() );
            departs.setIsActive( dto.getIsActive() );
            if ( dto.getCreatedAt() != null ) {
                departs.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            departs.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                departs.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            departs.setUpdatedBy( dto.getUpdatedBy() );
            departs.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                departs.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            departs.setDeletedBy( dto.getDeletedBy() );
        }
        departs.setBuses( buses );
        departs.setStations2( stations2 );
        departs.setStations( stations );

        return departs;
    }

    private Integer entityBusesId(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Buses buses = departs.getBuses();
        if ( buses == null ) {
            return null;
        }
        Integer id = buses.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityBusesBusNumber(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Buses buses = departs.getBuses();
        if ( buses == null ) {
            return null;
        }
        String busNumber = buses.getBusNumber();
        if ( busNumber == null ) {
            return null;
        }
        return busNumber;
    }

    private String entityBusesCompaniesName(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Buses buses = departs.getBuses();
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

    private Integer entityStations2Id(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Stations stations2 = departs.getStations2();
        if ( stations2 == null ) {
            return null;
        }
        Integer id = stations2.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityStations2Name(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Stations stations2 = departs.getStations2();
        if ( stations2 == null ) {
            return null;
        }
        String name = stations2.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityStations2CitiesName(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Stations stations2 = departs.getStations2();
        if ( stations2 == null ) {
            return null;
        }
        Cities cities = stations2.getCities();
        if ( cities == null ) {
            return null;
        }
        String name = cities.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityStationsCitiesName(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Stations stations = departs.getStations();
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

    private Integer entityStationsId(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Stations stations = departs.getStations();
        if ( stations == null ) {
            return null;
        }
        Integer id = stations.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityStationsName(Departs departs) {
        if ( departs == null ) {
            return null;
        }
        Stations stations = departs.getStations();
        if ( stations == null ) {
            return null;
        }
        String name = stations.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Bookings;
import tech.dev.eVoyageBackend.dao.entity.Buses;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Departs;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.BookingsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:21+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class BookingsTransformerImpl implements BookingsTransformer {

    @Override
    public BookingsDto toDto(Bookings entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        BookingsDto bookingsDto = new BookingsDto();

        if ( entity.getCreatedAt() != null ) {
            bookingsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            bookingsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            bookingsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        bookingsDto.setUserId( entityUsersId( entity ) );
        bookingsDto.setUsersName( entityUsersName( entity ) );
        bookingsDto.setUsersEmail( entityUsersEmail( entity ) );
        bookingsDto.setUsersPhone( entityUsersPhone( entity ) );
        bookingsDto.setBusId( entityBusesId( entity ) );
        bookingsDto.setNumeroCar( entityBusesBusNumber( entity ) );
        bookingsDto.setDestinationStationId( entityStations2Id( entity ) );
        bookingsDto.setStationsArrivalName( entityStations2Name( entity ) );
        bookingsDto.setVilleArrivee( entityStations2CitiesName( entity ) );
        bookingsDto.setDepartureId( entityDepartsId( entity ) );
        bookingsDto.setOriginStationId( entityStationsId( entity ) );
        bookingsDto.setStationsDepartureName( entityStationsName( entity ) );
        bookingsDto.setVilleDepart( entityStationsCitiesName( entity ) );
        bookingsDto.setCompanyId( entityCompaniesId( entity ) );
        bookingsDto.setCompaniesName( entityCompaniesName( entity ) );
        bookingsDto.setId( entity.getId() );
        bookingsDto.setSeatNumber( entity.getSeatNumber() );
        bookingsDto.setNumberOfSeats( entity.getNumberOfSeats() );
        bookingsDto.setStatus( entity.getStatus() );
        bookingsDto.setCreatedBy( entity.getCreatedBy() );
        bookingsDto.setUpdatedBy( entity.getUpdatedBy() );
        bookingsDto.setIsDeleted( entity.getIsDeleted() );
        bookingsDto.setDeletedBy( entity.getDeletedBy() );

        return bookingsDto;
    }

    @Override
    public List<BookingsDto> toDtos(List<Bookings> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<BookingsDto> list = new ArrayList<BookingsDto>( entities.size() );
        for ( Bookings bookings : entities ) {
            list.add( toDto( bookings ) );
        }

        return list;
    }

    @Override
    public Bookings toEntity(BookingsDto dto, Users users, Buses buses, Stations stations2, Departs departs, Stations stations, Companies companies) throws ParseException {
        if ( dto == null && users == null && buses == null && stations2 == null && departs == null && stations == null && companies == null ) {
            return null;
        }

        Bookings bookings = new Bookings();

        if ( dto != null ) {
            bookings.setId( dto.getId() );
            bookings.setSeatNumber( dto.getSeatNumber() );
            bookings.setNumberOfSeats( dto.getNumberOfSeats() );
            bookings.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                bookings.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            bookings.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                bookings.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            bookings.setUpdatedBy( dto.getUpdatedBy() );
            bookings.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                bookings.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            bookings.setDeletedBy( dto.getDeletedBy() );
        }
        bookings.setUsers( users );
        bookings.setBuses( buses );
        bookings.setStations2( stations2 );
        bookings.setDeparts( departs );
        bookings.setStations( stations );
        bookings.setCompanies( companies );

        return bookings;
    }

    private Integer entityUsersId(Bookings bookings) {
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

    private String entityUsersName(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Users users = bookings.getUsers();
        if ( users == null ) {
            return null;
        }
        String name = users.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityUsersEmail(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Users users = bookings.getUsers();
        if ( users == null ) {
            return null;
        }
        String email = users.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String entityUsersPhone(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Users users = bookings.getUsers();
        if ( users == null ) {
            return null;
        }
        String phone = users.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private Integer entityBusesId(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Buses buses = bookings.getBuses();
        if ( buses == null ) {
            return null;
        }
        Integer id = buses.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityBusesBusNumber(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Buses buses = bookings.getBuses();
        if ( buses == null ) {
            return null;
        }
        String busNumber = buses.getBusNumber();
        if ( busNumber == null ) {
            return null;
        }
        return busNumber;
    }

    private Integer entityStations2Id(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Stations stations2 = bookings.getStations2();
        if ( stations2 == null ) {
            return null;
        }
        Integer id = stations2.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityStations2Name(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Stations stations2 = bookings.getStations2();
        if ( stations2 == null ) {
            return null;
        }
        String name = stations2.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityStations2CitiesName(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Stations stations2 = bookings.getStations2();
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

    private Integer entityDepartsId(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Departs departs = bookings.getDeparts();
        if ( departs == null ) {
            return null;
        }
        Integer id = departs.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityStationsId(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Stations stations = bookings.getStations();
        if ( stations == null ) {
            return null;
        }
        Integer id = stations.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityStationsName(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Stations stations = bookings.getStations();
        if ( stations == null ) {
            return null;
        }
        String name = stations.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityStationsCitiesName(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Stations stations = bookings.getStations();
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

    private Integer entityCompaniesId(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Companies companies = bookings.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Bookings bookings) {
        if ( bookings == null ) {
            return null;
        }
        Companies companies = bookings.getCompanies();
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

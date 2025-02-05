package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.utils.dto.CitiesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:21+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class CitiesTransformerImpl implements CitiesTransformer {

    @Override
    public CitiesDto toDto(Cities entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        CitiesDto citiesDto = new CitiesDto();

        if ( entity.getCreatedAt() != null ) {
            citiesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            citiesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            citiesDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        citiesDto.setId( entity.getId() );
        citiesDto.setName( entity.getName() );
        citiesDto.setIsAvailable( entity.getIsAvailable() );
        citiesDto.setCreatedBy( entity.getCreatedBy() );
        citiesDto.setUpdatedBy( entity.getUpdatedBy() );
        citiesDto.setIsDeleted( entity.getIsDeleted() );
        citiesDto.setDeletedBy( entity.getDeletedBy() );

        return citiesDto;
    }

    @Override
    public List<CitiesDto> toDtos(List<Cities> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<CitiesDto> list = new ArrayList<CitiesDto>( entities.size() );
        for ( Cities cities : entities ) {
            list.add( toDto( cities ) );
        }

        return list;
    }

    @Override
    public Cities toEntity(CitiesDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        Cities cities = new Cities();

        cities.setId( dto.getId() );
        cities.setName( dto.getName() );
        cities.setIsAvailable( dto.getIsAvailable() );
        if ( dto.getCreatedAt() != null ) {
            cities.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        cities.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getUpdatedAt() != null ) {
            cities.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        cities.setUpdatedBy( dto.getUpdatedBy() );
        cities.setIsDeleted( dto.getIsDeleted() );
        if ( dto.getDeletedAt() != null ) {
            cities.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
        }
        cities.setDeletedBy( dto.getDeletedBy() );

        return cities;
    }
}

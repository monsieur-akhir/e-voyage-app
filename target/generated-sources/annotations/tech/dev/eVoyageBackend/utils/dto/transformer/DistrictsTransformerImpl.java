package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.Districts;
import tech.dev.eVoyageBackend.utils.dto.DistrictsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T09:52:16+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class DistrictsTransformerImpl implements DistrictsTransformer {

    @Override
    public DistrictsDto toDto(Districts entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        DistrictsDto districtsDto = new DistrictsDto();

        if ( entity.getCreatedAt() != null ) {
            districtsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            districtsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            districtsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        districtsDto.setCityId( entityCitiesId( entity ) );
        districtsDto.setCitiesName( entityCitiesName( entity ) );
        districtsDto.setId( entity.getId() );
        districtsDto.setName( entity.getName() );
        districtsDto.setIsAvailable( entity.getIsAvailable() );
        districtsDto.setCreatedBy( entity.getCreatedBy() );
        districtsDto.setUpdatedBy( entity.getUpdatedBy() );
        districtsDto.setIsDeleted( entity.getIsDeleted() );
        districtsDto.setDeletedBy( entity.getDeletedBy() );

        return districtsDto;
    }

    @Override
    public List<DistrictsDto> toDtos(List<Districts> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<DistrictsDto> list = new ArrayList<DistrictsDto>( entities.size() );
        for ( Districts districts : entities ) {
            list.add( toDto( districts ) );
        }

        return list;
    }

    @Override
    public Districts toEntity(DistrictsDto dto, Cities cities) throws ParseException {
        if ( dto == null && cities == null ) {
            return null;
        }

        Districts districts = new Districts();

        if ( dto != null ) {
            districts.setId( dto.getId() );
            districts.setName( dto.getName() );
            districts.setIsAvailable( dto.getIsAvailable() );
            if ( dto.getCreatedAt() != null ) {
                districts.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            districts.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                districts.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            districts.setUpdatedBy( dto.getUpdatedBy() );
            districts.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                districts.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            districts.setDeletedBy( dto.getDeletedBy() );
        }
        districts.setCities( cities );

        return districts;
    }

    private Integer entityCitiesId(Districts districts) {
        if ( districts == null ) {
            return null;
        }
        Cities cities = districts.getCities();
        if ( cities == null ) {
            return null;
        }
        Integer id = cities.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCitiesName(Districts districts) {
        if ( districts == null ) {
            return null;
        }
        Cities cities = districts.getCities();
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

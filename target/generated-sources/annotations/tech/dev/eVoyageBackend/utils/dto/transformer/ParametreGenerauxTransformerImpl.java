package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.ParametreGeneraux;
import tech.dev.eVoyageBackend.utils.dto.ParametreGenerauxDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:22+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class ParametreGenerauxTransformerImpl implements ParametreGenerauxTransformer {

    @Override
    public ParametreGenerauxDto toDto(ParametreGeneraux entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        ParametreGenerauxDto parametreGenerauxDto = new ParametreGenerauxDto();

        if ( entity.getCreatedAt() != null ) {
            parametreGenerauxDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            parametreGenerauxDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            parametreGenerauxDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        parametreGenerauxDto.setId( entity.getId() );
        parametreGenerauxDto.setCreatedBy( entity.getCreatedBy() );
        parametreGenerauxDto.setDeletedBy( entity.getDeletedBy() );
        parametreGenerauxDto.setIsDeleted( entity.getIsDeleted() );
        parametreGenerauxDto.setParamName( entity.getParamName() );
        parametreGenerauxDto.setParamValue( entity.getParamValue() );
        parametreGenerauxDto.setUpdatedBy( entity.getUpdatedBy() );

        return parametreGenerauxDto;
    }

    @Override
    public List<ParametreGenerauxDto> toDtos(List<ParametreGeneraux> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<ParametreGenerauxDto> list = new ArrayList<ParametreGenerauxDto>( entities.size() );
        for ( ParametreGeneraux parametreGeneraux : entities ) {
            list.add( toDto( parametreGeneraux ) );
        }

        return list;
    }

    @Override
    public ParametreGeneraux toEntity(ParametreGenerauxDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        ParametreGeneraux parametreGeneraux = new ParametreGeneraux();

        parametreGeneraux.setId( dto.getId() );
        if ( dto.getCreatedAt() != null ) {
            parametreGeneraux.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        parametreGeneraux.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getDeletedAt() != null ) {
            parametreGeneraux.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
        }
        parametreGeneraux.setDeletedBy( dto.getDeletedBy() );
        parametreGeneraux.setIsDeleted( dto.getIsDeleted() );
        parametreGeneraux.setParamName( dto.getParamName() );
        parametreGeneraux.setParamValue( dto.getParamValue() );
        if ( dto.getUpdatedAt() != null ) {
            parametreGeneraux.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        parametreGeneraux.setUpdatedBy( dto.getUpdatedBy() );

        return parametreGeneraux;
    }
}

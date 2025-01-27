package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Permissions;
import tech.dev.eVoyageBackend.utils.dto.PermissionsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class PermissionsTransformerImpl implements PermissionsTransformer {

    @Override
    public PermissionsDto toDto(Permissions entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        PermissionsDto permissionsDto = new PermissionsDto();

        if ( entity.getCreatedAt() != null ) {
            permissionsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            permissionsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        permissionsDto.setId( entity.getId() );
        permissionsDto.setName( entity.getName() );
        permissionsDto.setDescription( entity.getDescription() );
        permissionsDto.setCreatedBy( entity.getCreatedBy() );
        permissionsDto.setUpdatedBy( entity.getUpdatedBy() );

        return permissionsDto;
    }

    @Override
    public List<PermissionsDto> toDtos(List<Permissions> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<PermissionsDto> list = new ArrayList<PermissionsDto>( entities.size() );
        for ( Permissions permissions : entities ) {
            list.add( toDto( permissions ) );
        }

        return list;
    }

    @Override
    public Permissions toEntity(PermissionsDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        Permissions permissions = new Permissions();

        permissions.setId( dto.getId() );
        permissions.setName( dto.getName() );
        permissions.setDescription( dto.getDescription() );
        if ( dto.getCreatedAt() != null ) {
            permissions.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        permissions.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getUpdatedAt() != null ) {
            permissions.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        permissions.setUpdatedBy( dto.getUpdatedBy() );

        return permissions;
    }
}

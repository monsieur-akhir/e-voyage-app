package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Roles;
import tech.dev.eVoyageBackend.utils.dto.RolesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:54+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class RolesTransformerImpl implements RolesTransformer {

    @Override
    public RolesDto toDto(Roles entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        RolesDto rolesDto = new RolesDto();

        if ( entity.getCreatedAt() != null ) {
            rolesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            rolesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            rolesDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        rolesDto.setId( entity.getId() );
        rolesDto.setName( entity.getName() );
        rolesDto.setStatus( entity.getStatus() );
        rolesDto.setCreatedBy( entity.getCreatedBy() );
        rolesDto.setUpdatedBy( entity.getUpdatedBy() );
        rolesDto.setIsDeleted( entity.getIsDeleted() );
        rolesDto.setDeletedBy( entity.getDeletedBy() );

        return rolesDto;
    }

    @Override
    public List<RolesDto> toDtos(List<Roles> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<RolesDto> list = new ArrayList<RolesDto>( entities.size() );
        for ( Roles roles : entities ) {
            list.add( toDto( roles ) );
        }

        return list;
    }

    @Override
    public Roles toEntity(RolesDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        Roles roles = new Roles();

        roles.setId( dto.getId() );
        roles.setName( dto.getName() );
        roles.setStatus( dto.getStatus() );
        if ( dto.getCreatedAt() != null ) {
            roles.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        roles.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getUpdatedAt() != null ) {
            roles.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        roles.setUpdatedBy( dto.getUpdatedBy() );
        roles.setIsDeleted( dto.getIsDeleted() );
        if ( dto.getDeletedAt() != null ) {
            roles.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
        }
        roles.setDeletedBy( dto.getDeletedBy() );

        return roles;
    }
}

package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.utils.dto.RoleDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:21+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class RoleTransformerImpl implements RoleTransformer {

    @Override
    public RoleDto toDto(Role entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( entity.getCreatedAt() != null ) {
            roleDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            roleDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        roleDto.setId( entity.getId() );
        roleDto.setCreatedBy( entity.getCreatedBy() );
        roleDto.setIsDeleted( entity.getIsDeleted() );
        roleDto.setLibelle( entity.getLibelle() );
        roleDto.setUpdatedBy( entity.getUpdatedBy() );

        return roleDto;
    }

    @Override
    public List<RoleDto> toDtos(List<Role> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entities.size() );
        for ( Role role : entities ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    @Override
    public Role toEntity(RoleDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        if ( dto.getCreatedAt() != null ) {
            role.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        role.setCreatedBy( dto.getCreatedBy() );
        role.setIsDeleted( dto.getIsDeleted() );
        role.setLibelle( dto.getLibelle() );
        if ( dto.getUpdatedAt() != null ) {
            role.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        role.setUpdatedBy( dto.getUpdatedBy() );

        return role;
    }
}

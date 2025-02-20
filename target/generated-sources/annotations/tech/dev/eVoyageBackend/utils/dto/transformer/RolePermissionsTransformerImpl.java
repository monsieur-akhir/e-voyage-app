package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Permissions;
import tech.dev.eVoyageBackend.dao.entity.RolePermissions;
import tech.dev.eVoyageBackend.dao.entity.Roles;
import tech.dev.eVoyageBackend.utils.dto.RolePermissionsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T09:52:16+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class RolePermissionsTransformerImpl implements RolePermissionsTransformer {

    @Override
    public RolePermissionsDto toDto(RolePermissions entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        RolePermissionsDto rolePermissionsDto = new RolePermissionsDto();

        if ( entity.getCreatedAt() != null ) {
            rolePermissionsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            rolePermissionsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        rolePermissionsDto.setRoleId( entityRolesId( entity ) );
        rolePermissionsDto.setRolesName( entityRolesName( entity ) );
        rolePermissionsDto.setPermissionId( entityPermissionsId( entity ) );
        rolePermissionsDto.setPermissionsName( entityPermissionsName( entity ) );
        rolePermissionsDto.setId( entity.getId() );
        rolePermissionsDto.setStatus( entity.getStatus() );
        rolePermissionsDto.setCreatedBy( entity.getCreatedBy() );
        rolePermissionsDto.setUpdatedBy( entity.getUpdatedBy() );

        return rolePermissionsDto;
    }

    @Override
    public List<RolePermissionsDto> toDtos(List<RolePermissions> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<RolePermissionsDto> list = new ArrayList<RolePermissionsDto>( entities.size() );
        for ( RolePermissions rolePermissions : entities ) {
            list.add( toDto( rolePermissions ) );
        }

        return list;
    }

    @Override
    public RolePermissions toEntity(RolePermissionsDto dto, Roles roles, Permissions permissions) throws ParseException {
        if ( dto == null && roles == null && permissions == null ) {
            return null;
        }

        RolePermissions rolePermissions = new RolePermissions();

        if ( dto != null ) {
            rolePermissions.setId( dto.getId() );
            rolePermissions.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                rolePermissions.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            rolePermissions.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                rolePermissions.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            rolePermissions.setUpdatedBy( dto.getUpdatedBy() );
        }
        rolePermissions.setRoles( roles );
        rolePermissions.setPermissions( permissions );

        return rolePermissions;
    }

    private Integer entityRolesId(RolePermissions rolePermissions) {
        if ( rolePermissions == null ) {
            return null;
        }
        Roles roles = rolePermissions.getRoles();
        if ( roles == null ) {
            return null;
        }
        Integer id = roles.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityRolesName(RolePermissions rolePermissions) {
        if ( rolePermissions == null ) {
            return null;
        }
        Roles roles = rolePermissions.getRoles();
        if ( roles == null ) {
            return null;
        }
        String name = roles.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityPermissionsId(RolePermissions rolePermissions) {
        if ( rolePermissions == null ) {
            return null;
        }
        Permissions permissions = rolePermissions.getPermissions();
        if ( permissions == null ) {
            return null;
        }
        Integer id = permissions.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityPermissionsName(RolePermissions rolePermissions) {
        if ( rolePermissions == null ) {
            return null;
        }
        Permissions permissions = rolePermissions.getPermissions();
        if ( permissions == null ) {
            return null;
        }
        String name = permissions.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

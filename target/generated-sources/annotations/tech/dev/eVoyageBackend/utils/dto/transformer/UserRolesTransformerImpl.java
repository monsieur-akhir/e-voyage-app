package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Roles;
import tech.dev.eVoyageBackend.dao.entity.UserRoles;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.UserRolesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class UserRolesTransformerImpl implements UserRolesTransformer {

    @Override
    public UserRolesDto toDto(UserRoles entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        UserRolesDto userRolesDto = new UserRolesDto();

        if ( entity.getCreatedAt() != null ) {
            userRolesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            userRolesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            userRolesDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        userRolesDto.setRoleId( entityRolesId( entity ) );
        userRolesDto.setRolesName( entityRolesName( entity ) );
        userRolesDto.setUserId( entityUsersId( entity ) );
        userRolesDto.setUsersName( entityUsersName( entity ) );
        userRolesDto.setId( entity.getId() );
        userRolesDto.setCreatedBy( entity.getCreatedBy() );
        userRolesDto.setUpdatedBy( entity.getUpdatedBy() );
        userRolesDto.setIsDeleted( entity.getIsDeleted() );
        userRolesDto.setDeletedBy( entity.getDeletedBy() );

        return userRolesDto;
    }

    @Override
    public List<UserRolesDto> toDtos(List<UserRoles> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<UserRolesDto> list = new ArrayList<UserRolesDto>( entities.size() );
        for ( UserRoles userRoles : entities ) {
            list.add( toDto( userRoles ) );
        }

        return list;
    }

    @Override
    public UserRoles toEntity(UserRolesDto dto, Roles roles, Users users) throws ParseException {
        if ( dto == null && roles == null && users == null ) {
            return null;
        }

        UserRoles userRoles = new UserRoles();

        if ( dto != null ) {
            userRoles.setId( dto.getId() );
            if ( dto.getCreatedAt() != null ) {
                userRoles.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            userRoles.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                userRoles.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            userRoles.setUpdatedBy( dto.getUpdatedBy() );
            userRoles.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                userRoles.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            userRoles.setDeletedBy( dto.getDeletedBy() );
        }
        userRoles.setRoles( roles );
        userRoles.setUsers( users );

        return userRoles;
    }

    private Integer entityRolesId(UserRoles userRoles) {
        if ( userRoles == null ) {
            return null;
        }
        Roles roles = userRoles.getRoles();
        if ( roles == null ) {
            return null;
        }
        Integer id = roles.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityRolesName(UserRoles userRoles) {
        if ( userRoles == null ) {
            return null;
        }
        Roles roles = userRoles.getRoles();
        if ( roles == null ) {
            return null;
        }
        String name = roles.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityUsersId(UserRoles userRoles) {
        if ( userRoles == null ) {
            return null;
        }
        Users users = userRoles.getUsers();
        if ( users == null ) {
            return null;
        }
        Integer id = users.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityUsersName(UserRoles userRoles) {
        if ( userRoles == null ) {
            return null;
        }
        Users users = userRoles.getUsers();
        if ( users == null ) {
            return null;
        }
        String name = users.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

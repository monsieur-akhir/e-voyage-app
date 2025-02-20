package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.utils.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T09:52:13+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class UserTransformerImpl implements UserTransformer {

    @Override
    public UserDto toDto(User entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( entity.getBornOn() != null ) {
            userDto.setBornOn( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getBornOn() ) );
        }
        if ( entity.getLastConnectionDate() != null ) {
            userDto.setLastConnectionDate( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getLastConnectionDate() ) );
        }
        if ( entity.getFirstConnection() != null ) {
            userDto.setFirstConnection( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getFirstConnection() ) );
        }
        if ( entity.getLastLockDate() != null ) {
            userDto.setLastLockDate( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getLastLockDate() ) );
        }
        if ( entity.getPassCodeExpireAt() != null ) {
            userDto.setPassCodeExpireAt( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getPassCodeExpireAt() ) );
        }
        if ( entity.getPassCodeCreatedAt() != null ) {
            userDto.setPassCodeCreatedAt( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getPassCodeCreatedAt() ) );
        }
        if ( entity.getTokenCreatedAt() != null ) {
            userDto.setTokenCreatedAt( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getTokenCreatedAt() ) );
        }
        if ( entity.getTokenExpireAt() != null ) {
            userDto.setTokenExpireAt( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( entity.getTokenExpireAt() ) );
        }
        if ( entity.getCreatedAt() != null ) {
            userDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            userDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        userDto.setRoleId( entityRoleId( entity ) );
        userDto.setRoleLibelle( entityRoleLibelle( entity ) );
        userDto.setId( entity.getId() );
        userDto.setLogin( entity.getLogin() );
        userDto.setFirstName( entity.getFirstName() );
        userDto.setLastName( entity.getLastName() );
        userDto.setFonction( entity.getFonction() );
        userDto.setLieuFonction( entity.getLieuFonction() );
        userDto.setEmail( entity.getEmail() );
        userDto.setOtpCode( entity.getOtpCode() );
        if ( entity.getDateSendCodeOtpAt() != null ) {
            userDto.setDateSendCodeOtpAt( new SimpleDateFormat().format( entity.getDateSendCodeOtpAt() ) );
        }
        userDto.setTelephone( entity.getTelephone() );
        userDto.setIsDefaultPassword( entity.getIsDefaultPassword() );
        userDto.setIsConnected( entity.getIsConnected() );
        userDto.setIsLocked( entity.getIsLocked() );
        userDto.setPassCode( entity.getPassCode() );
        userDto.setIsValidPassCode( entity.getIsValidPassCode() );
        userDto.setToken( entity.getToken() );
        userDto.setIsValidToken( entity.getIsValidToken() );
        userDto.setCreatedBy( entity.getCreatedBy() );
        userDto.setUpdatedBy( entity.getUpdatedBy() );
        userDto.setLoginAttempts( entity.getLoginAttempts() );
        userDto.setIsActive( entity.getIsActive() );
        userDto.setIsDeleted( entity.getIsDeleted() );
        userDto.setIsLdapUser( entity.getIsLdapUser() );

        return userDto;
    }

    @Override
    public List<UserDto> toDtos(List<User> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entities.size() );
        for ( User user : entities ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDto dto, Role role) throws ParseException {
        if ( dto == null && role == null ) {
            return null;
        }

        User user = new User();

        if ( dto != null ) {
            user.setId( dto.getId() );
            user.setLogin( dto.getLogin() );
            user.setPassword( dto.getPassword() );
            user.setFirstName( dto.getFirstName() );
            user.setLastName( dto.getLastName() );
            user.setFonction( dto.getFonction() );
            user.setLieuFonction( dto.getLieuFonction() );
            user.setEmail( dto.getEmail() );
            user.setOtpCode( dto.getOtpCode() );
            if ( dto.getBornOn() != null ) {
                user.setBornOn( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getBornOn() ) );
            }
            user.setTelephone( dto.getTelephone() );
            user.setIsDefaultPassword( dto.getIsDefaultPassword() );
            user.setIsConnected( dto.getIsConnected() );
            user.setIsLocked( dto.getIsLocked() );
            if ( dto.getLastConnectionDate() != null ) {
                user.setLastConnectionDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getLastConnectionDate() ) );
            }
            if ( dto.getFirstConnection() != null ) {
                user.setFirstConnection( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getFirstConnection() ) );
            }
            if ( dto.getLastLockDate() != null ) {
                user.setLastLockDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getLastLockDate() ) );
            }
            user.setPassCode( dto.getPassCode() );
            user.setIsValidPassCode( dto.getIsValidPassCode() );
            if ( dto.getPassCodeExpireAt() != null ) {
                user.setPassCodeExpireAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getPassCodeExpireAt() ) );
            }
            if ( dto.getPassCodeCreatedAt() != null ) {
                user.setPassCodeCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getPassCodeCreatedAt() ) );
            }
            user.setToken( dto.getToken() );
            user.setIsValidToken( dto.getIsValidToken() );
            if ( dto.getTokenCreatedAt() != null ) {
                user.setTokenCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getTokenCreatedAt() ) );
            }
            if ( dto.getTokenExpireAt() != null ) {
                user.setTokenExpireAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getTokenExpireAt() ) );
            }
            if ( dto.getCreatedAt() != null ) {
                user.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            user.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                user.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            user.setUpdatedBy( dto.getUpdatedBy() );
            user.setLoginAttempts( dto.getLoginAttempts() );
            user.setIsActive( dto.getIsActive() );
            user.setSearchString( dto.getSearchString() );
            user.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDateSendCodeOtpAt() != null ) {
                user.setDateSendCodeOtpAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDateSendCodeOtpAt() ) );
            }
            user.setIsLdapUser( dto.getIsLdapUser() );
        }
        user.setRole( role );

        return user;
    }

    private Integer entityRoleId(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        Integer id = role.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityRoleLibelle(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        String libelle = role.getLibelle();
        if ( libelle == null ) {
            return null;
        }
        return libelle;
    }
}

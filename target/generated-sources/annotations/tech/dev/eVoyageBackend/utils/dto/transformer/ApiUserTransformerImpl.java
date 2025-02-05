package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.ApiUser;
import tech.dev.eVoyageBackend.utils.dto.ApiUserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:22+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class ApiUserTransformerImpl implements ApiUserTransformer {

    @Override
    public ApiUserDto toDto(ApiUser entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        ApiUserDto apiUserDto = new ApiUserDto();

        if ( entity.getCreatedAt() != null ) {
            apiUserDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getDateSendCodeOtpAt() != null ) {
            apiUserDto.setDateSendCodeOtpAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDateSendCodeOtpAt() ) );
        }
        if ( entity.getFirstConnection() != null ) {
            apiUserDto.setFirstConnection( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getFirstConnection() ) );
        }
        if ( entity.getLastActivityDate() != null ) {
            apiUserDto.setLastActivityDate( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getLastActivityDate() ) );
        }
        if ( entity.getLastConnectionDate() != null ) {
            apiUserDto.setLastConnectionDate( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getLastConnectionDate() ) );
        }
        if ( entity.getLastLockDate() != null ) {
            apiUserDto.setLastLockDate( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getLastLockDate() ) );
        }
        if ( entity.getPassCodeCreatedAt() != null ) {
            apiUserDto.setPassCodeCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getPassCodeCreatedAt() ) );
        }
        if ( entity.getPassCodeExpireAt() != null ) {
            apiUserDto.setPassCodeExpireAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getPassCodeExpireAt() ) );
        }
        if ( entity.getTokenCreatedAt() != null ) {
            apiUserDto.setTokenCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getTokenCreatedAt() ) );
        }
        if ( entity.getTokenExpireAt() != null ) {
            apiUserDto.setTokenExpireAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getTokenExpireAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            apiUserDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        apiUserDto.setId( entity.getId() );
        apiUserDto.setCreatedBy( entity.getCreatedBy() );
        apiUserDto.setEmail( entity.getEmail() );
        apiUserDto.setIsActive( entity.getIsActive() );
        apiUserDto.setIsConnected( entity.getIsConnected() );
        apiUserDto.setIsDefaultPassword( entity.getIsDefaultPassword() );
        apiUserDto.setIsDeleted( entity.getIsDeleted() );
        apiUserDto.setIsLocked( entity.getIsLocked() );
        apiUserDto.setIsValidPassCode( entity.getIsValidPassCode() );
        apiUserDto.setIsValidToken( entity.getIsValidToken() );
        apiUserDto.setLogin( entity.getLogin() );
        apiUserDto.setLoginAttempts( entity.getLoginAttempts() );
        apiUserDto.setOtpCode( entity.getOtpCode() );
        apiUserDto.setPassCode( entity.getPassCode() );
        apiUserDto.setPassword( entity.getPassword() );
        apiUserDto.setSearchString( entity.getSearchString() );
        apiUserDto.setTelephone( entity.getTelephone() );
        apiUserDto.setToken( entity.getToken() );
        apiUserDto.setUpdatedBy( entity.getUpdatedBy() );
        apiUserDto.setRoleId( entity.getRoleId() );
        apiUserDto.setType( entity.getType() );

        return apiUserDto;
    }

    @Override
    public List<ApiUserDto> toDtos(List<ApiUser> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<ApiUserDto> list = new ArrayList<ApiUserDto>( entities.size() );
        for ( ApiUser apiUser : entities ) {
            list.add( toDto( apiUser ) );
        }

        return list;
    }

    @Override
    public ApiUser toEntity(ApiUserDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        ApiUser apiUser = new ApiUser();

        apiUser.setId( dto.getId() );
        if ( dto.getCreatedAt() != null ) {
            apiUser.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        apiUser.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getDateSendCodeOtpAt() != null ) {
            apiUser.setDateSendCodeOtpAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDateSendCodeOtpAt() ) );
        }
        apiUser.setEmail( dto.getEmail() );
        if ( dto.getFirstConnection() != null ) {
            apiUser.setFirstConnection( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getFirstConnection() ) );
        }
        apiUser.setIsActive( dto.getIsActive() );
        apiUser.setIsConnected( dto.getIsConnected() );
        apiUser.setIsDefaultPassword( dto.getIsDefaultPassword() );
        apiUser.setIsDeleted( dto.getIsDeleted() );
        apiUser.setIsLocked( dto.getIsLocked() );
        apiUser.setIsValidPassCode( dto.getIsValidPassCode() );
        apiUser.setIsValidToken( dto.getIsValidToken() );
        if ( dto.getLastActivityDate() != null ) {
            apiUser.setLastActivityDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getLastActivityDate() ) );
        }
        if ( dto.getLastConnectionDate() != null ) {
            apiUser.setLastConnectionDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getLastConnectionDate() ) );
        }
        if ( dto.getLastLockDate() != null ) {
            apiUser.setLastLockDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getLastLockDate() ) );
        }
        apiUser.setLogin( dto.getLogin() );
        apiUser.setLoginAttempts( dto.getLoginAttempts() );
        apiUser.setOtpCode( dto.getOtpCode() );
        apiUser.setPassCode( dto.getPassCode() );
        if ( dto.getPassCodeCreatedAt() != null ) {
            apiUser.setPassCodeCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getPassCodeCreatedAt() ) );
        }
        if ( dto.getPassCodeExpireAt() != null ) {
            apiUser.setPassCodeExpireAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getPassCodeExpireAt() ) );
        }
        apiUser.setPassword( dto.getPassword() );
        apiUser.setSearchString( dto.getSearchString() );
        apiUser.setTelephone( dto.getTelephone() );
        apiUser.setToken( dto.getToken() );
        if ( dto.getTokenCreatedAt() != null ) {
            apiUser.setTokenCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getTokenCreatedAt() ) );
        }
        if ( dto.getTokenExpireAt() != null ) {
            apiUser.setTokenExpireAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getTokenExpireAt() ) );
        }
        if ( dto.getUpdatedAt() != null ) {
            apiUser.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        apiUser.setUpdatedBy( dto.getUpdatedBy() );
        apiUser.setRoleId( dto.getRoleId() );
        apiUser.setType( dto.getType() );

        return apiUser;
    }
}

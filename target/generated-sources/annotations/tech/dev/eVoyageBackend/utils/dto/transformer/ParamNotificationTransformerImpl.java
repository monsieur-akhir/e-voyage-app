package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.ParamNotification;
import tech.dev.eVoyageBackend.utils.dto.ParamNotificationDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class ParamNotificationTransformerImpl implements ParamNotificationTransformer {

    @Override
    public ParamNotificationDto toDto(ParamNotification entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        ParamNotificationDto paramNotificationDto = new ParamNotificationDto();

        if ( entity.getCreatedAt() != null ) {
            paramNotificationDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            paramNotificationDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        paramNotificationDto.setId( entity.getId() );
        paramNotificationDto.setCreatedBy( entity.getCreatedBy() );
        paramNotificationDto.setIsDeleted( entity.getIsDeleted() );
        paramNotificationDto.setLang( entity.getLang() );
        paramNotificationDto.setMessage( entity.getMessage() );
        paramNotificationDto.setType( entity.getType() );
        paramNotificationDto.setUpdatedBy( entity.getUpdatedBy() );

        return paramNotificationDto;
    }

    @Override
    public List<ParamNotificationDto> toDtos(List<ParamNotification> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<ParamNotificationDto> list = new ArrayList<ParamNotificationDto>( entities.size() );
        for ( ParamNotification paramNotification : entities ) {
            list.add( toDto( paramNotification ) );
        }

        return list;
    }

    @Override
    public ParamNotification toEntity(ParamNotificationDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        ParamNotification paramNotification = new ParamNotification();

        paramNotification.setId( dto.getId() );
        if ( dto.getCreatedAt() != null ) {
            paramNotification.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        paramNotification.setCreatedBy( dto.getCreatedBy() );
        paramNotification.setIsDeleted( dto.getIsDeleted() );
        paramNotification.setLang( dto.getLang() );
        paramNotification.setMessage( dto.getMessage() );
        paramNotification.setType( dto.getType() );
        if ( dto.getUpdatedAt() != null ) {
            paramNotification.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        paramNotification.setUpdatedBy( dto.getUpdatedBy() );

        return paramNotification;
    }
}

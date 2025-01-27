package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Notifications;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.NotificationsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:52+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class NotificationsTransformerImpl implements NotificationsTransformer {

    @Override
    public NotificationsDto toDto(Notifications entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        NotificationsDto notificationsDto = new NotificationsDto();

        if ( entity.getCreatedAt() != null ) {
            notificationsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            notificationsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            notificationsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        notificationsDto.setUserId( entityUsersId( entity ) );
        notificationsDto.setUsersName( entityUsersName( entity ) );
        notificationsDto.setCompanyId( entityCompaniesId( entity ) );
        notificationsDto.setCompaniesName( entityCompaniesName( entity ) );
        notificationsDto.setId( entity.getId() );
        notificationsDto.setType( entity.getType() );
        notificationsDto.setContent( entity.getContent() );
        notificationsDto.setStatus( entity.getStatus() );
        notificationsDto.setCreatedBy( entity.getCreatedBy() );
        notificationsDto.setUpdatedBy( entity.getUpdatedBy() );
        notificationsDto.setIsDeleted( entity.getIsDeleted() );
        notificationsDto.setDeletedBy( entity.getDeletedBy() );

        return notificationsDto;
    }

    @Override
    public List<NotificationsDto> toDtos(List<Notifications> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<NotificationsDto> list = new ArrayList<NotificationsDto>( entities.size() );
        for ( Notifications notifications : entities ) {
            list.add( toDto( notifications ) );
        }

        return list;
    }

    @Override
    public Notifications toEntity(NotificationsDto dto, Users users, Companies companies) throws ParseException {
        if ( dto == null && users == null && companies == null ) {
            return null;
        }

        Notifications notifications = new Notifications();

        if ( dto != null ) {
            notifications.setId( dto.getId() );
            notifications.setType( dto.getType() );
            notifications.setContent( dto.getContent() );
            notifications.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                notifications.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            notifications.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                notifications.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            notifications.setUpdatedBy( dto.getUpdatedBy() );
            notifications.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                notifications.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            notifications.setDeletedBy( dto.getDeletedBy() );
        }
        notifications.setUsers( users );
        notifications.setCompanies( companies );

        return notifications;
    }

    private Integer entityUsersId(Notifications notifications) {
        if ( notifications == null ) {
            return null;
        }
        Users users = notifications.getUsers();
        if ( users == null ) {
            return null;
        }
        Integer id = users.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityUsersName(Notifications notifications) {
        if ( notifications == null ) {
            return null;
        }
        Users users = notifications.getUsers();
        if ( users == null ) {
            return null;
        }
        String name = users.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer entityCompaniesId(Notifications notifications) {
        if ( notifications == null ) {
            return null;
        }
        Companies companies = notifications.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Notifications notifications) {
        if ( notifications == null ) {
            return null;
        }
        Companies companies = notifications.getCompanies();
        if ( companies == null ) {
            return null;
        }
        String name = companies.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

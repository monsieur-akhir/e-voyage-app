package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Logs;
import tech.dev.eVoyageBackend.utils.dto.LogsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:21+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class LogsTransformerImpl implements LogsTransformer {

    @Override
    public LogsDto toDto(Logs entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        LogsDto logsDto = new LogsDto();

        if ( entity.getCreatedAt() != null ) {
            logsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getDate() != null ) {
            logsDto.setDate( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDate() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            logsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        logsDto.setId( entity.getId() );
        logsDto.setActionService( entity.getActionService() );
        logsDto.setCreatedBy( entity.getCreatedBy() );
        logsDto.setIdStatus( entity.getIdStatus() );
        logsDto.setIpadress( entity.getIpadress() );
        logsDto.setIsConnexion( entity.getIsConnexion() );
        logsDto.setIsDeleted( entity.getIsDeleted() );
        logsDto.setLogin( entity.getLogin() );
        logsDto.setMachine( entity.getMachine() );
        logsDto.setNom( entity.getNom() );
        logsDto.setPrenom( entity.getPrenom() );
        logsDto.setSearchString( entity.getSearchString() );
        logsDto.setStatusConnection( entity.getStatusConnection() );
        logsDto.setUpdatedBy( entity.getUpdatedBy() );
        logsDto.setUri( entity.getUri() );

        return logsDto;
    }

    @Override
    public List<LogsDto> toDtos(List<Logs> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<LogsDto> list = new ArrayList<LogsDto>( entities.size() );
        for ( Logs logs : entities ) {
            list.add( toDto( logs ) );
        }

        return list;
    }

    @Override
    public Logs toEntity(LogsDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        Logs logs = new Logs();

        logs.setId( dto.getId() );
        logs.setActionService( dto.getActionService() );
        if ( dto.getCreatedAt() != null ) {
            logs.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        logs.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getDate() != null ) {
            logs.setDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDate() ) );
        }
        logs.setIdStatus( dto.getIdStatus() );
        logs.setIpadress( dto.getIpadress() );
        logs.setIsConnexion( dto.getIsConnexion() );
        logs.setIsDeleted( dto.getIsDeleted() );
        logs.setLogin( dto.getLogin() );
        logs.setMachine( dto.getMachine() );
        logs.setNom( dto.getNom() );
        logs.setPrenom( dto.getPrenom() );
        logs.setRequest( dto.getRequest() );
        logs.setResponse( dto.getResponse() );
        logs.setSearchString( dto.getSearchString() );
        logs.setStatusConnection( dto.getStatusConnection() );
        if ( dto.getUpdatedAt() != null ) {
            logs.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        logs.setUpdatedBy( dto.getUpdatedBy() );
        logs.setUri( dto.getUri() );

        return logs;
    }
}

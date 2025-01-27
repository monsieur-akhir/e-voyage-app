package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.ApiLogs;
import tech.dev.eVoyageBackend.utils.dto.ApiLogsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:54+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class ApiLogsTransformerImpl implements ApiLogsTransformer {

    @Override
    public ApiLogsDto toDto(ApiLogs entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        ApiLogsDto apiLogsDto = new ApiLogsDto();

        if ( entity.getRequestTime() != null ) {
            apiLogsDto.setRequestTime( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getRequestTime() ) );
        }
        if ( entity.getResponseTime() != null ) {
            apiLogsDto.setResponseTime( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getResponseTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            apiLogsDto.setCreatedDate( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedDate() ) );
        }
        if ( entity.getLastModifiedDate() != null ) {
            apiLogsDto.setLastModifiedDate( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getLastModifiedDate() ) );
        }
        apiLogsDto.setId( entity.getId() );
        apiLogsDto.setRequestUrl( entity.getRequestUrl() );
        apiLogsDto.setRequestMethod( entity.getRequestMethod() );
        apiLogsDto.setRequestHeaders( entity.getRequestHeaders() );
        apiLogsDto.setRequestBody( entity.getRequestBody() );
        apiLogsDto.setResponseStatus( entity.getResponseStatus() );
        apiLogsDto.setResponseBody( entity.getResponseBody() );
        apiLogsDto.setCreatedBy( entity.getCreatedBy() );
        apiLogsDto.setLastModifiedBy( entity.getLastModifiedBy() );
        apiLogsDto.setStatus( entity.getStatus() );

        return apiLogsDto;
    }

    @Override
    public List<ApiLogsDto> toDtos(List<ApiLogs> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<ApiLogsDto> list = new ArrayList<ApiLogsDto>( entities.size() );
        for ( ApiLogs apiLogs : entities ) {
            list.add( toDto( apiLogs ) );
        }

        return list;
    }

    @Override
    public ApiLogs toEntity(ApiLogsDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        ApiLogs apiLogs = new ApiLogs();

        apiLogs.setId( dto.getId() );
        if ( dto.getRequestTime() != null ) {
            apiLogs.setRequestTime( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getRequestTime() ) );
        }
        apiLogs.setRequestUrl( dto.getRequestUrl() );
        apiLogs.setRequestMethod( dto.getRequestMethod() );
        apiLogs.setRequestHeaders( dto.getRequestHeaders() );
        apiLogs.setRequestBody( dto.getRequestBody() );
        if ( dto.getResponseTime() != null ) {
            apiLogs.setResponseTime( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getResponseTime() ) );
        }
        apiLogs.setResponseStatus( dto.getResponseStatus() );
        apiLogs.setResponseBody( dto.getResponseBody() );
        apiLogs.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getCreatedDate() != null ) {
            apiLogs.setCreatedDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedDate() ) );
        }
        apiLogs.setLastModifiedBy( dto.getLastModifiedBy() );
        if ( dto.getLastModifiedDate() != null ) {
            apiLogs.setLastModifiedDate( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getLastModifiedDate() ) );
        }
        apiLogs.setStatus( dto.getStatus() );

        return apiLogs;
    }
}

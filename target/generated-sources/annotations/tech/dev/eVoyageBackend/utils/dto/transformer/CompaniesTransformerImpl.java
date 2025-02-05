package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.utils.dto.CompaniesDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T15:57:22+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class CompaniesTransformerImpl implements CompaniesTransformer {

    @Override
    public CompaniesDto toDto(Companies entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        CompaniesDto companiesDto = new CompaniesDto();

        if ( entity.getCreatedAt() != null ) {
            companiesDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            companiesDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            companiesDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        companiesDto.setId( entity.getId() );
        companiesDto.setName( entity.getName() );
        companiesDto.setAddress( entity.getAddress() );
        companiesDto.setContact( entity.getContact() );
        companiesDto.setLicenseNumber( entity.getLicenseNumber() );
        companiesDto.setRating( entity.getRating() );
        companiesDto.setStatus( entity.getStatus() );
        companiesDto.setLogoPath( entity.getLogoPath() );
        companiesDto.setCreatedBy( entity.getCreatedBy() );
        companiesDto.setUpdatedBy( entity.getUpdatedBy() );
        companiesDto.setIsDeleted( entity.getIsDeleted() );
        companiesDto.setDeletedBy( entity.getDeletedBy() );

        return companiesDto;
    }

    @Override
    public List<CompaniesDto> toDtos(List<Companies> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<CompaniesDto> list = new ArrayList<CompaniesDto>( entities.size() );
        for ( Companies companies : entities ) {
            list.add( toDto( companies ) );
        }

        return list;
    }

    @Override
    public Companies toEntity(CompaniesDto dto) throws ParseException {
        if ( dto == null ) {
            return null;
        }

        Companies companies = new Companies();

        companies.setId( dto.getId() );
        companies.setName( dto.getName() );
        companies.setAddress( dto.getAddress() );
        companies.setContact( dto.getContact() );
        companies.setLicenseNumber( dto.getLicenseNumber() );
        companies.setRating( dto.getRating() );
        companies.setStatus( dto.getStatus() );
        companies.setLogoPath( dto.getLogoPath() );
        if ( dto.getCreatedAt() != null ) {
            companies.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
        }
        companies.setCreatedBy( dto.getCreatedBy() );
        if ( dto.getUpdatedAt() != null ) {
            companies.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
        }
        companies.setUpdatedBy( dto.getUpdatedBy() );
        companies.setIsDeleted( dto.getIsDeleted() );
        if ( dto.getDeletedAt() != null ) {
            companies.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
        }
        companies.setDeletedBy( dto.getDeletedBy() );

        return companies;
    }
}

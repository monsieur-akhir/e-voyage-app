package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.FinancialReports;
import tech.dev.eVoyageBackend.utils.dto.FinancialReportsDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T09:52:11+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class FinancialReportsTransformerImpl implements FinancialReportsTransformer {

    @Override
    public FinancialReportsDto toDto(FinancialReports entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        FinancialReportsDto financialReportsDto = new FinancialReportsDto();

        if ( entity.getCreatedAt() != null ) {
            financialReportsDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            financialReportsDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            financialReportsDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        financialReportsDto.setCompanyId( entityCompaniesId( entity ) );
        financialReportsDto.setCompaniesName( entityCompaniesName( entity ) );
        financialReportsDto.setId( entity.getId() );
        financialReportsDto.setTotalRevenue( entity.getTotalRevenue() );
        financialReportsDto.setTotalBookings( entity.getTotalBookings() );
        financialReportsDto.setReportDate( entity.getReportDate() );
        financialReportsDto.setStatus( entity.getStatus() );
        financialReportsDto.setCreatedBy( entity.getCreatedBy() );
        financialReportsDto.setUpdatedBy( entity.getUpdatedBy() );
        financialReportsDto.setIsDeleted( entity.getIsDeleted() );
        financialReportsDto.setDeletedBy( entity.getDeletedBy() );

        return financialReportsDto;
    }

    @Override
    public List<FinancialReportsDto> toDtos(List<FinancialReports> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<FinancialReportsDto> list = new ArrayList<FinancialReportsDto>( entities.size() );
        for ( FinancialReports financialReports : entities ) {
            list.add( toDto( financialReports ) );
        }

        return list;
    }

    @Override
    public FinancialReports toEntity(FinancialReportsDto dto, Companies companies) throws ParseException {
        if ( dto == null && companies == null ) {
            return null;
        }

        FinancialReports financialReports = new FinancialReports();

        if ( dto != null ) {
            financialReports.setId( dto.getId() );
            financialReports.setTotalRevenue( dto.getTotalRevenue() );
            financialReports.setTotalBookings( dto.getTotalBookings() );
            financialReports.setReportDate( dto.getReportDate() );
            financialReports.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                financialReports.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            financialReports.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                financialReports.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            financialReports.setUpdatedBy( dto.getUpdatedBy() );
            financialReports.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                financialReports.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            financialReports.setDeletedBy( dto.getDeletedBy() );
        }
        financialReports.setCompanies( companies );

        return financialReports;
    }

    private Integer entityCompaniesId(FinancialReports financialReports) {
        if ( financialReports == null ) {
            return null;
        }
        Companies companies = financialReports.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(FinancialReports financialReports) {
        if ( financialReports == null ) {
            return null;
        }
        Companies companies = financialReports.getCompanies();
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

package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.utils.dto.UsersDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class UsersTransformerImpl implements UsersTransformer {

    @Override
    public UsersDto toDto(Users entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        UsersDto usersDto = new UsersDto();

        if ( entity.getCreatedAt() != null ) {
            usersDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            usersDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        if ( entity.getDeletedAt() != null ) {
            usersDto.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getDeletedAt() ) );
        }
        usersDto.setCompanyId( entityCompaniesId( entity ) );
        usersDto.setCompaniesName( entityCompaniesName( entity ) );
        usersDto.setId( entity.getId() );
        usersDto.setName( entity.getName() );
        usersDto.setEmail( entity.getEmail() );
        usersDto.setPhone( entity.getPhone() );
        usersDto.setPassword( entity.getPassword() );
        usersDto.setRoleId( entity.getRoleId() );
        usersDto.setStatus( entity.getStatus() );
        usersDto.setCreatedBy( entity.getCreatedBy() );
        usersDto.setUpdatedBy( entity.getUpdatedBy() );
        usersDto.setIsDeleted( entity.getIsDeleted() );
        usersDto.setDeletedBy( entity.getDeletedBy() );

        return usersDto;
    }

    @Override
    public List<UsersDto> toDtos(List<Users> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<UsersDto> list = new ArrayList<UsersDto>( entities.size() );
        for ( Users users : entities ) {
            list.add( toDto( users ) );
        }

        return list;
    }

    @Override
    public Users toEntity(UsersDto dto, Companies companies) throws ParseException {
        if ( dto == null && companies == null ) {
            return null;
        }

        Users users = new Users();

        if ( dto != null ) {
            users.setId( dto.getId() );
            users.setName( dto.getName() );
            users.setEmail( dto.getEmail() );
            users.setPhone( dto.getPhone() );
            users.setPassword( dto.getPassword() );
            users.setRoleId( dto.getRoleId() );
            users.setStatus( dto.getStatus() );
            if ( dto.getCreatedAt() != null ) {
                users.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            users.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                users.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            users.setUpdatedBy( dto.getUpdatedBy() );
            users.setIsDeleted( dto.getIsDeleted() );
            if ( dto.getDeletedAt() != null ) {
                users.setDeletedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getDeletedAt() ) );
            }
            users.setDeletedBy( dto.getDeletedBy() );
        }
        users.setCompanies( companies );

        return users;
    }

    private Integer entityCompaniesId(Users users) {
        if ( users == null ) {
            return null;
        }
        Companies companies = users.getCompanies();
        if ( companies == null ) {
            return null;
        }
        Integer id = companies.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompaniesName(Users users) {
        if ( users == null ) {
            return null;
        }
        Companies companies = users.getCompanies();
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

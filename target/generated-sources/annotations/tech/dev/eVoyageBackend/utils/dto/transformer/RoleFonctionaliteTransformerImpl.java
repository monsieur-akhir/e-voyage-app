package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.dao.entity.RoleFonctionalite;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T09:52:12+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class RoleFonctionaliteTransformerImpl implements RoleFonctionaliteTransformer {

    @Override
    public RoleFonctionaliteDto toDto(RoleFonctionalite entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        RoleFonctionaliteDto roleFonctionaliteDto = new RoleFonctionaliteDto();

        if ( entity.getCreatedAt() != null ) {
            roleFonctionaliteDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            roleFonctionaliteDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        roleFonctionaliteDto.setRoleId( entityRoleId( entity ) );
        roleFonctionaliteDto.setRoleLibelle( entityRoleLibelle( entity ) );
        roleFonctionaliteDto.setFonctionnaliteId( entityFonctionaliteId( entity ) );
        roleFonctionaliteDto.setFonctionaliteCode( entityFonctionaliteCode( entity ) );
        roleFonctionaliteDto.setFonctionaliteLibelle( entityFonctionaliteLibelle( entity ) );
        roleFonctionaliteDto.setId( entity.getId() );
        roleFonctionaliteDto.setCreatedBy( entity.getCreatedBy() );
        roleFonctionaliteDto.setUpdatedBy( entity.getUpdatedBy() );
        roleFonctionaliteDto.setIsDeleted( entity.getIsDeleted() );

        return roleFonctionaliteDto;
    }

    @Override
    public List<RoleFonctionaliteDto> toDtos(List<RoleFonctionalite> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<RoleFonctionaliteDto> list = new ArrayList<RoleFonctionaliteDto>( entities.size() );
        for ( RoleFonctionalite roleFonctionalite : entities ) {
            list.add( toDto( roleFonctionalite ) );
        }

        return list;
    }

    @Override
    public RoleFonctionalite toEntity(RoleFonctionaliteDto dto, Role role, Fonctionalite fonctionalite) throws ParseException {
        if ( dto == null && role == null && fonctionalite == null ) {
            return null;
        }

        RoleFonctionalite roleFonctionalite = new RoleFonctionalite();

        if ( dto != null ) {
            roleFonctionalite.setId( dto.getId() );
            if ( dto.getCreatedAt() != null ) {
                roleFonctionalite.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            if ( dto.getUpdatedAt() != null ) {
                roleFonctionalite.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            roleFonctionalite.setCreatedBy( dto.getCreatedBy() );
            roleFonctionalite.setUpdatedBy( dto.getUpdatedBy() );
            roleFonctionalite.setIsDeleted( dto.getIsDeleted() );
        }
        roleFonctionalite.setRole( role );
        roleFonctionalite.setFonctionalite( fonctionalite );

        return roleFonctionalite;
    }

    private Integer entityRoleId(RoleFonctionalite roleFonctionalite) {
        if ( roleFonctionalite == null ) {
            return null;
        }
        Role role = roleFonctionalite.getRole();
        if ( role == null ) {
            return null;
        }
        Integer id = role.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityRoleLibelle(RoleFonctionalite roleFonctionalite) {
        if ( roleFonctionalite == null ) {
            return null;
        }
        Role role = roleFonctionalite.getRole();
        if ( role == null ) {
            return null;
        }
        String libelle = role.getLibelle();
        if ( libelle == null ) {
            return null;
        }
        return libelle;
    }

    private Integer entityFonctionaliteId(RoleFonctionalite roleFonctionalite) {
        if ( roleFonctionalite == null ) {
            return null;
        }
        Fonctionalite fonctionalite = roleFonctionalite.getFonctionalite();
        if ( fonctionalite == null ) {
            return null;
        }
        Integer id = fonctionalite.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityFonctionaliteCode(RoleFonctionalite roleFonctionalite) {
        if ( roleFonctionalite == null ) {
            return null;
        }
        Fonctionalite fonctionalite = roleFonctionalite.getFonctionalite();
        if ( fonctionalite == null ) {
            return null;
        }
        String code = fonctionalite.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityFonctionaliteLibelle(RoleFonctionalite roleFonctionalite) {
        if ( roleFonctionalite == null ) {
            return null;
        }
        Fonctionalite fonctionalite = roleFonctionalite.getFonctionalite();
        if ( fonctionalite == null ) {
            return null;
        }
        String libelle = fonctionalite.getLibelle();
        if ( libelle == null ) {
            return null;
        }
        return libelle;
    }
}

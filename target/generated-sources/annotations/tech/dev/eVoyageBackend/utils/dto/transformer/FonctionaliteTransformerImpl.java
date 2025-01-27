package tech.dev.eVoyageBackend.utils.dto.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.utils.dto.FonctionaliteDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T13:28:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
public class FonctionaliteTransformerImpl implements FonctionaliteTransformer {

    @Override
    public FonctionaliteDto toDto(Fonctionalite entity) throws ParseException {
        if ( entity == null ) {
            return null;
        }

        FonctionaliteDto fonctionaliteDto = new FonctionaliteDto();

        if ( entity.getCreatedAt() != null ) {
            fonctionaliteDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            fonctionaliteDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).format( entity.getUpdatedAt() ) );
        }
        fonctionaliteDto.setParentId( entityFonctionaliteId( entity ) );
        fonctionaliteDto.setFonctionaliteCode( entityFonctionaliteCode( entity ) );
        fonctionaliteDto.setFonctionaliteLibelle( entityFonctionaliteLibelle( entity ) );
        fonctionaliteDto.setId( entity.getId() );
        fonctionaliteDto.setCode( entity.getCode() );
        fonctionaliteDto.setLibelle( entity.getLibelle() );
        fonctionaliteDto.setIsAvailableForUser( entity.getIsAvailableForUser() );
        fonctionaliteDto.setCreatedBy( entity.getCreatedBy() );
        fonctionaliteDto.setUpdatedBy( entity.getUpdatedBy() );
        fonctionaliteDto.setIsDeleted( entity.getIsDeleted() );

        return fonctionaliteDto;
    }

    @Override
    public List<FonctionaliteDto> toDtos(List<Fonctionalite> entities) throws ParseException {
        if ( entities == null ) {
            return null;
        }

        List<FonctionaliteDto> list = new ArrayList<FonctionaliteDto>( entities.size() );
        for ( Fonctionalite fonctionalite : entities ) {
            list.add( toDto( fonctionalite ) );
        }

        return list;
    }

    @Override
    public Fonctionalite toEntity(FonctionaliteDto dto, Fonctionalite fonctionalite) throws ParseException {
        if ( dto == null && fonctionalite == null ) {
            return null;
        }

        Fonctionalite fonctionalite1 = new Fonctionalite();

        if ( dto != null ) {
            fonctionalite1.setId( dto.getId() );
            fonctionalite1.setCode( dto.getCode() );
            fonctionalite1.setLibelle( dto.getLibelle() );
            fonctionalite1.setIsAvailableForUser( dto.getIsAvailableForUser() );
            if ( dto.getCreatedAt() != null ) {
                fonctionalite1.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getCreatedAt() ) );
            }
            fonctionalite1.setCreatedBy( dto.getCreatedBy() );
            if ( dto.getUpdatedAt() != null ) {
                fonctionalite1.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy" ).parse( dto.getUpdatedAt() ) );
            }
            fonctionalite1.setUpdatedBy( dto.getUpdatedBy() );
            fonctionalite1.setIsDeleted( dto.getIsDeleted() );
        }
        fonctionalite1.setFonctionalite( fonctionalite );

        return fonctionalite1;
    }

    private Integer entityFonctionaliteId(Fonctionalite fonctionalite) {
        if ( fonctionalite == null ) {
            return null;
        }
        Fonctionalite fonctionalite1 = fonctionalite.getFonctionalite();
        if ( fonctionalite1 == null ) {
            return null;
        }
        Integer id = fonctionalite1.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityFonctionaliteCode(Fonctionalite fonctionalite) {
        if ( fonctionalite == null ) {
            return null;
        }
        Fonctionalite fonctionalite1 = fonctionalite.getFonctionalite();
        if ( fonctionalite1 == null ) {
            return null;
        }
        String code = fonctionalite1.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityFonctionaliteLibelle(Fonctionalite fonctionalite) {
        if ( fonctionalite == null ) {
            return null;
        }
        Fonctionalite fonctionalite1 = fonctionalite.getFonctionalite();
        if ( fonctionalite1 == null ) {
            return null;
        }
        String libelle = fonctionalite1.getLibelle();
        if ( libelle == null ) {
            return null;
        }
        return libelle;
    }
}

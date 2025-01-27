
/*
 * Java dto for entity table role 
 * Created on 2024-08-20 ( Time 10:24:21 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.dto.customize;

import java.util.Date;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.FonctionaliteDto;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;

/**
 * DTO customize for table "role"
 * 
 * @author Geo
 *
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _RoleDto {

    private List<RoleFonctionaliteDto> datasFonctionalites;
    private List<FonctionaliteDto> datasFonctionalite;

}


/*
 * Java dto for entity table users 
 * Created on 2025-01-12 ( Time 17:40:01 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.dto.customize;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import tech.dev.eVoyageBackend.utils.contract.*;

/**
 * DTO customize for table "users"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _UsersDto {

    private String token;
    private String refreshToken;

}

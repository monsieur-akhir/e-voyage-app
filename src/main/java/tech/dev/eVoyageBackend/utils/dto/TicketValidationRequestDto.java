package tech.dev.eVoyageBackend.utils.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class TicketValidationRequestDto {
    private String base64QrCode;

    // Constructeurs
    public TicketValidationRequestDto() {}

    public TicketValidationRequestDto(String base64QrCode) {
        this.base64QrCode = base64QrCode;
    }

}

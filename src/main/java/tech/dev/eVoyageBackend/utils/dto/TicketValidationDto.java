package tech.dev.eVoyageBackend.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)

public class TicketValidationDto {
    private String bookingId;
    private String clientId;
    private String clientName;
    private String departureCity;
    private String departureStation;
    private String destinationCity;
    private String destinationStation;
    private String compagnie;
    private String status;

    // Constructeur mis à jour
    public TicketValidationDto(String bookingId, String clientId, String clientName, String departureCity, String departureStation, String destinationCity, String destinationStation, String compagnie, String status) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.departureCity = departureCity;
        this.departureStation = departureStation;
        this.destinationCity = destinationCity;
        this.destinationStation = destinationStation;
        this.compagnie = compagnie;
        this.status = status;
    }

    // Getters & Setters (si nécessaire)

}

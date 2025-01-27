package tech.dev.eVoyageBackend.utils.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class DownloadTicketRequest {

    @NotBlank(message = "Le chemin du fichier est obligatoire.")
    private String filePath;

    @NotBlank(message = "Le token utilisateur est obligatoire.")
    private String token;

}

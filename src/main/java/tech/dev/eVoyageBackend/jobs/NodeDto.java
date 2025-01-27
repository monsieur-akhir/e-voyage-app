package tech.dev.eVoyageBackend.jobs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeDto {

    private String name;
    private String ip;
    private String lastPing;
    private String lastExecution;
    private String createdAt;
    private boolean isLeader;
    private boolean isDown;

    public void setName(String name) {
        if (name != null && !name.contains("node")) {
            this.name = "node-" + name;
        } else {
            this.name = name;
        }

    }
}

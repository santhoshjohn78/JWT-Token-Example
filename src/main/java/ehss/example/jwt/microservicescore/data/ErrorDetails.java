package ehss.example.jwt.microservicescore.data;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {
    private String correlationId;
    private String originatingSystem;
    private Date timestamp;
    private String message;
    private String details;
    private String uri;

    public ErrorDetails(String correlationId,String originatingSystem,Date timestamp, String message, String details) {
        super();
        this.originatingSystem = originatingSystem;
        this.correlationId = correlationId;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }



}

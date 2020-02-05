package ehss.example.jwt.microservicescore.data;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends Exception {

    public InvalidRequestException(String msg){
        super(msg);
    }
}

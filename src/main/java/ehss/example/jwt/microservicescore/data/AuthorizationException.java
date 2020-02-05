package ehss.example.jwt.microservicescore.data;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends Exception{

    public AuthorizationException(String msg){
        super(msg);
    }

}

package ehss.example.jwt.microservicescore;

import ehss.example.jwt.microservicescore.data.*;
import org.kp.kpmc.integration.microservicescore.data.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getHeader(Constants.CORRELATIONID),"",new Date(), ex.getMessage(), ex.getLocalizedMessage());
        errorDetails.setUri(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> invalidRequestException(InvalidRequestException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getHeader(Constants.CORRELATIONID),"",new Date(), ex.getMessage(), ex.getLocalizedMessage());
        errorDetails.setUri(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceException(ServiceException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getHeader(Constants.CORRELATIONID),"",new Date(), ex.getMessage(), ex.getLocalizedMessage());
        errorDetails.setUri(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<?> authorizationException(AuthorizationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getHeader(Constants.CORRELATIONID),"",new Date(), ex.getMessage(), ex.getLocalizedMessage());
        errorDetails.setUri(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

}

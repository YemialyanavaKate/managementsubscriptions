package by.subscriptions.yemialyanava.managementsubscriptions.error;

import by.subscriptions.yemialyanava.managementsubscriptions.error.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorDetails> handleHttpServerErrorException(HttpServerErrorException ex) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return  new  ResponseEntity <>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDetails> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST);
        return  new  ResponseEntity <>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException (UserNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return  new  ResponseEntity <>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

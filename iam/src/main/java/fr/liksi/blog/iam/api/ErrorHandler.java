package fr.liksi.blog.iam.api;

import fr.liksi.blog.iam.api.bean.ErrorResponse;
import fr.liksi.blog.iam.exception.ObjectAlreadyExist;
import fr.liksi.blog.iam.exception.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<ErrorResponse> handleException(ObjectNotFound exception) {
        ErrorResponse error = new ErrorResponse("object not found", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleException(ObjectAlreadyExist exception) {
        ErrorResponse error = new ErrorResponse("object already exist", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = new ErrorResponse("unknown error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


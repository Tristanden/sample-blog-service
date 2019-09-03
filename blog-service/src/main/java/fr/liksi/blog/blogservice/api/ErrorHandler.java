package fr.liksi.blog.blogservice.api;

import fr.liksi.blog.blogservice.api.bean.ErrorResponse;
import fr.liksi.blog.blogservice.exception.ObjectAlreadyExist;
import fr.liksi.blog.blogservice.exception.ObjectNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<ErrorResponse> handleException(ObjectNotFound exception) {
        ErrorResponse error = new ErrorResponse("object not found", exception.getMessage());
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleException(ObjectAlreadyExist exception) {
        ErrorResponse error = new ErrorResponse("object already exist", exception.getMessage());
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = new ErrorResponse("unknown error", exception.getMessage());
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


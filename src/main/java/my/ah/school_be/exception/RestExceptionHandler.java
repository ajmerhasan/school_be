package my.ah.school_be.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorModel> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    private ResponseEntity<ErrorModel> handleEntityExists(EntityExistsException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.CONFLICT, "Resource already exist", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    private ResponseEntity<ErrorModel> handleNonUniqueResult(NonUniqueResultException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.CONFLICT, "Resource already exist", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FileUploadException.class)
    private ResponseEntity<ErrorModel> handleGlobalException(Exception ex) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Resource already exist", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

package pl.dziedziul.videorentalstore.infra;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return yourBad(ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return yourBad(ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(final HttpMediaTypeNotAcceptableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return yourBad(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(final MissingPathVariableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return yourBad(ex);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return yourBad(ex);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        if (errors.isEmpty()) {
            builder.message(ex.getMessage());
        } else {
            errors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .sorted()
                .forEachOrdered(builder::message);
        }
        return new ResponseEntity<>(builder.build(), BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return yourBad(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity onException(Exception ex) {
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getMessage()), INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> yourBad(final Exception ex) {
        return new ResponseEntity<>(ErrorResponse.builder().message(ex.getMessage()), BAD_REQUEST);
    }

}

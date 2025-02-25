package ec.reto.persona.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class PersonaExceptionHandler {

    /**
     * Handles MangasException when a requested resource is not found.
     *
     * @param ex      the MangasException thrown when the resource is not found
     * @param request the WebRequest in which the exception occurred
     * @return a ResponseEntity containing a map with error details, including the HTTP status as NOT_FOUND
     */
    @ExceptionHandler({PersonaException.class, NoResourceFoundException.class, HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(final Exception ex, final WebRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any unhandled exception that occurs in the application.
     *
     * @param ex      the exception that was thrown
     * @param request the web request in which the exception occurred
     * @return a ResponseEntity containing a map with error details and the appropriate HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(final Exception ex, final WebRequest request) {
        HttpStatus status = determineHttpStatus(ex);
        return handleException(ex, request, status);
    }

    private ResponseEntity<Map<String, Object>> handleException(final Exception ex, final WebRequest request, final HttpStatus status) {
        logRequestAndException(request, ex);
        Map<String, Object> responseBody = buildResponseBody(ex, status);
        return new ResponseEntity<>(responseBody, status);
    }

    private void logRequestAndException(final WebRequest request, final Exception ex) {
        log.warn(request.getDescription(Boolean.TRUE));
        log.error(ex.getMessage());
    }

    private Map<String, Object> buildResponseBody(final Exception ex, final HttpStatus status) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message", determineMessage(ex));
        responseBody.put("status", status.value());
        return responseBody;
    }

    private HttpStatus determineHttpStatus(final Exception ex) {
        return (ex instanceof PersonaException || ex instanceof NoResourceFoundException || ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof MethodArgumentNotValidException)
                ? HttpStatus.NOT_FOUND
                : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String determineMessage(final Exception ex) {
        if (ex instanceof PersonaException || ex instanceof NoResourceFoundException || ex instanceof HttpRequestMethodNotSupportedException) {
            return ex.getMessage();
        } else if (ex instanceof MethodArgumentNotValidException methodArgumentException) {
            List<String> errors = new ArrayList<>();
            methodArgumentException.getBindingResult().getFieldErrors().forEach(error -> {
                String errorDetail;
                errorDetail = error.getDefaultMessage();
                errors.add(errorDetail);
            });
            return errors.toString();
        } else {
            return "Error inesperado. Contacte con el soporte si el problema persiste.";
        }
    }
}

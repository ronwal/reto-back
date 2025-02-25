package ec.reto.persona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuentaException extends RuntimeException {
    public CuentaException(final String message) {
        super(message);
    }
}

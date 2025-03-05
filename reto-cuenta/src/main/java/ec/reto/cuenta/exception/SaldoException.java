package ec.reto.cuenta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents an exception that occurs while processing settings.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaldoException extends RuntimeException {
    /**
     * Constructs a new SettingsException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public SaldoException(final String message) {
        super(message);
    }

    /**
     * This exception is thrown when an error occurs while processing settings.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public SaldoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SettingsException with the specified cause.
     *
     * @param cause the cause of the exception
     * @throws NullPointerException if the cause is null
     */
    public SaldoException(final Throwable cause) {
        super(cause);
    }
}

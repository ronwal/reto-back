package ec.reto.persona.exception;

/**
 * Represents an exception that occurs while processing settings.
 */
public class PersonaException extends Exception {
    /**
     * Constructs a new SettingsException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PersonaException(final String message) {
        super(message);
    }

    /**
     * This exception is thrown when an error occurs while processing settings.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public PersonaException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SettingsException with the specified cause.
     *
     * @param cause the cause of the exception
     * @throws NullPointerException if the cause is null
     */
    public PersonaException(final Throwable cause) {
        super(cause);
    }
}

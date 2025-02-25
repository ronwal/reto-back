package ec.reto.persona.enums;

public enum RespuestaEnums {
    MSJ_CREACION_EXITOSA("Registro creado de manera exitosa!"),
    MSJ_CREACION_ERROR("El registro no pudo ser creado, por favor intente nuevamente!");
    private final String message;

    /**
     * Constructor for the MensajesRespuestaEnums enum.
     *
     * @param menssage the message associated with the enum constant
     */
    RespuestaEnums(final String menssage) {
        this.message = menssage;
    }

    /**
     * Retrieves the message associated with the enum constant.
     *
     * @return the message as a String
     */
    public String message() {
        return this.message;
    }
}

package institutoinnova;

/**
 * Se lanza cuando el numero de documento no cumple con la
 * cantidad de digitos exigida por su tipo (DNI = 8, Carne de
 * Residencia Temporal = 11), o cuando contiene caracteres no numericos.
 */
public class DocumentoInvalidoException extends Exception {

    public DocumentoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

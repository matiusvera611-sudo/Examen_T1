package institutoinnova;

/**
 * Tipos de documento de identidad que acepta el instituto.
 * Cada tipo define cuantos digitos debe tener el numero de documento.
 */
public enum TipoDocumento {
    DNI(8),
    CARNE_RESIDENCIA(11);

    private final int longitudRequerida;

    TipoDocumento(int longitudRequerida) {
        this.longitudRequerida = longitudRequerida;
    }

    public int getLongitudRequerida() {
        return longitudRequerida;
    }
}

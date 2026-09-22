package institutoinnova;

import java.util.Objects;

/**
 * Representa a un alumno del Instituto Innova.
 * <p>
 * Aplica encapsulamiento: todos los atributos son privados y solo se
 * accede a ellos mediante metodos get/set. El set del numero de
 * documento valida la cantidad de digitos segun el tipo de documento,
 * para resolver el problema de identidad/homonimia que tenia Regina:
 * dos alumnos nunca se consideran el mismo si su numero de documento
 * es distinto, sin importar que compartan nombre y apellido.
 */
public class Alumno {

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private NivelSocioeconomico nivelSocioeconomico;
    private TipoBeca tipoBeca;

    public Alumno(String nombre, String apellido, TipoDocumento tipoDocumento,
                  String numeroDocumento, NivelSocioeconomico nivelSocioeconomico,
                  TipoBeca tipoBeca) throws DocumentoInvalidoException {
        setNombre(nombre);
        setApellido(apellido);
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento, "El tipo de documento no puede ser nulo");
        setNumeroDocumento(numeroDocumento, tipoDocumento);
        this.nivelSocioeconomico = Objects.requireNonNull(nivelSocioeconomico, "El nivel socioeconomico no puede ser nulo");
        this.tipoBeca = (tipoBeca == null) ? TipoBeca.NINGUNA : tipoBeca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        this.apellido = apellido.trim();
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Valida y asigna el numero de documento segun el tipo:
     * DNI debe tener exactamente 8 digitos, Carne de Residencia
     * Temporal debe tener exactamente 11 digitos. Solo se aceptan
     * caracteres numericos.
     */
    public void setNumeroDocumento(String numeroDocumento, TipoDocumento tipoDocumento) throws DocumentoInvalidoException {
        if (numeroDocumento == null || !numeroDocumento.matches("\\d+")) {
            throw new DocumentoInvalidoException("El numero de documento solo debe contener digitos");
        }
        int longitudEsperada = tipoDocumento.getLongitudRequerida();
        if (numeroDocumento.length() != longitudEsperada) {
            throw new DocumentoInvalidoException(
                "El " + tipoDocumento + " debe tener exactamente " + longitudEsperada
                + " digitos (se ingresaron " + numeroDocumento.length() + ")");
        }
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public NivelSocioeconomico getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }

    public void setNivelSocioeconomico(NivelSocioeconomico nivelSocioeconomico) {
        this.nivelSocioeconomico = Objects.requireNonNull(nivelSocioeconomico, "El nivel socioeconomico no puede ser nulo");
    }

    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(TipoBeca tipoBeca) {
        this.tipoBeca = (tipoBeca == null) ? TipoBeca.NINGUNA : tipoBeca;
    }

    /**
     * Calcula la pension final: tarifa base del nivel socioeconomico
     * menos el descuento por beca (parcial 50%, total 100%).
     */
    public double calcularPension() {
        double tarifaBase = nivelSocioeconomico.getTarifaBase();
        double descuento = tarifaBase * tipoBeca.getPorcentajeDescuento();
        return tarifaBase - descuento;
    }

    /**
     * Dos alumnos se consideran el mismo unicamente si comparten
     * tipo y numero de documento. Esto evita que la homonimia
     * (mismo nombre y apellido) confunda al sistema.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Alumno)) return false;
        Alumno otro = (Alumno) obj;
        return tipoDocumento == otro.tipoDocumento
            && Objects.equals(numeroDocumento, otro.numeroDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoDocumento, numeroDocumento);
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s | %s: %s | Nivel: %s | Beca: %s | Pension: S/ %.2f",
            nombre, apellido, tipoDocumento, numeroDocumento,
            nivelSocioeconomico, tipoBeca, calcularPension());
    }
}

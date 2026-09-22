package institutoinnova;

/**
 * Nivel socioeconomico del alumno. Cada nivel define la tarifa base
 * de la pension mensual antes de aplicar cualquier beca.
 * Los montos son referenciales y pueden ajustarse segun politica del instituto.
 */
public enum NivelSocioeconomico {
    A(500.0),
    B(350.0),
    C(200.0);

    private final double tarifaBase;

    NivelSocioeconomico(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }
}

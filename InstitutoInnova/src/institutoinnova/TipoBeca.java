package institutoinnova;

/**
 * Tipo de beca del alumno y el porcentaje de descuento que aplica
 * sobre la tarifa base de su nivel socioeconomico.
 */
public enum TipoBeca {
    NINGUNA(0.0),
    PARCIAL(0.50),
    TOTAL(1.00);

    private final double porcentajeDescuento;

    TipoBeca(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}

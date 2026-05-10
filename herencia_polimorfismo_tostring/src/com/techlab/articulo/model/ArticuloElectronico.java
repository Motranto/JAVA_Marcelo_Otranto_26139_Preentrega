package com.techlab.articulo.model;

/*
 * CLASE ARTICULOELECTRONICO
 * --------------------------------------------------
 * Esta clase hereda de Articulo.
 * agrega
 *   - garantia en meses
 */

public class ArticuloElectronico extends Articulo {

    private int garantiaMeses;

    /* Constructor
     * --------------------------------------------------
     * Llama a super(...) para inicializar los atributos heredados.
     * Luego inicializa su atributo propio.
     */
    public ArticuloElectronico(int sku, int ean, String nombre, String descripcion, double precio, Categoria categoria, int garantiaMeses) { 
        super(sku, ean, nombre, descripcion, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    /* Getters y Setters */

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    // Sobreescribimos los metodos obligatorios
    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    }

    public String nroTelMesaDeAyudaParaReclamos() {
        return "0800-123-4567";
    }

    /*
     * toString() específico del hijo
     * --------------------------------------------------
     * Acá mostramos otra idea importante:
     * además del toString() de la clase padre, una clase hija también puede
     * sobrescribirlo si quiere agregar o cambiar el formato.
     *
     * En este caso reutilizamos super.toString() y le agregamos una aclaración.
     */
    @Override
    public String toString() {
        return super.toString() + " [subtipo electrónico]";
    }
}

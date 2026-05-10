package com.techlab.articulo.model;

/*
 * CLASE ARTICULOTEXTIL
 * --------------------------------------------------
 * Esta clase hereda de Articulo.
 * agrega
 *   - talle
 */

public class ArticuloTextil extends Articulo {

    private int talle;

    /* Constructor
     * --------------------------------------------------
     * Llama a super(...) para inicializar los atributos heredados.
     * Luego inicializa su atributo propio.
     */
    
    public ArticuloTextil(int sku, int ean, String nombre, String descripcion, double precio, Categoria categoria, int talle) { 
        super(sku, ean, nombre, descripcion, precio, categoria);
        this.talle = talle;
    }

    /* Getters y Setters */
    
    public int getTalleo() {
        return talle;
    }

    public void setTalle(int talle) {
        this.talle = talle;
    }

    // Sobreescribimos los metodos obligatorios
    @Override
    public String getTipoArticulo() {
        return "Textil";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Talle: " + talle;
    }

    // y el toString
    @Override
    public String toString() {
        return super.toString() + " [subtipo textil]";
    }
}

package com.techlab.articulo.model;

/*
 * CLASE ARTICULOTEXTIL
 * --------------------------------------------------
 * Esta clase hereda de Articulo.
 * agrega
 *   - talle
 *   - color
 */

public class ArticuloTextil extends Articulo {

    private int talle;

    // Atributo que representa el color del artiuclo
    private Color color;

    /* Constructor
     * --------------------------------------------------
     * Llama a super(...) para inicializar los atributos heredados.
     * Luego inicializa su atributo propio.
     */
    
    public ArticuloTextil(int sku, int ean, String nombre, String descripcion, double precio, Categoria categoria, int talle, Color color) { 
        super(sku, ean, nombre, descripcion, precio, categoria);
        this.talle = talle;
        this.color = color;
    }

    /* Getters y Setters */
    
    public int getTalle() {
        return talle;
    }

    public void setTalle(int talle) {
        this.talle = talle;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Sobreescribimos los metodos obligatorios
    @Override
    public String getTipoArticulo() {
        return "Textil";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Talle: " + talle + ", color = " + color.getNombre();
    }

    // y el toString
    @Override
    public String toString() {
        return super.toString() + " [subtipo textil]";
    }

 /*  Calcular calcularPrecioFinal:
 * -  los colores 1 Blanco o 2 Negro tienen un 10 % de descuento
 * -  el precio final queda igual al precio base
 */

    @Override
    public double calcularPrecioFinal() {
        if (color.getCodigo() == 1 || color.getCodigo() == 2) {
            return getPrecio() * 0.9;
        }

        return getPrecio();
    }

}

package com.techlab.articulo.model;

/*
 * CLASE ARTICULO
 * ---------------------------------------------------------
 * Esta clase representa el concepto de artículo en el sistema.
 * SKU
 * EAN
 * NOMBRE
 * DESCRIPCION
 * PRECIO
 * CATEGORIA
 */
public class Articulo {

    // Atributo que representa el código del artículo.
    private int sku;

    // Atributo que representa el código de barras del artículo
    private int ean;

    // Atributo que representa el nombre del artículo.
    private String nombre;

    // Atributo que representa la descripción larga del artículo.
    private String descripcion;

    // Atributo que representa el precio del artículo.
    private double precio;

    // Atributo que representa la categoria del artiuclo
    private String categoria;

    /*
     * CONSTRUCTOR
     * ---------------------------------------------------------
     * El constructor sirve para crear objetos ya inicializados.
     *
     * Cuando hacemos:
     * new Articulo(1, 77908500232, "Coca Cola", "Lata de Coca Cola de 350 cm3", 2500, "BEBIDAS")
     *
     * estamos creando un objeto con estos datos.
     */
    public Articulo(int sku, int ean, String nombre, String descripcion, double precio, String categoria) {
        this.sku = sku;
        this.ean = ean;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    /*
     * GETTER DE SKU
     * ---------------------------------------------------------
     * Permite obtener el valor del atributo SKU.
     */
    public int getSku() {
        return sku;
    }

    /*
     * SETTER DE SKU
     * ---------------------------------------------------------
     * Permite modificar el valor del atributo SKU.
     */
    public void setSku(int sku) {
        this.sku = sku;
    }

    /*
     * GETTER DE EAN
     * ---------------------------------------------------------
     * Permite obtener el valor del atributo EAN.
     */
    public int getEan() {
        return ean;
    }

    /*
     * SETTER DE EAN
     * ---------------------------------------------------------
     * Permite modificar el valor del atributo EAN.
     */
    public void setEan(int ean) {
        this.ean = ean;
    }

    /*
     * GETTER DE NOMBRE
     * ---------------------------------------------------------
     * Permite obtener el nombre del artículo.
     */
    public String getNombre() {
        return nombre;
    }

    /*
     * SETTER DE NOMBRE
     * ---------------------------------------------------------
     * Permite modificar el nombre del artículo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * GETTER DE DESCRIPCIOM
     * ---------------------------------------------------------
     * Permite obtener la descripcion del artículo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /*
     * SETTER DE DESCRIPCION
     * ---------------------------------------------------------
     * Permite modificar la descripcion del artículo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*
     * GETTER DE PRECIO
     * ---------------------------------------------------------
     * Permite obtener el precio del artículo.
     */
    public double getPrecio() {
        return precio;
    }

    /*
     * SETTER DE PRECIO
     * ---------------------------------------------------------
     * Permite modificar el precio del artículo.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /*
     * GETTER DE CATEGORIA
     * ---------------------------------------------------------
     * Permite obtener la categoría del artículo.
     */
    public String getCategoria() {
        return categoria;
    }

    /*
     * SETTER DE CATEGORIA
     * ---------------------------------------------------------
     * Permite modificar la categoria del artículo.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    /*
     * MÉTODO toString
     * ---------------------------------------------------------
     * Este método permite definir cómo queremos mostrar el objeto.
     *
     * Si no lo sobrescribimos, Java mostraría algo poco útil, como:
     * com.techlab.articulo.model.Articulo@7a81197d
     *
     * En cambio, con toString() mostramos la información real del objeto.
     */
    @Override
    public String toString() {
        return "Artículo {" +
                "sku = " + sku +
                ", ean = " + ean +
                ", nombre = '" + nombre + '\'' +
                ", descripcion = '" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoria = '" + categoria + '\'' +
                '}';
    }
}

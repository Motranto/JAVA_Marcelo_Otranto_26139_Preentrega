package com.techlab.articulo.model;

/*
 * CLASE ARTICULOALIMENTICIO
 * --------------------------------------------------
 * Esta clase hereda de Articulo.
 * agrega
 *   - días para vencimiento
 */

public class ArticuloAlimenticio extends Articulo {

    private int diasParaVencimiento;

    /* Constructor
     * --------------------------------------------------
     * Llama a super(...) para inicializar los atributos heredados.
     * Luego inicializa su atributo propio.
     */
    
    public ArticuloAlimenticio(int sku, int ean, String nombre, String descripcion, double precio, Categoria categoria, int diasParaVencimiento) { 
        super(sku, ean, nombre, descripcion, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    /* Getters y Setters */
    
    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    // Sobreescribimos los metodos obligatorios
    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Días para vencimiento: " + diasParaVencimiento;
    }

    // y el toString
    @Override
    public String toString() {
        return super.toString() + " [subtipo alimenticio]";
    }


/*  Calcular calcularPrecioFinal:
 * - si vence en 3 días o menos -> 20% de descuento
 * - si vence en 7 días o menos -> 10% de descuento
 * - si no -> mantiene el precio base
 */

    @Override
    public double calcularPrecioFinal() {
        if (diasParaVencimiento <= 3) {
            return getPrecio() * 0.80;
        }

        if (diasParaVencimiento <= 7) {
            return getPrecio() * 0.90;
        }

        return getPrecio();
    }
    
}



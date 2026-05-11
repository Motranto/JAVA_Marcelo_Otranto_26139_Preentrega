package com.techlab.articulo.model;

/*
 * CLASE COLOR
 * --------------------------------------------------
 * objeto para asignar el color a los artículos Textil.
 */

public class Color {

    private int codigo;
    private String nombre;
    
    public Color(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Color {" +
                "código=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

package org.example.examenii_i_ciclo_2023.presentation.models;

public class Platillo {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;

    public Platillo(String id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}

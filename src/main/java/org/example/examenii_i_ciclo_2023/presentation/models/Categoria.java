package org.example.examenii_i_ciclo_2023.presentation.models;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String id;
    private String nombre;
    private List<Platillo> platillos;

    public Categoria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.platillos = new ArrayList<>();
    }

    public void addPlatillo(Platillo platillo) {
        platillos.add(platillo);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Platillo> getPlatillos() {
        return new ArrayList<>(platillos);
    }
}

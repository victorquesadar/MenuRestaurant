package org.example.examenii_i_ciclo_2023.presentation.models;

import java.util.List;

public class Orden {
    private String id;
    private List<OrdenItem> items;
    private double total;

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrdenItem> getItems() {
        return items;
    }

    public void setItems(List<OrdenItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}



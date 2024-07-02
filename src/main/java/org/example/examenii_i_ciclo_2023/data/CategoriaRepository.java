package org.example.examenii_i_ciclo_2023.data;

import org.example.examenii_i_ciclo_2023.presentation.models.Categoria;
import org.example.examenii_i_ciclo_2023.presentation.models.Platillo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaRepository {
    private List<Categoria> categorias;

    public CategoriaRepository() {
        categorias = new ArrayList<>();
        // Crear categorías y asignar platillos
        Categoria entradas = new Categoria("111", "Entradas");
        Categoria carnes = new Categoria("222", "Carnes");
        Categoria sopas = new Categoria("333", "Sopas");
        Categoria arroces = new Categoria("444", "Arroces");
        Categoria bebidas = new Categoria("555", "Bebidas");
        Categoria postres = new Categoria("666", "Postres");

        // Agregar categorías al repositorio
        categorias.add(entradas);
        categorias.add(carnes);
        categorias.add(sopas);
        categorias.add(arroces);
        categorias.add(bebidas);
        categorias.add(postres);

// Crear platillos y asignarlos a cada categoría sin pasar el objeto Categoria
        entradas.addPlatillo(new Platillo("101", "Ensalada Caprese", "Tomate y mozzarella", 15.00));
        entradas.addPlatillo(new Platillo("101", "Ensalada Jaonesa", "Queso lecho", 15.00));

        carnes.addPlatillo(new Platillo("201", "Bife de Chorizo", "Carne de primera calidad", 25.00));
        sopas.addPlatillo(new Platillo("301", "Sopa de Tomate", "Sopa cremosa de tomate", 10.00));
        arroces.addPlatillo(new Platillo("401", "Paella", "Arroz con mariscos", 30.00));
        bebidas.addPlatillo(new Platillo("501", "Limonada", "Limonada natural", 5.00));
        postres.addPlatillo(new Platillo("601", "Tiramisú", "Postre italiano con café", 12.00));

    }

    public Categoria findById(String id) throws Exception {
        for (Categoria categoria : categorias) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        throw new Exception("Categoria not found");
    }

    public List<Categoria> findAll() {
        return new ArrayList<>(categorias);
    }
}

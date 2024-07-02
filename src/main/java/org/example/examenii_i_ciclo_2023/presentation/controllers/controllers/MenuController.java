package org.example.examenii_i_ciclo_2023.presentation.controllers.controllers;

import org.example.examenii_i_ciclo_2023.data.CategoriaRepository;
import org.example.examenii_i_ciclo_2023.presentation.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public MenuController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias != null) {
            return ResponseEntity.ok(categorias);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    // Endpoint para obtener platillos por categoría

    // Endpoint para obtener platillos por categoría
    @GetMapping("/platillos/{categoriaId}")
    public ResponseEntity<?> getPlatillosByCategoria(@PathVariable String categoriaId) {
        try {
            Categoria categoria = categoriaRepository.findById(categoriaId);
            return ResponseEntity.ok(categoria.getPlatillos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada: " + categoriaId);
        }
    }

    @PostMapping("/orden")
    public ResponseEntity<?> crearOrden(@RequestBody Orden orden) {
        String ordenId = generarIdOrden();
        orden.setId(ordenId);

        // Imprimir detalles de la orden en la consola
        System.out.println("==========");
        System.out.println("Orden: " + orden.getId());
        for (OrdenItem item : orden.getItems()) {
            System.out.println(item.getCantidad() + " x " + item.getNombrePlatillo() + " - " + item.getSize());
        }

        // Devolver una respuesta con el ID de la orden
        Map<String, String> response = new HashMap<>();
        response.put("ordenId", ordenId);
        return ResponseEntity.ok(response);
    }


    private String generarIdOrden() {
        // Obtener la fecha y hora actual
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);

        // Generar un número aleatorio
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Generar un número aleatorio entre 0 y 9999

        // Combinar el timestamp con el número aleatorio para generar el ID de la orden
        return "ORD-" + timestamp + "-" + String.format("%04d", randomNumber);
    }
}

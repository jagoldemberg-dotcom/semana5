package com.example.demo.controller;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
  private final PeliculaService service;
  public PeliculaController(PeliculaService service){ this.service = service; }

  @GetMapping
  public ResponseEntity<List<Pelicula>> todas() {
    return ResponseEntity.ok(service.getAllPelicula());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pelicula> porId(@PathVariable int id) {
    return service.getPeliculaById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

    // --- NUEVO: CREAR ---
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PeliculaRequest req) {
        Pelicula p = new Pelicula();
        p.setTitulo(req.getTitulo());
        p.setAnio(req.getAnio());
        p.setDirector(req.getDirector());
        p.setGenero(req.getGenero());
        p.setSinopsis(req.getSinopsis());

        Pelicula creado = service.crear(p);
        return ResponseEntity
                .created(URI.create("/peliculas/" + creado.getId()))
                .body(creado);
    }

    // --- NUEVO: ELIMINAR POR ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable int id) {
        boolean ok = service.eliminarPorId(id);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // --- NUEVO: ELIMINAR POR TÍTULO ---
    // Ej: DELETE /peliculas?titulo=El%20padrino
    @DeleteMapping
    public ResponseEntity<?> eliminarPorTitulo(@RequestParam(name = "titulo") String titulo) {
        long borrados = service.eliminarPorTitulo(titulo);
        // 204 si borró algo, 404 si no encontró coincidencias
        return (borrados > 0) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
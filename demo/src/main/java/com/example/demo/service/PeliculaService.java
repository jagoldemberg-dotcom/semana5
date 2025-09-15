package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pelicula;

@Service
public interface PeliculaService {

    List<Pelicula> getAllPelicula();
    Optional<Pelicula> getPeliculaById(int id);
    Pelicula crear(Pelicula p);
    boolean eliminarPorId(int id);
    long eliminarPorTitulo(String titulo);

}

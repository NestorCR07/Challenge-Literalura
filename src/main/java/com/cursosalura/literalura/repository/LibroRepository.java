package com.cursosalura.literalura.repository;

import com.cursosalura.literalura.model.Autor;
import com.cursosalura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByIdiomasContaining(String idioma);

}

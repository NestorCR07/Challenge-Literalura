package com.cursosalura.literalura.repository;

import com.cursosalura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {

}

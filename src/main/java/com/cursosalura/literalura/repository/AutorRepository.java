package com.cursosalura.literalura.repository;

import com.cursosalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    List<Autor> findByFechaDeFallecimientoGreaterThanEqual(Long fecha);


    //@Query("SELECT a FROM Autor a WHERE a.fechaDeFallecimiento >= :fecha")
    //List<Autor> autoresVivosPorFecha(Long fecha);

}

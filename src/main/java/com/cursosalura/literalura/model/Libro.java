package com.cursosalura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


@Entity
@Table(name="libros")
public class Libro {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idiomas;
    private Double numeroDeDescargas;
    @OneToOne(mappedBy = "libros", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Autor autors;


    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = String.valueOf(datosLibro.autor().stream().
                map(DatosAutor::nombre).collect(Collectors.toList()))
                .replace("[","").replace("]","");
        this.idiomas = String.valueOf(datosLibro.idiomas()).
                replace("[","").replace("]","");
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutors() {
        return autors;
    }

    public void setAutors(Autor autors) {
        this.autors = autors;
    }

    @Override
    public String toString() {
        return "Libro: \n\n" +
                "Id: " + Id +
                "\nTitulo: " + titulo +
                "\nAutor: " + autor +
                "\nIdiomas: " + idiomas +
                "\nNumero De Descargas: " + numeroDeDescargas + "\n";
    }
}

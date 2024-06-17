package com.cursosalura.literalura.model;

import jakarta.persistence.*;
import jakarta.transaction.TransactionScoped;

import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Long fechaDeNacimiento;
    private Long fechaDeFallecimiento;
    @OneToOne
    private Libro libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
    }

    public Autor(String nombre, Long fechaDeNacimiento, Long fechaDeFallecimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeFallecimiento = fechaDeFallecimiento;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Long fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Long getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Long fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public Libro getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor: \n\n" +
                "Id: " + Id +
                "\nNombre: " + nombre +
                "\nFecha De Nacimiento: " + fechaDeNacimiento +
                "\nFecha De Fallecimiento: " + fechaDeFallecimiento +
                "\nLibros: " + libros.getTitulo() + "\n";

    }
}

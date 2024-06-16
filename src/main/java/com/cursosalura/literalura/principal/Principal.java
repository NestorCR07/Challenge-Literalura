package com.cursosalura.literalura.principal;

import com.cursosalura.literalura.model.Datos;
import com.cursosalura.literalura.model.DatosLibro;
import com.cursosalura.literalura.model.Libro;
import com.cursosalura.literalura.repository.LibroRepository;
import com.cursosalura.literalura.service.ConsumoAPI;
import com.cursosalura.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String API_KEY = "&apikey=43ec27d0";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repository;
    private List<Libro> libros;

    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    public void muestraElMenu() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("\n");
            var menu = """
                    1 - Buscar libro por titulo .
                    2 - Listar libros registrados.
                    3 - Listar Autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libros por idioma.
                                                      
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibrosRegistrados();

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibroPorTitulo() {
        Optional<DatosLibro> libroBuscado = getDatosLibro();

        if(libroBuscado.isPresent()){
            System.out.println("Libro Encontrado");
            Libro libro = new Libro(libroBuscado.get());
            repository.save(libro);
            System.out.println(libro);

        }else {
            System.out.println("Libro no encontrado");
        }
    }
    private Optional<DatosLibro> getDatosLibro() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoApi.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        return libroBuscado;
    }
    private void buscarLibrosRegistrados() {

        libros = repository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

}

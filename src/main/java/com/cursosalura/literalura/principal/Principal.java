package com.cursosalura.literalura.principal;

import com.cursosalura.literalura.model.Autor;
import com.cursosalura.literalura.model.Datos;
import com.cursosalura.literalura.model.DatosLibro;
import com.cursosalura.literalura.model.Libro;
import com.cursosalura.literalura.repository.AutorRepository;
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
    private AutorRepository repositoryA;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository repository, AutorRepository repositoryA) {
        this.repository = repository;
        this.repositoryA = repositoryA;
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
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivosPorFecha();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
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
            DatosLibro datosLibro =  libroBuscado.get();
            Libro libro = new Libro(datosLibro);

            var autor = datosLibro.autor().stream().
                    map(a -> new Autor(a.nombre(),a.fechaDeNacimiento(),a.fechaDeFallecimiento()))
                    .toList();
            if (!autor.isEmpty()) {
                autor.get(0).setLibros(libro);
                libro.setAutors(autor.get(0));
            }

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
    private void buscarAutoresRegistrados() {
        autores = repositoryA.findAll();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }
    private void buscarAutoresVivosPorFecha() {

        System.out.println("Escribe la fecha para consultar autores vivos antes de ese año: ");
        var fecha = teclado.nextLong();
        List<Autor> autoresEncontrados = repositoryA.findByFechaDeFallecimientoGreaterThanEqual(fecha);
        System.out.println("\nAutores Vivos Antes De "+fecha+":");
        autoresEncontrados.forEach(e ->
                System.out.printf("\nAutor: %s \nFecha de nacimiento %s \nFecha de fallecimiento %s\n\n",
                        e.getNombre(),e.getFechaDeNacimiento(),e.getFechaDeFallecimiento()));

    }
    private void buscarLibrosPorIdioma() {
        System.out.println("\n");
        var opciones = -1;
        String idioma;
        var menu2 = """
                    Seleccione En Que Idioma Quiere Hacer La Busqueda:
                    
                    1 - En - English .
                    2 - Es - Español.
                                                      
                    0 - Salir
                    """;
        System.out.println(menu2);
        opciones = teclado.nextInt();
        teclado.nextLine();

        switch (opciones) {
            case 1:
                idioma = "en";
                getLibrosPorIdioma(idioma);
                break;
            case 2:
                idioma = "es";
                getLibrosPorIdioma(idioma);
                break;
            default:
                System.out.println("Opción inválida");
        }

    }

    private void getLibrosPorIdioma(String idioma) {

        List<Libro> librosEncontrados = repository.findByIdiomasContaining(idioma);
        System.out.println("\nLibros Encontrados: ");
        librosEncontrados.forEach(e ->
                System.out.printf("\nTitulo: %s \nAutor: %s \nIdioma: %s \nNumero De Descargas: %s\n\n",
                        e.getTitulo(),
                        e.getAutor(),
                        e.getIdiomas().replace("es","Español").replace("en","English"),
                        e.getNumeroDeDescargas()));

    }

}

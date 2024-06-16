package com.cursosalura.literalura.principal;

import com.cursosalura.literalura.model.Datos;
import com.cursosalura.literalura.service.ConsumoAPI;
import com.cursosalura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String API_KEY = "&apikey=43ec27d0";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
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
                    var json = consumoApi.obtenerDatos(URL_BASE);
                    System.out.println(json);
                    var datos = conversor.obtenerDatos(json, Datos.class);
                    System.out.println(datos);

                    break;
                case 2:

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
}

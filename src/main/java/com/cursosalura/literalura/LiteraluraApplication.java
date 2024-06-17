package com.cursosalura.literalura;

import com.cursosalura.literalura.model.Autor;
import com.cursosalura.literalura.model.Libro;
import com.cursosalura.literalura.principal.Principal;
import com.cursosalura.literalura.repository.AutorRepository;
import com.cursosalura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	LibroRepository repository;
	@Autowired
	AutorRepository repositoryA;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,repositoryA);
		principal.muestraElMenu();
	}
}

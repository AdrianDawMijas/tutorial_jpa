package org.iesvdm.tutorial;


import org.iesvdm.tutorial.domain.Categoria;
import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.domain.PeliculaCategoria;
import org.iesvdm.tutorial.repository.CategoriaPeliculaRepository;
import org.iesvdm.tutorial.repository.CategoriaRepository;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.iesvdm.tutorial.repository.PeliculaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PeliculaCategoriaTests {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    IdiomaRepository idiomaRepository;

    @Autowired
    CategoriaPeliculaRepository categoriaPeliculaRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    @BeforeEach
    public void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Test
    @Order(1)
    void grabarMultiplesPeliculasCategorias(){

        Idioma idioma1 = new Idioma(0, "español", new HashSet<>());
        idiomaRepository.save(idioma1);

        Pelicula pelicula1 = new Pelicula(0, "Pelicula1", idioma1);
        idioma1.getPeliculas().add(pelicula1);
        peliculaRepository.save(pelicula1);

        PeliculaCategoria peliculaCategoria = new PeliculaCategoria();

        Categoria categoria = Categoria.builder().build();



    }


}

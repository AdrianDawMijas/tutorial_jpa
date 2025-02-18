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
import java.util.List;
import java.util.stream.Collectors;

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

    @BeforeEach
    public void setUp() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Test
    @Order(1)
    void grabarMultiplesPeliculasCategorias(){

        Idioma idioma1 = new Idioma(0, "español", new HashSet<>());
        idiomaRepository.save(idioma1);

        Pelicula pelicula1 = new Pelicula(0, "Pelicula1", idioma1);
        idioma1.getPeliculas().add(pelicula1);
        peliculaRepository.save(pelicula1);

        Categoria categoria = Categoria.builder().id(0).nombre("categoria").build();
        categoriaRepository.save(categoria);

        PeliculaCategoria peliculaCategoria = PeliculaCategoria.builder().pelicula(pelicula1).categoria(categoria).build();
        categoriaPeliculaRepository.save(peliculaCategoria);

        Pelicula pelicula2 = new Pelicula(0, "Pelicula2", idioma1);
        idioma1.getPeliculas().add(pelicula2);
        peliculaRepository.save(pelicula2);

        Categoria categoria2 = Categoria.builder().id(1).nombre("categoria2").build();
        categoriaRepository.save(categoria2);

        PeliculaCategoria peliculaCategoria1 = PeliculaCategoria.builder().pelicula(pelicula2).id(1).build();
        categoriaPeliculaRepository.save(peliculaCategoria1);

    }

    @Test
    @Order(2)
    void actualizarCategoriaPeliculaNull(){

        PeliculaCategoria peliculaCategoria = categoriaPeliculaRepository.findById(2L).orElse(null);
        peliculaCategoria.setCategoria(null);
    }

    @Test
    @Order(3)
    void actualizarCategoriaPelicula(){

        PeliculaCategoria peliculaCategoria = categoriaPeliculaRepository.findById(2L).orElse(null);
        peliculaCategoria.setCategoria(categoriaRepository.findById(1L).orElse(null));

    }

    @Test
    @Order(4)
    void eliminarPelicula(){

        Pelicula pelicula1 = peliculaRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        // Buscar y eliminar las relaciones en PeliculaCategoria
                List<PeliculaCategoria> peliculasCategoriasEliminar = categoriaPeliculaRepository.findByPelicula(pelicula1);

        // Eliminar las relaciones en la base de datos
                categoriaPeliculaRepository.deleteAll(peliculasCategoriasEliminar);

        // Ahora podemos eliminar la película
                peliculaRepository.delete(pelicula1);
    }

    @Test
    @Order(5)
    void eliminarPeliculasAsociadasACategoria(){

        Categoria categoria = categoriaRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        List<PeliculaCategoria> peliculaCategoriaEliminar = categoriaPeliculaRepository.findByCategoria(categoria);

        categoriaPeliculaRepository.deleteAll(peliculaCategoriaEliminar);

        categoriaRepository.delete(categoria);
    }

    @Test
    @Order(6)
    void eliminarPeliculasAsociadasACategoriasYCategoria(){

        Categoria categoria = categoriaRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        List<PeliculaCategoria> peliculaCategoriaEliminar = categoriaPeliculaRepository.findByCategoria(categoria);
        List<Pelicula> peliculasEliminar = peliculaCategoriaEliminar.stream().map(PeliculaCategoria::getPelicula).toList();

        categoriaPeliculaRepository.deleteAll(peliculaCategoriaEliminar);
        peliculaRepository.deleteAll(peliculasEliminar);
        categoriaRepository.delete(categoria);

    }



}

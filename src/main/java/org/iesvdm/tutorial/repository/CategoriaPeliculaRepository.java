package org.iesvdm.tutorial.repository;
import org.iesvdm.tutorial.domain.Categoria;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.domain.PeliculaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriaPeliculaRepository extends JpaRepository<PeliculaCategoria, Long> {

    List<PeliculaCategoria> findByPelicula(Pelicula pelicula1);

    List<PeliculaCategoria> findByCategoria(Categoria categoria);
}

package org.iesvdm.tutorial.repository;
import org.iesvdm.tutorial.domain.Categoria;
import org.iesvdm.tutorial.domain.PeliculaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaPeliculaRepository extends JpaRepository<PeliculaCategoria, Long> {

}

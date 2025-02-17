package org.iesvdm.tutorial.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(length = 30, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private Set<PeliculaCategoria> peliculaCategorias;
}

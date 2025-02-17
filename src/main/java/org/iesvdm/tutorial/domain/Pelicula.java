package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.tutorial.serializer.PeliculaSerializer;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Pelicula.class)
@JsonSerialize(using = PeliculaSerializer.class)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String titulo;
    private Integer añoLanzamiento;

    @ManyToOne
    //@JsonBackReference
    @ToString.Exclude
    private Idioma idioma;

    @ManyToOne
    @ToString.Exclude
    private Idioma idiomaOriginal;

    private Integer duracionALquiler;

    @Column(precision = 4, scale = 2)
    private BigDecimal rental_rate;

    private Short duracion;

    @Column(precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @OneToMany( mappedBy = "pelicula")
    private Set<PeliculaCategoria> peliculaCategorias;

    private ClasificacionEnum clasificacionEnum;

    @ManyToMany(mappedBy = "peliculas")
    private Set<CaracteristicaEspecial> caracteristicasEspeciales;

    public Pelicula(int i, String pelicula1, Idioma idioma1) {
        this.id = i;
        this.titulo = pelicula1;
        this.idioma = idioma1;
    }
}

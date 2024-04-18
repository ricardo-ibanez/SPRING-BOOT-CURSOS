package com.riki.gestionCursos.gestioncursosspringboot.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Cursos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString // para usar el método ToString (casteo)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // incrementa de 1 a 1 los valores
    private Integer id;

    @Column(length = 128, nullable = false) // establece restricciones 128 caracteres y que no pueda ser nulo
    private String titulo;
    @Column(length = 256)
    private String descripcion;
    @Column(nullable = false)
    private int nivel;
    @Column(name = "estado-publicacion")
    private boolean isPublicado;

}

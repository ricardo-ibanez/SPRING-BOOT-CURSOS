package com.riki.gestionCursos.gestioncursosspringboot.repository;


import com.riki.gestionCursos.gestioncursosspringboot.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// en cursoRespository Heredamos de JPA repository por los metodos que nos brinda esta clase
@Repository
public interface CursoRepository extends JpaRepository<Curso,Integer> {
}

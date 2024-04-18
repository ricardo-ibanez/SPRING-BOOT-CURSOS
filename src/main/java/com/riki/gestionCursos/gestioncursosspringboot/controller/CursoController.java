package com.riki.gestionCursos.gestioncursosspringboot.controller;



import com.riki.gestionCursos.gestioncursosspringboot.entity.Curso;
import com.riki.gestionCursos.gestioncursosspringboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CursoController {


    /*
  La anotación @Autowired en Spring Boot es parte del conjunto de anotaciones de Spring Framework que se utiliza para realizar la inyección
 de dependencias de forma automática. Cuando se utiliza @Autowired en un campo, método setter o constructor de una clase, Spring automáticamente
  intenta encontrar un bean que coincida con el tipo del campo o el parámetro del método y lo inyecta en ese campo o método durante la inicialización del bean.

    * */
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/home")
    public String home(){


        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model){

        //al tener el autowired podemos acceder a los metodos de JPARepository de cursoResository
        List<Curso> cursos = cursoRepository.findAll();

        cursos = cursoRepository.findAll();
        // agregamos un atributo a la visto ... nombre y objeto
        model.addAttribute("cursos",cursos);
        return "cursos";
    }


    @GetMapping("/cursos/nuevo")
    public String agregarCurso(Model model){
        Curso curso = new Curso();
        curso.setPublicado(true);
        // el objeto curso se le envia al form y se le va a asignar al objeto y se usa PostMapping para enviarlo a la bbdd
        model.addAttribute("curso",curso);

        model.addAttribute("pageTitle","Nuevo Curso");
        return "curso_form";
    }
    @PostMapping("/cursos/save")
    public String guardarCurso(Curso curso, RedirectAttributes redirectAttributes){

        try{
            cursoRepository.save(curso);
            //Esto es basicamente como un JOptionPane (ventana emergente)
            redirectAttributes.addFlashAttribute("message","El curso ha sido guardado con exito");
        }catch(Exception e){

            redirectAttributes.addAttribute("message",e.getMessage());

        }
        return "redirect:/cursos";
    }


    @GetMapping("/cursos/{id}")
    public String editarCurso(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){

        try{
            Curso curso= cursoRepository.findById(id).get();

            model.addAttribute("curso",curso);
            model.addAttribute("pageTitle","Editar Curso :" +id );

            //Esto es basicamente como un JOptionPane (ventana emergente)
            return "curso_form";
        }catch(Exception e){

            redirectAttributes.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/cursos";
    }

    @GetMapping("/cursos/delete/{id}")
    public String eliminarCurso(@PathVariable Integer id,Model model, RedirectAttributes redirectAttributes){

        try{

            cursoRepository.deleteById(id);
            redirectAttributes.addAttribute("message","Cruso Eliminado con Exito");


        }catch(Exception e) {

            redirectAttributes.addFlashAttribute("message",e.getMessage());


        }
        return "redirect:/cursos";
    }
}

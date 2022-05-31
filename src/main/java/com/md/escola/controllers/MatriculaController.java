package com.md.escola.controllers;

import com.md.escola.models.*;
import com.md.escola.service.AlunoService;
import com.md.escola.service.MatriculaService;
import com.md.escola.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MatriculaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping(value = "/admin/matricula")
    public ModelAndView createMatricula(){
        ModelAndView modelAndView = new ModelAndView("matricula");
        Matricula matricula = new Matricula();
        List<Turma> turmas = turmaService.getAll();
        List<Aluno> alunos = alunoService.getAll();

        modelAndView.addObject("turmas", turmas);
        modelAndView.addObject("alunos", alunos);
        modelAndView.addObject("matricula", matricula);

        return modelAndView;
    }

    @PostMapping(value = "/admin/matricula-salvar")
    public String createNewMatricula(Matricula matricula){
        ModelAndView modelAndView = new ModelAndView();
        matriculaService.insert(matricula);
        modelAndView.addObject("successMessage", "Matricula realizada com sucesso");
        Matricula matricula1 = new Matricula();
        modelAndView.addObject("matricula", matricula1);
        modelAndView.setViewName("matricula");
        return "redirect:/admin/listar-matriculas";
    }


    @GetMapping(value = "/admin/listar-matriculas")
    public ModelAndView listMatriculas(){
        ModelAndView modelAndView = new ModelAndView("listar-matricula");
        List<Matricula> matriculas = matriculaService.getAll();
        modelAndView.addObject("matriculas", matriculas);
        return modelAndView;
    }



    @GetMapping(value = "/profesor/editar-nota/{id}")
    public ModelAndView editNota(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("editar-nota");
        Matricula matricula = matriculaService.findById(id);
        modelAndView.addObject("matricula", matricula);
        return modelAndView;
    }

    @RequestMapping(value = "/salvar-nota")
    public String atualizarNota(Matricula matricula){

        matriculaService.update(matricula);

        return "redirect:/home-professor";
    }


}

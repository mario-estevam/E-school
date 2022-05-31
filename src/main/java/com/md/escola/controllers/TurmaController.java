package com.md.escola.controllers;

import com.md.escola.models.*;
import com.md.escola.service.DisciplinaService;
import com.md.escola.service.PeriodoService;
import com.md.escola.service.ProfessorService;
import com.md.escola.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorService professorService;


    @Autowired
    private PeriodoService periodoService;

    @GetMapping(value = "/cadastro-turma")
    public ModelAndView createTurma(){
        ModelAndView modelAndView = new ModelAndView("turma");
        Turma turma = new Turma();
        List<Periodo> periodos = periodoService.getAll();
        List<Disciplina> disciplinas = disciplinaService.getAll();
        List<Professor> professores = professorService.getAll();

        modelAndView.addObject("turma", turma);
        modelAndView.addObject("professores", professores);
        modelAndView.addObject("periodos", periodos);
        modelAndView.addObject("disciplinas", disciplinas);
        return modelAndView;
    }


    @GetMapping(value = "/listar-turmas")
    public ModelAndView listDiscplinas(){
        ModelAndView modelAndView = new ModelAndView("listar-turmas");
        List<Turma> turmas = turmaService.getAll();
        modelAndView.addObject("turmas", turmas);
        return modelAndView;
    }




    @PostMapping(value = "/salvar-turma")
    public String createNewTurma(Turma turma){

        turmaService.insert(turma);

        return "redirect:/cadastro-turma";
    }
}

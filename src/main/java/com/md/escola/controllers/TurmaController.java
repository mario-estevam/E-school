package com.md.escola.controllers;

import com.md.escola.models.Disciplina;
import com.md.escola.models.Pessoa;
import com.md.escola.models.Turma;
import com.md.escola.service.DisciplinaService;
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

    @GetMapping(value = "/cadastro-turma")
    public ModelAndView createTurma(){
        ModelAndView modelAndView = new ModelAndView("turma");
        Turma turma = new Turma();
        List<Disciplina> disciplinas = disciplinaService.getAll();
        List<Pessoa> professores = new ArrayList<>();
        professorService.getAll().forEach(professor -> {
            professores.add(professor.getPessoa());
        });
        modelAndView.addObject("turma", turma);
        modelAndView.addObject("professores", professores);
        modelAndView.addObject("disciplinas", disciplinas);
        return modelAndView;
    }

    @PostMapping(value = "/salvar-turma")
    public ModelAndView createNewTurma(@Valid Turma turma, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("turma", turma);
            modelAndView.setViewName("turma");
            return modelAndView;
        }
        turmaService.insert(turma, turma.getProfessor().getId());
        modelAndView.addObject("successMessage", "A turma foi cadastrada com sucesso");
        modelAndView.addObject("turma", turma);
        modelAndView.setViewName("turma");
        return modelAndView;
    }
}

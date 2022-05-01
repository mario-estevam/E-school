package com.md.escola.controllers;

import com.md.escola.models.Pessoa;
import com.md.escola.models.Professor;
import com.md.escola.service.PessoaService;
import com.md.escola.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfessorController {


    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/cadastro-professor")
    public ModelAndView createProfessor(){
        ModelAndView modelAndView = new ModelAndView();
        Professor professor = new Professor();
        List<Pessoa> pessoas = pessoaService.getAll();
        modelAndView.addObject("professor", professor);
        modelAndView.addObject("pessoas", pessoas);
        modelAndView.setViewName("professor");
        return modelAndView;
    }

    @PostMapping(value = "/salvar-professor")
    public ModelAndView getProfessor(Professor professor){
        ModelAndView modelAndView = new ModelAndView();
        professorService.insert(professor, professor.getPessoa().getCpf());
        modelAndView.addObject("professor", professor);
        modelAndView.setViewName("professor");
        return modelAndView;
    }




}

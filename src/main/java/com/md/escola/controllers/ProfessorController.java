package com.md.escola.controllers;

import com.md.escola.models.Pessoa;
import com.md.escola.models.Professor;
import com.md.escola.models.Turma;
import com.md.escola.models.User;
import com.md.escola.service.PessoaService;
import com.md.escola.service.ProfessorService;
import com.md.escola.service.TurmaService;
import com.md.escola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {


    @Autowired
    private TurmaService turmaService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UserService userService;

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


    @GetMapping(value = "/home-professor")
    public ModelAndView getTurmasByProfessor(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        System.out.println(user);
        Long id = user.getPessoa().getId();
        Professor professor = professorService.getId(id);
        List<Turma> turmas = turmaService.getTurmasByProfessor(professor);
        modelAndView.addObject("turmas", turmas);
        modelAndView.setViewName("professor-home");
        return modelAndView;
    }






}

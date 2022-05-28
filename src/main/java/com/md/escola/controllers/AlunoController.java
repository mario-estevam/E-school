package com.md.escola.controllers;


import com.md.escola.models.Aluno;
import com.md.escola.models.Matricula;
import com.md.escola.models.User;
import com.md.escola.service.AlunoService;
import com.md.escola.service.MatriculaService;
import com.md.escola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    MatriculaService matriculaService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/aluno/listar-matriculas")
    public ModelAndView listMatriculas(){
        ModelAndView modelAndView = new ModelAndView("aluno-matriculas");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        Aluno aluno = alunoService.findByPessoa(user.getPessoa());
        List<Matricula> matriculas = matriculaService.findByAluno(aluno);
        modelAndView.addObject("matriculas", matriculas);
        return modelAndView;
    }


    @GetMapping(value = "/aluno/visualizar-nota/{id}")
    public ModelAndView listNotasByMatricula(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("aluno-notas");
        Matricula matricula = matriculaService.findById(id);
        modelAndView.addObject("matricula", matricula);
        return modelAndView;
    }




}

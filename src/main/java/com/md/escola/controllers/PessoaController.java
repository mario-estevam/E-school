package com.md.escola.controllers;

import com.md.escola.models.Pessoa;
import com.md.escola.models.User;
import com.md.escola.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping(value="/cadastro")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
         Pessoa pessoa = new Pessoa();
        modelAndView.addObject("pessoa", pessoa);
        modelAndView.setViewName("cadastrarPessoa");
        return modelAndView;
    }


    @PostMapping("/cadastro")
    public ModelAndView cadastarPessoa(@Valid Pessoa pessoa, Errors errors){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pessoa", pessoa);
        modelAndView.setViewName("cadastrarPessoa");
        service.insert(pessoa);
        return modelAndView;
    }

}

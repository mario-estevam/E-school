package com.md.escola.controllers;

import com.md.escola.models.Disciplina;
import com.md.escola.service.DisciplinaService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping(value = "/cadastro-disciplina")
    public ModelAndView createDisciplina(){
        ModelAndView modelAndView = new ModelAndView("disciplina");
        Disciplina disciplina = new Disciplina();
        modelAndView.addObject("disciplina", disciplina);
        return modelAndView;
    }

    @PostMapping(value = "/salvar-disciplina")
    public ModelAndView createNewDiscipline(@Valid Disciplina disciplina, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("disciplina", disciplina);
            modelAndView.setViewName("disciplina");
            return modelAndView;
        }
        disciplinaService.insert(disciplina);
        modelAndView.addObject("successMessage", "Disciplina cadastrada com sucesso");;
        modelAndView.addObject("disciplina", disciplina);
        modelAndView.setViewName("disciplina");
        return modelAndView;
    }
}

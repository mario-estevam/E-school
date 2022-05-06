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
import java.util.List;

@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping(value = "/cadastro-disciplina")
    public ModelAndView createDisciplina(){
        ModelAndView modelAndView = new ModelAndView("disciplina");
        List<Disciplina> disciplinas = disciplinaService.getAll();
        modelAndView.addObject("disciplinas", disciplinas);
        Disciplina disciplina = new Disciplina();
        modelAndView.addObject("disciplina", disciplina);
        return modelAndView;
    }

    @GetMapping(value = "/listar-disciplinas")
    public ModelAndView listDiscplinas(){
        ModelAndView modelAndView = new ModelAndView("listar-disciplina");
        List<Disciplina> disciplinas = disciplinaService.getAll();
        modelAndView.addObject("disciplinas", disciplinas);
        return modelAndView;
    }


    @PostMapping(value = "/cadastro-disciplina")
    public ModelAndView createNewDiscipline(@Valid Disciplina disciplina, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("disciplina", disciplina);
            modelAndView.setViewName("disciplina");
            return  modelAndView;
        }
        Boolean confirm = disciplinaService.findByName(disciplina.getNome());
        if(!confirm){
            modelAndView.addObject("disciplinaValidate","Esta disciplina já foi cadastrada");
            modelAndView.addObject("disciplina", disciplina);
            modelAndView.setViewName("disciplina");
        }else{
            disciplinaService.insert(disciplina);
            modelAndView.addObject("successMessage", "Disciplina cadastrada com sucesso");;
            modelAndView.addObject("disciplina", disciplina);
            modelAndView.setViewName("disciplina");

        }
        return modelAndView;
    }
}

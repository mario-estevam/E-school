package com.md.escola.controllers;

import com.md.escola.models.Disciplina;
import com.md.escola.models.User;
import com.md.escola.service.DisciplinaService;
import com.md.escola.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/cadastro-disciplina")
    public ModelAndView createDisciplina(){
        ModelAndView modelAndView = new ModelAndView("disciplina");
        List<Disciplina> disciplinas = disciplinaService.getAll();
        modelAndView.addObject("disciplinas", disciplinas);
        Disciplina disciplina = new Disciplina();
        modelAndView.addObject("disciplina", disciplina);
        return modelAndView;
    }

    @GetMapping(value = "/admin/listar-disciplinas")
    public ModelAndView listDiscplinas(){
        ModelAndView modelAndView = new ModelAndView("listar-disciplina");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user.getRole().getId() != 1) {
            modelAndView.setViewName("error");
        } else {
            List<Disciplina> disciplinas = disciplinaService.getAll();
            modelAndView.addObject("disciplinas", disciplinas);
        }
        return modelAndView;
    }

    @GetMapping(value = "/admin/editar-disciplina/{id}")
    public ModelAndView updateDisciplina(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        Disciplina disciplina = disciplinaService.findById(id);
        modelAndView.addObject("disciplina", disciplina);
        modelAndView.setViewName("atualizar-disciplina");
        return modelAndView;
    }

    @PostMapping(value = "/admin/atualizar-disciplina")
    public String editSave(@ModelAttribute Disciplina disciplina, RedirectAttributes redirectAttributes){
        disciplinaService.insert(disciplina);
        redirectAttributes.addAttribute("msg", "Disciplina atualizada com sucesso");
        return "redirect:/listar-disciplinas";
    }


    @RequestMapping("/admin/deletar-disciplina/{id}")
    public String doDelete(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes){
        disciplinaService.delete(id);
        redirectAttributes.addAttribute("msg", "Disciplina deletada com sucesso");
        return "redirect:/listar-disciplinas";
    }

    @PostMapping(value = "/admin/cadastro-disciplina")
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
            modelAndView.addObject("successMessage", "Disciplina cadastrada com sucesso");
            Disciplina disciplina1 = new Disciplina();
            modelAndView.addObject("disciplina", disciplina1);
            modelAndView.setViewName("disciplina");
        }
        return modelAndView;
    }
}

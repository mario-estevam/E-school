package com.md.escola.controllers;

import com.md.escola.models.*;
import com.md.escola.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;


    @GetMapping(value = "/cadastro-professor")
    public ModelAndView createProfessor(){

        Role role = roleRepository.findByRole("PROFESSOR");

        ModelAndView modelAndView = new ModelAndView();
        Professor professor = new Professor();
        List<User> usuarios = userService.getUsersByRole(role);
        modelAndView.addObject("professor", professor);
        modelAndView.addObject("usuarios", usuarios);
        modelAndView.setViewName("professor");
        return modelAndView;
    }

    @PostMapping(value = "/cadastro-professor")
    public ModelAndView getProfessor(Professor professor){
        ModelAndView modelAndView = new ModelAndView();
        Boolean professorExist = professorService.ProfessorIsExist(professor.getSiape());
        System.out.println(professorExist);

        if(professorExist){
            modelAndView.addObject("avisoProfessor", "Professor já cadastrado no sistema!");
            modelAndView.setViewName("professor");


        }else{
            professorService.insert(professor, professor.getPessoa().getCpf());
            modelAndView.addObject("professor", professor);
            modelAndView.setViewName("professor");


        }

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

package com.md.escola.controllers;

import com.md.escola.models.*;
import com.md.escola.repository.RoleRepository;
import com.md.escola.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private MatriculaService matriculaService;




    @PostMapping(value = "/save-atualizar-professor")
    public String atualizarProfessor(Professor professor, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        Boolean existSiape = professorService.ProfessorIsExist(professor.getSiape());
        Pessoa pessoa = pessoaService.getPessoaByCpf(professor.getPessoa().getCpf());
        System.out.println(pessoa.getCpf());
        System.out.println(professor.getPessoa().getCpf());

        if((professor.getPessoa().getCpf().equals(pessoa.getCpf())) || (pessoa.getCpf() == null)){
            System.out.println("entrou");
            professorService.insert(professor, professor.getPessoa().getCpf());
            return "redirect:/listar-professores";


        }else{
          redirectAttributes.addAttribute("validacaoCpf", "Cpf já cadastrado no sistema");
         return "redirect:/listar-professores";
      }

    }


    @GetMapping(value = "/home-professor")
    public ModelAndView getTurmasByProfessor(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        Professor professor = professorService.getPessoa(user.getPessoa());
        List<Turma> turmas = turmaService.getTurmasByProfessor(professor);
        System.out.println(turmas);
        modelAndView.addObject("turmas", turmas);
        modelAndView.setViewName("professor-home");
        return modelAndView;
    }

    @GetMapping(value = "listar-professores")
    public ModelAndView listProfessores(){
        ModelAndView modelAndView = new ModelAndView();
        List<Professor> listProfessores = professorService.getAll();
        modelAndView.addObject("professores", listProfessores);
        modelAndView.setViewName("listar-professor");

        return  modelAndView;
    }

    @PostMapping(value = "/admin/atualizar-professor")
    public String editSave(@ModelAttribute Professor professor, RedirectAttributes redirectAttributes){
        professorService.insert(professor, professor.getPessoa().getCpf());
        redirectAttributes.addAttribute("atualiza-professor", "Dados atualizados");
        return "redirect:/listar-disciplina";
    }


    @GetMapping(value = "/admin/professor/editar/{id}")
    public ModelAndView editUsuer(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        Professor professor = professorService.getId(id);
        modelAndView.addObject("professor",professor);
        modelAndView.setViewName("atualizar-professor");
        return  modelAndView;
    }


    @GetMapping(value = "/professor/visualizar-matriculas/{id}")
    public ModelAndView findMatriculasByTurma(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        Turma turma = turmaService.findById(id);
        List<Matricula> matriculas = matriculaService.findMatriculaByTurma(turma);
        modelAndView.addObject("matriculas",matriculas);
        modelAndView.setViewName("professor-matriculas");
        return  modelAndView;
    }

}

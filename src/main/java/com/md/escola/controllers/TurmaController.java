package com.md.escola.controllers;

import com.md.escola.models.*;
import com.md.escola.service.DisciplinaService;
import com.md.escola.service.PeriodoService;
import com.md.escola.service.ProfessorService;
import com.md.escola.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorService professorService;


    @Autowired
    private PeriodoService periodoService;

    @GetMapping(value = "/admin/cadastro-turma")
    public ModelAndView createTurma(){
        ModelAndView modelAndView = new ModelAndView("turma");
        Turma turma = new Turma();
        List<Periodo> periodos = periodoService.getAll();
        List<Disciplina> disciplinas = disciplinaService.getAll();
        List<Professor> professores = professorService.getAll();
        modelAndView.addObject("turma", turma);
        modelAndView.addObject("professores", professores);
        modelAndView.addObject("periodos", periodos);
        modelAndView.addObject("disciplinas", disciplinas);
        return modelAndView;
    }

    @GetMapping(value = "/listar-turmas")
    public ModelAndView listTurmas(){
        ModelAndView modelAndView = new ModelAndView("listar-turmas");
        List<Turma> turmas = turmaService.getAll();
        modelAndView.addObject("turmas", turmas);
        return modelAndView;
    }

    @GetMapping(value = "/admin/editar-turma/{id}")
    public ModelAndView editTurma(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("atualizar-turma");
        Turma turma = turmaService.findById(id);
        List<Periodo> periodos = periodoService.getAll();
        List<Disciplina> disciplinas = disciplinaService.getAll();
        List<Professor> professores = professorService.getAll();
        modelAndView.addObject("turma", turma);
        modelAndView.addObject("professores", professores);
        modelAndView.addObject("periodos", periodos);
        modelAndView.addObject("disciplinas", disciplinas);
        return modelAndView;
    }

    @PostMapping(value = "/admin/atualizar-turma")
    public String updateTurma(@ModelAttribute Turma turma, RedirectAttributes redirectAttributes){
        turmaService.insert(turma);
        redirectAttributes.addAttribute("msg", "Turma atualizada com sucesso");
        return "redirect:/listar-turmas";
    }

    @RequestMapping("/admin/deletar-turma/{id}")
    public String deleteTurma(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes){
        turmaService.delete(id);
        redirectAttributes.addAttribute("msg", "Turma deletada com sucesso");
        return "redirect:/listar-turmas";
    }

    @PostMapping(value = "/salvar-turma")
    public String createNewTurma(Turma turma, RedirectAttributes redirectAttributes){
        turmaService.insert(turma);
        redirectAttributes.addAttribute("msg", "Turma cadastrada com sucesso");
        return "redirect:/admin/cadastro-turma";
    }
}

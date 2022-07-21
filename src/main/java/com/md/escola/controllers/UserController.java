package com.md.escola.controllers;


import com.md.escola.models.Pessoa;
import com.md.escola.models.User;
import com.md.escola.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = {"/index"})
    public String index(RedirectAttributes redirectAttributes) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        if (user != null) {
            redirectAttributes.addAttribute("usuario", user);

        } else {
            return "redirect:/login";
        }
        if (user.getRole().getRole().equals("ADMIN")) {
            return "redirect:/home-admin";
        } else if (user.getRole().getRole().equals("PROFESSOR")) {
            return "redirect:/home-professor";
        }else{
            return "redirect:/aluno/listar-matriculas";

        }
    }




    @GetMapping(value="/home-admin")
    public ModelAndView homeAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home-admin");
        return  modelAndView;

    }

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("usuario", user);
        modelAndView.setViewName("cadastro");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, @RequestParam(value="action",required=true) String action) {

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("usuario", user);
            modelAndView.setViewName("cadastro");
            return modelAndView;
        }
        try {
            Boolean confirm = userService.confirmarSenha(user.getSenha(),user.getRepetirSenha());
            Boolean emailConfirm = userService.findUserByEmail(user.getEmail());
            Boolean userNameConfirm = userService.findUserUsernameBoolean(user.getUserName());
            if(!confirm){
                modelAndView.addObject("senhas","as senhas não coincidem");
                modelAndView.addObject("usuario", user);
                modelAndView.setViewName("cadastro");
            } else if(!userNameConfirm){
                modelAndView.addObject("userName","Este nome de usuário já existe");
                modelAndView.addObject("usuario", user);
                modelAndView.setViewName("cadastro");
            } else if(!emailConfirm){
                modelAndView.addObject("email","Este email já foi cadastrado");
                modelAndView.addObject("usuario", user);
                modelAndView.setViewName("cadastro");
            } else {
                if(action.equals("cancelar")){
                    modelAndView.setViewName("login");


                }else{
                    userService.saveUser(user);
                    modelAndView.addObject("successMessage", "Usuario cadastrado com sucesso");
                    modelAndView.addObject("usuario", new User());
                    if (action.equals("submit")) {
                        modelAndView.setViewName("login");
                    }

                    if (action.equals("salvarAndAdd")) {
                        modelAndView.setViewName("cadastro");
                    }

                }

            }

        }catch (Exception e){
            modelAndView.addObject("erroCpf", "Este CPF já foi cadastrado");
            modelAndView.addObject("usuario", user);
            modelAndView.setViewName("cadastro");
        }

        return modelAndView;
    }



    @PostMapping(value = "/admin/usuario/salvar")
    public String editSave(@ModelAttribute User user, RedirectAttributes redirectAttributes){
       User senha = userService.findUserById(user.getId());
       user.setSenha(senha.getSenha());
       user.setRepetirSenha(senha.getRepetirSenha());
       userService.saveUser(user);
       Pessoa pessoa = user.getPessoa();
       pessoaService.update(pessoa);
       redirectAttributes.addAttribute("msg", "Usuário atualizado com sucesso");
       return "redirect:/admin/listar-usuarios";
    }

    @GetMapping(value = "/editar-usuario/{id}")
    public ModelAndView updateUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(id);
        modelAndView.addObject("usuario", user);
        modelAndView.setViewName("atualizar-usuario");
        return modelAndView;
    }

    @PostMapping(value = "/cancelar")
    public ModelAndView cancelarSubmit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return  modelAndView;
    }

    @RequestMapping("/admin/deletar/{id}")
    public String doDelete(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes){
        userService.delete(id);
        redirectAttributes.addAttribute("msg", "Deletado com sucesso");
        return "redirect:/admin/listar-usuarios";
    }


    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user.getRole().getId() != 1){
            modelAndView.setViewName("error");
        }else{
            modelAndView.addObject("usuario", user);
            modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários com função de administrador");
            modelAndView.setViewName("admin/home");
        }
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping(value = "/admin/listar-usuarios")
    public ModelAndView listUsers(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user.getRole().getId() != 1) {
            modelAndView.setViewName("error");
        }else{
            List<User> usuarios = userService.findAllUsers();
            modelAndView.addObject("usuarios", usuarios);
            modelAndView.setViewName("listar-usuarios");
        }
//        modelAndView.setViewName("listar-usuarios");
        return modelAndView;
    }

    @GetMapping(value="/professor/home")
    public ModelAndView homeProfessor(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        if(user.getRole().getId() != 1){
            modelAndView.setViewName("error");
        }else{
            modelAndView.addObject("usuario", user);
            modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários com função de administrador");
            modelAndView.setViewName("professor-home");
        }
        return modelAndView;
    }



}




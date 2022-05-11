package com.md.escola.controllers;


import com.md.escola.models.User;
import com.md.escola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value={"/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        System.out.println(user.getRole().getRole());
        //FALTA IMPLEMENTAR O REDIRECIONAMENTO POR TIPO DE USUÁRIO
        //esse código abaixo não está funcionando
        if(user != null){
            modelAndView.addObject("usuario", user);

        }else{
            modelAndView.addObject("userName", "Nenhum usuário logado no sistema");
        }
        if(user.getRole().getRole().equals("ADMIN")){
            modelAndView.setViewName("/admin/home");
        } else if(user.getRole().getRole().equals("PROFESSOR")){
            modelAndView.setViewName("professor-home");
        } else if(user.getRole().getRole().equals("ALUNO")){

            modelAndView.setViewName("aluno-home");
        }

        return modelAndView;
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

    @PostMapping(value = "/cancelar")
    public ModelAndView cancelarSubmit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return  modelAndView;

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
            modelAndView.setViewName("professor/home");
        }
        return modelAndView;
    }



}




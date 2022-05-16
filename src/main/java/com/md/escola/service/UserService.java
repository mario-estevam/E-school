package com.md.escola.service;


import com.md.escola.models.*;
import com.md.escola.repository.PessoaRepository;
import com.md.escola.repository.RoleRepository;
import com.md.escola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UsuarioRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    public UserService(UsuarioRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user ==null){
            return true;
        }else{
            return false;
        }
    }

    public Boolean findUserUsernameBoolean(String userName){
        User user = userRepository.findByUserName(userName);
        if(user ==null){
            return true;
        }else{
            return false;
        }
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findUserById(Long id){
        return userRepository.getById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Boolean confirmarSenha(String senha, String repetirSenha){
        if(senha.equals(repetirSenha)){
            return true;
        }else{
            return false;
        }
    }

    public User saveUser(User user) {
        user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
        user.setRepetirSenha(bCryptPasswordEncoder.encode(user.getRepetirSenha()));

        user.setActive(true);
        Pessoa pessoa = user.getPessoa();
        pessoaService.insert(pessoa);
        Role userRole = user.getRole();
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        System.out.println(userRole);
        if(userRole.getRole().equals("ALUNO")){
            Aluno aluno = new Aluno();
            aluno.setPessoa(pessoa);
            String matricula="10101010";
            aluno.setMatriculaGeral(matricula);
            alunoService.saveAluno(aluno);
        }


        return userRepository.save(user);
    }

    public List<User> getUsersByRole(Role role){
        return userRepository.findUsersByRole(role);
    }

}












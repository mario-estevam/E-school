package com.md.escola.service;


import com.md.escola.models.*;
import com.md.escola.repository.RoleRepository;
import com.md.escola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
        return userRepository.findAllByDeleteIsNull();
    }

    public void delete(Long id){
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        User user = userRepository.getById(id);
        user.setDelete(date);
        userRepository.save(user);
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

            LocalDate date = LocalDate.now();
            Random random = new Random();
            int number = random.nextInt(9999);
            String matricula=date.toString()+number;
            aluno.setMatriculaGeral(matricula);
            alunoService.saveAluno(aluno);
        }else if(userRole.getRole().equals("PROFESSOR")){
            Professor professor = new Professor();
            professor.setPessoa(pessoa);
            Random random = new Random();
            int siape = random.nextInt(9999);
            professor.setSiape(siape);
            LocalDate date = LocalDate.now();
            professor.setData_admissao(date);
            professorService.salvar(professor);
        }


        return userRepository.save(user);
    }

    public List<User> getUsersByRole(Role role){
        return userRepository.findUsersByRole(role);
    }

}












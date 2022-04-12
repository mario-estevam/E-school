package com.md.escola.service;


import com.md.escola.models.Pessoa;
import com.md.escola.models.Role;
import com.md.escola.models.User;
import com.md.escola.repository.PessoaRepository;
import com.md.escola.repository.RoleRepository;
import com.md.escola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private UsuarioRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    public UserService(UsuarioRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
        user.setActive(true);
        Pessoa pessoa = user.getPessoa();
        pessoaService.insert(pessoa);
        Role userRole = user.getRole();
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}
package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Length(min = 5, message = "O campo nome deve conter no minimo 5 caracteres")
    @NotEmpty(message = "Por favor insira um nome")
    private String userName;
    @Email(message = "Por favor insira um email válido")
    @NotEmpty(message = "Por favor insira um email")
    private String email;
    @Length(min = 5, message = "Sua senha deve conter no minimo 5 caracteres")
    @NotEmpty(message = "Por favor insira uma senha")
    private String senha;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToOne
    @JoinColumn(name = "role_id")
    Role role;

    // para primeira parte do projeto focado no logine cadastro de usuários, deixaremos omitido o cadastro de Pessoa
//    @OneToOne
//    @JoinColumn(name = "pessoa_id")
//    Pessoa pessoa;


}

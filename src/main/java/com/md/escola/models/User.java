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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
    @NotEmpty(message = "Por favor insira um nome")
    private String nome;
    @NotEmpty(message = "Por favor insira seu sobrenome")
    private String sobrenome;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}

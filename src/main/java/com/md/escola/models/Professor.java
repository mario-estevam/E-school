package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Professor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String data_admissao;
    @OneToMany(mappedBy="professor", fetch = FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.ALL)
    List<Turma> turmas = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    Pessoa pessoa;

}

package com.md.escola.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id")
    Aluno aluno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id")
    Turma turma;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_id")
    Nota nota;

    Long util;


    private Date delete;

}

package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    @JoinColumn(name = "periodo_id")
    Periodo periodo;

    @OneToOne
    @JoinColumn(name = "disciplina_id")
    Disciplina disciplina;

//    [ ] Uma turma tem várias disciplinas
//    @OneToMany
//    @JoinColumn(name = "disciplina_id")
//    List<Disciplina> disciplinas

    String turno;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

//    [ ] Uma turma tem vários professores
//    @OneToMany
//    @JoinColumn(name = "professor_id")
//    private List<Professor> professores;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

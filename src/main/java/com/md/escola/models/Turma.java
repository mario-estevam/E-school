package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    String turno;

    private Date delete;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

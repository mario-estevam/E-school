package com.md.escola.repository;

import com.md.escola.models.Aluno;
import com.md.escola.models.Matricula;
import com.md.escola.models.Professor;
import com.md.escola.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
    List<Matricula> findMatriculaByAluno(Aluno aluno);
    List<Matricula> findMatriculaByTurma(Turma turma);

}

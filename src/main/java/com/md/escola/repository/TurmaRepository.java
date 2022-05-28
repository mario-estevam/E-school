package com.md.escola.repository;

import com.md.escola.models.Professor;
import com.md.escola.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma,Long> {

    List<Turma> findByProfessor(Professor professor);


}

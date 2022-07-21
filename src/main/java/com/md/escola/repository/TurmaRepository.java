package com.md.escola.repository;

import com.md.escola.models.Periodo;
import com.md.escola.models.Professor;
import com.md.escola.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma,Long> {

    List<Turma> findByProfessor(Professor professor);
    List<Turma> findByPeriodo(Periodo periodo);
    List<Turma> findByDeleteIsNull();

    @Query(value = "SELECT count(t) FROM turma t", nativeQuery = true)
    Integer countTurmas();

}

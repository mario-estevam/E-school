package com.md.escola.repository;

import com.md.escola.models.Aluno;
import com.md.escola.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findAlunoByPessoa(Pessoa id);

    @Query(value = "SELECT count(a) FROM aluno a", nativeQuery = true)
    Integer countAlunos();
}

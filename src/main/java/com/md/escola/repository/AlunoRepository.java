package com.md.escola.repository;

import com.md.escola.models.Aluno;
import com.md.escola.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findAlunoByPessoa(Pessoa id);
}

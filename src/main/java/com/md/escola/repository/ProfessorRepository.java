package com.md.escola.repository;

import com.md.escola.models.Pessoa;
import com.md.escola.models.Professor;
import com.md.escola.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    Professor findProfessorByPessoa(Pessoa pessoa);
    Professor findProfessorBySiape(int siape);

    @Query(value = "SELECT count(p) FROM professor p", nativeQuery = true)
    Integer countProfessores();

}

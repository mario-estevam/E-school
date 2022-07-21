package com.md.escola.repository;

import com.md.escola.models.Disciplina;
import com.md.escola.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    Disciplina findByNome(String nome);
    List<Disciplina> findAllByDeleteIsNull();

    @Query(value = "SELECT count(d) FROM disciplina d",nativeQuery = true)
    Integer countDisciplinas();
}

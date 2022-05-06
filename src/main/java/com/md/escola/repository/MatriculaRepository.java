package com.md.escola.repository;

import com.md.escola.models.Matricula;
import com.md.escola.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {



}

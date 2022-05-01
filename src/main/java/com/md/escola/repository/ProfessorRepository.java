package com.md.escola.repository;

import com.md.escola.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository  extends JpaRepository<Professor, Long> {
}

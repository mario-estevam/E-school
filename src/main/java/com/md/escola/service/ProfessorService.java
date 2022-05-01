package com.md.escola.service;

import com.md.escola.models.Professor;
import com.md.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository professorRepository;

  public Professor saveProfessor(Professor professor){

    return  professorRepository.save(professor);

  }
}

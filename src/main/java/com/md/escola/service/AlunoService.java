package com.md.escola.service;

import com.md.escola.models.Aluno;
import com.md.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
  @Autowired
  private AlunoRepository repository;

  public void setRepository(AlunoRepository repository) {
    this.repository = repository;
  }

  public Aluno saveAluno(Aluno aluno){
    return  repository.save(aluno);

  }
}

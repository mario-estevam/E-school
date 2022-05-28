package com.md.escola.service;

import com.md.escola.models.Aluno;
import com.md.escola.models.Pessoa;
import com.md.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Aluno findByPessoa(Pessoa pessoa){
    return repository.findAlunoByPessoa(pessoa);
  }

  public List<Aluno> getAll(){
    return repository.findAll();
  }
}

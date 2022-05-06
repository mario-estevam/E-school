package com.md.escola.service;

import com.md.escola.models.Disciplina;
import com.md.escola.models.Pessoa;
import com.md.escola.repository.DisciplinaRepository;
import com.md.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    @Autowired
    private void setDisciplinaRepository(DisciplinaRepository repository){ this.repository = repository; }

    public Disciplina insert(Disciplina disciplina){
        return repository.save(disciplina);
    }

    public Boolean findByName(String nome){
        Disciplina disciplina = repository.findByNome(nome);
        if(disciplina==null){
            return true;
        }else{
            return false;
        }
    }

    public List<Disciplina> getAll(){
        return repository.findAll();
    }

}
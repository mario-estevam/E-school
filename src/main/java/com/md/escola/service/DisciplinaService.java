package com.md.escola.service;

import com.md.escola.models.Disciplina;
import com.md.escola.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    public Disciplina insert(Disciplina disciplina){
        return repository.save(disciplina);
    }
}
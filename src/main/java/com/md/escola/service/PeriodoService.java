package com.md.escola.service;


import com.md.escola.models.Disciplina;
import com.md.escola.models.Periodo;
import com.md.escola.repository.DisciplinaRepository;
import com.md.escola.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoService {

    @Autowired
    PeriodoRepository repository;

    @Autowired
    private void setDisciplinaRepository(PeriodoRepository repository){ this.repository = repository; }

    public Periodo insert(Periodo periodo){
        return repository.save(periodo);
    }

    public List<Periodo> getAll(){
        return repository.findAll();
    }
}

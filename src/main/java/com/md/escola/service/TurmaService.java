package com.md.escola.service;

import com.md.escola.models.Professor;
import com.md.escola.models.Turma;
import com.md.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    TurmaRepository repository;

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    private void setTurmaRepository(TurmaRepository repository){ this.repository = repository; }

    public Turma insert(Turma turma){
        return repository.save(turma);
    }


    public Turma findById(Long id){
        return repository.getById(id);
    }

    public List<Turma> getTurmasByProfessor(Professor professor){
        return repository.findByProfessor(professor);
    }

    public List<Turma> getAll(){
        return repository.findAll();
    }

}

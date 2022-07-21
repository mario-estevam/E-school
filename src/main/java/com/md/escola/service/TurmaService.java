package com.md.escola.service;

import com.md.escola.models.Periodo;
import com.md.escola.models.Professor;
import com.md.escola.models.Turma;
import com.md.escola.models.User;
import com.md.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void delete(Long id){
        Date date = new Date();
        Turma turma = repository.getById(id);
        turma.setDelete(date);
        repository.save(turma);
    }

    public List<Turma> findByPeriodo(Periodo periodo){
        return repository.findByPeriodo(periodo);
    }

    public Turma findById(Long id){
        return repository.getById(id);
    }

    public List<Turma> getTurmasByProfessor(Professor professor){
        return repository.findByProfessor(professor);
    }

    public List<Turma> getAll(){
        return repository.findByDeleteIsNull();
    }

    public Integer countTurmas(){ return repository.countTurmas();}

}

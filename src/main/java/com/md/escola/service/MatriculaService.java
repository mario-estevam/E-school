package com.md.escola.service;


import com.md.escola.models.*;
import com.md.escola.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    MatriculaRepository repository;

    @Autowired
    AlunoService alunoService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private void setMatriculaRepository(MatriculaRepository repository){
        this.repository = repository;
    }

    public  Matricula insert(Matricula matricula){
        Nota nota = new Nota();
        nota.setNota1(0.0);
        nota.setNota2(0.0);
        nota.setNota3(0.0);
        nota.setRec(0.0);
        matricula.setNota(nota);
        return repository.save(matricula);
    }

    public  Matricula update(Matricula matricula){

        return repository.save(matricula);
    }

    public Matricula findById(Long id){
        return repository.getById(id);
    }

    public List<Matricula> findMatriculaByTurma(Turma turma){
        return repository.findMatriculaByTurma(turma);
    }

    public List<Matricula> findByAluno(Aluno aluno){
        return repository.findMatriculaByAluno(aluno);
    }

    public List<Matricula> getAll(){
        return repository.findAll();
    }



}

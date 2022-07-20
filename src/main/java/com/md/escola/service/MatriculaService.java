package com.md.escola.service;


import com.md.escola.models.*;
import com.md.escola.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private PeriodoService periodoService;

    @Autowired
    private void setMatriculaRepository(MatriculaRepository repository){
        this.repository = repository;
    }

    public Matricula insert(Matricula matricula){
        Nota nota = new Nota();
        nota.setNota1(0.0);
        nota.setNota2(0.0);
        nota.setNota3(0.0);
        nota.setRec(0.0);
        matricula.setNota(nota);
        return repository.save(matricula);
    }

    public List<Matricula> insertList(List<Matricula> matriculas){
        return repository.saveAll(matriculas);
    }

    public  Matricula update(Matricula matricula){
        double media = (matricula.getNota().getNota1() +  matricula.getNota().getNota2() +  matricula.getNota().getNota3())/3;
        if(media < 7){
            double rec = 21 - media;
            Nota nota = new Nota();
            nota.setNota1(matricula.getNota().getNota1());
            nota.setNota2(matricula.getNota().getNota2());
            nota.setNota3(matricula.getNota().getNota3());
            nota.setRec(rec);
            matricula.setNota(nota);
        } else if( media > 7){
            Nota nota = new Nota();
            nota.setNota1(matricula.getNota().getNota1());
            nota.setNota2(matricula.getNota().getNota2());
            nota.setNota3(matricula.getNota().getNota3());
            nota.setRec(0.0);
            matricula.setNota(nota);
        }

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

    public void inserirMatriculas(Matricula matricula) {
        Periodo periodo = periodoService.findById(matricula.getUtil());
        ArrayList<Matricula> matriculas = new ArrayList<>();
        List<Turma> turmas = turmaService.findByPeriodo(periodo);
        for (Turma turma : turmas) {
            Matricula matricula1 = new Matricula();
            matricula1.setTurma(turma);
            matricula1.setAluno(matricula.getAluno());
            Nota nota = new Nota();
            nota.setNota1(0.0);
            nota.setNota2(0.0);
            nota.setNota3(0.0);
            nota.setRec(0.0);
            matricula1.setNota(nota);
            matriculas.add(matricula1);
        }
        insertList(matriculas);
    }

    public void delete(Long id) {
//        TO DO
    }
}

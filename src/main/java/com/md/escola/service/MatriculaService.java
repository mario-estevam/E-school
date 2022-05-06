package com.md.escola.service;


import com.md.escola.models.Matricula;
import com.md.escola.models.Professor;
import com.md.escola.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    MatriculaRepository repository;

    @Autowired
    private void setMatriculaRepository(MatriculaRepository repository){
        this.repository = repository;
    }

//    public List<Matricula> getTurmaByProfessor(Professor professor){
//      return  repository.findByTurmaProfessor(professor);
//    }


}

package com.md.escola.service;

import com.md.escola.models.Pessoa;
import com.md.escola.models.Professor;
import com.md.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    ProfessorRepository repository;
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private void setPessoaRepository(ProfessorRepository repository){ this.repository = repository; }

    public Professor insert(Professor professor, String cpf){
        Pessoa pessoa = pessoaService.getPessoaByCpf(cpf);
        professor.setPessoa(pessoa);
        return repository.save(professor);
    }




}

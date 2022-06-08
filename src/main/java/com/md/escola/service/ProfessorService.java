package com.md.escola.service;

import com.md.escola.models.Pessoa;
import com.md.escola.models.Professor;
import com.md.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.saveAndFlush(professor);
    }

    public Professor salvar(Professor professor){
        return repository.save(professor);
    }


    public List<Professor> getAll(){
        return repository.findAll();
    }

    public Professor findById(Long id){
        return repository.getById(id);
    }

    public Professor getPessoa(Pessoa pessoa){
        return repository.findProfessorByPessoa(pessoa);
    }

    public Professor getId(Long id){
        return repository.getById(id);
    }



    public Boolean ProfessorIsExist(int siape){
        Professor professor = repository.findProfessorBySiape(siape);

       if(professor == null){
           return false;
       }else{
           return true;
       }

    }



}

package com.md.escola.service;

import com.md.escola.models.Pessoa;
import com.md.escola.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    PessoaRepository repository;

    @Autowired
    private void setPessoaRepository(PessoaRepository repository){ this.repository = repository; }

    public Pessoa insert(Pessoa pessoa){
        return repository.save(pessoa);
    }
    public Pessoa update(Pessoa pessoa){
        return repository.save(pessoa);
    }
    public Pessoa getPessoaById(Long id){
        return repository.getById(id);
    }

    public Pessoa getPessoaByCpf(String cpf){
        return repository.getByCpf(cpf);
    }

    public void delete(Pessoa g){
        repository.delete(g);
    }
    public Pessoa getId(Long id){
        return repository.findById(id).orElse(null);
    }
    public List<Pessoa> getAll(){
        return repository.findAll();
    }
}

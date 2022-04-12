package com.md.escola.repository;

import com.md.escola.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Pessoa getByCpf(String cpf);
}

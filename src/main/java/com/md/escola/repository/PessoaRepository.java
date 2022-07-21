package com.md.escola.repository;

import com.md.escola.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa getByCpf(String cpf);

    @Query(value = "SELECT count(p) FROM pessoa p", nativeQuery = true)
    Integer countPessoas();
}

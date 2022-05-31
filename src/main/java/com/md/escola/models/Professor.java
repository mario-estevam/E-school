package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Professor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate data_admissao;
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    Pessoa pessoa;
    int siape;

}

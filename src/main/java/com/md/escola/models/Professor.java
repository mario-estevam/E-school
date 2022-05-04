package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Professor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String data_admissao;
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    Pessoa pessoa;

}

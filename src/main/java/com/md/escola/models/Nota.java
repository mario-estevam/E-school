package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Nota  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    double nota1;
    double nota2;
    double nota3;
    double rec;

}

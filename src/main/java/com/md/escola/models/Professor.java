package com.md.escola.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Professor extends User  {

    String formacao;

    @OneToMany(mappedBy="professor", fetch = FetchType.LAZY, orphanRemoval=true, cascade = CascadeType.ALL)
    List<Turma> turmas = new ArrayList<>();


}

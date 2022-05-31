package com.md.escola.service;

import com.md.escola.models.Disciplina;
import com.md.escola.models.Pessoa;
import com.md.escola.models.User;
import com.md.escola.repository.DisciplinaRepository;
import com.md.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    @Autowired
    private void setDisciplinaRepository(DisciplinaRepository repository){ this.repository = repository; }

    public Disciplina insert(Disciplina disciplina){
        return repository.save(disciplina);
    }

    public void delete(Long id){
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Disciplina disciplina = repository.getById(id);
        disciplina.setDelete(date);
        repository.save(disciplina);
    }

    public Disciplina findById(Long id){
        return repository.getById(id);
    }


    public Boolean findByName(String nome){
        Disciplina disciplina = repository.findByNome(nome);
        if(disciplina==null){
            return true;
        }else{
            return false;
        }
    }

    public List<Disciplina> getAll(){
        return repository.findAllByDeleteIsNull();
    }

}
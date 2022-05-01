package com.md.escola;

import com.md.escola.models.Role;
import com.md.escola.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscolaApplication {


    public static void main(String[] args) {
        SpringApplication.run(EscolaApplication.class, args);
    }

}

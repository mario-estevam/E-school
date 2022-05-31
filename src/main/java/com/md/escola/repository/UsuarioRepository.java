package com.md.escola.repository;

import com.md.escola.models.Role;
import com.md.escola.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
    List <User> findUsersByRole(Role role);
    List<User> findAllByDeleteIsNull();
}

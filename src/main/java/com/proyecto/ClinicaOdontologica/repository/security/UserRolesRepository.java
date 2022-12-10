package com.proyecto.ClinicaOdontologica.repository.security;

import com.proyecto.ClinicaOdontologica.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional //(readOnly = true)
public interface UserRolesRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}

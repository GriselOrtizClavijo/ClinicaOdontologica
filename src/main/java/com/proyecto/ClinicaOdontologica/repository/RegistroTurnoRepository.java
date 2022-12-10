package com.proyecto.ClinicaOdontologica.repository;


import com.proyecto.ClinicaOdontologica.entity.RegistroTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroTurnoRepository extends JpaRepository<RegistroTurno, Integer> {

}

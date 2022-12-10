package com.proyecto.ClinicaOdontologica.repository;

import com.proyecto.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRespository extends JpaRepository<Paciente, Integer> {
}

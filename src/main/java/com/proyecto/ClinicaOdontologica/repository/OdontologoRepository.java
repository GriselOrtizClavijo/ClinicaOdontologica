package com.proyecto.ClinicaOdontologica.repository;

import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {

    /*@Query("SELECT o FROM Odontologo WHERE o.id = :id1")
    Odontologo buscarOdontologo(Integer id);

    @Query("INSERT INTO ODONTOLOGOS ( matricula, nombre, apellido) VALUES (?,?,?)")
    Odontologo agregarOdontologo(Odontologo odontologo);

    @Query("DELETE FROM ODONTOLOGOS WHERE ID=?")
    Odontologo eliminarOdontologo(Integer id);

    @Query("UPDATE ODONTOLOGOS SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID=? ")
    Odontologo modificarOdontologo(Integer id);*/



}

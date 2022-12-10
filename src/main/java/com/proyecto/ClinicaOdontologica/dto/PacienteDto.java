package com.proyecto.ClinicaOdontologica.dto;

import com.proyecto.ClinicaOdontologica.entity.Domicilio;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Component
public class PacienteDto {

    public Integer id;

    @NotNull
    public String nombre;

    @NotNull
    public String apellido;
    public Domicilio domicilio;
    @NotNull
    public Integer dni;
    public Date fechaAlta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "\nPaciente: " +
                "\nid=" + id +
                ", \nnombre='" + nombre + '\'' +
                ", \napellido='" + apellido + '\'' +
                ", \ndni=" + dni +
                ", \nfechaAlta=" + fechaAlta +
                ", \ndomicilio=" + domicilio + '\'' ;
    }
}

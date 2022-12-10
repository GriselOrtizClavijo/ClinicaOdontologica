package com.proyecto.ClinicaOdontologica.dto;

import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

@Component
public class RegistroTurnoDto {

    public Integer id;
    @NotNull
    public Date fecha;
    public Time hora;

    public Paciente paciente;
    public Odontologo odontologo;

    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    @Override
    public String toString() {
        return "\nRegistroTurnoDto: " +
                "\nid=" + id +
                ", \nfecha=" + fecha +
                ", \nhora=" + hora +  '\'' +
                ", \npaciente=" + paciente.getNombre() + " " + paciente.getApellido() +  '\'' +
                ", \nodontologo=" + odontologo.getNombre() + " " + odontologo.getApellido() + '\'';
    }
}

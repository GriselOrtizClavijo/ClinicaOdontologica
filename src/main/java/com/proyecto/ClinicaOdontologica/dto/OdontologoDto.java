package com.proyecto.ClinicaOdontologica.dto;


import org.springframework.beans.factory.annotation.Autowired;


public class OdontologoDto {

    public Integer id;

    public String nombre;

    public String apellido;
    public Integer matricula;

    @Autowired
    public OdontologoDto(String nombre, String apellido, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }


    public Integer getId() {
        return id;
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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "\nOdontologo: " +
                "\nid=" + id +
                ", \nnombre='" + nombre + '\'' +
                ", \napellido='" + apellido + '\'' +
                ", \nmatricula=" + matricula + '\'';
    }
}

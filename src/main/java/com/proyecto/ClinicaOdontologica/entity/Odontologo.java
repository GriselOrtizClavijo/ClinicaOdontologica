package com.proyecto.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="Odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private int matricula;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Set<RegistroTurno> turno;

    public Odontologo() {}

    public Odontologo(String nombre, String apellido, int matricula) {
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }


    public Set<RegistroTurno> getTurno() {
        return turno;
    }

    public void setTurno(Set<RegistroTurno> turno) {
        this.turno = turno;
    }


}

package com.proyecto.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
public class Paciente  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private int dni;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaAlta;

   @OneToMany(fetch = FetchType.EAGER)
   @JsonIgnoreProperties("hibernateLazyInitializer")
    private Set<RegistroTurno> turno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    @JsonIgnoreProperties
    private Domicilio domicilio;

    public Paciente() {}


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


    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }


    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getId() {
        return id;
    }


    public Date getFechaAlta() {
        return fechaAlta;
    }

    public Set<RegistroTurno> getTurno() {
        return turno;
    }

    public void setTurno(Set<RegistroTurno> turno) {
        this.turno = turno;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

}

package com.proyecto.ClinicaOdontologica.controllers;

import com.proyecto.ClinicaOdontologica.service.OdontologoService;
import com.proyecto.ClinicaOdontologica.service.PacienteService;
import com.proyecto.ClinicaOdontologica.dto.PacienteDto;
import com.proyecto.ClinicaOdontologica.exception.BadRequestException;
import com.proyecto.ClinicaOdontologica.exception.OkException;
import com.proyecto.ClinicaOdontologica.exception.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping("/pacientes")
public class PacienteControllers {

    private static final Logger logger = LogManager.getLogger(OdontologoService.class);
    @Autowired
    private PacienteService pacienteService;


    @GetMapping
    public ResponseEntity<Set<PacienteDto>> listarPacientes() throws ResourceNotFoundException, OkException {
       Set<PacienteDto> pacienteDto = pacienteService.listarPacientes();
        logger.info("Se listan los pacientes desde el controller");
        return ResponseEntity.status(HttpStatus.FOUND).body(pacienteDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> buscarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.buscarPacientes(id);
            logger.info("Se encuentra paciente desde el controller");
            return ResponseEntity.status(HttpStatus.FOUND).body("Se encuentra paciente: " + pacienteDto.toString());
    }

    @PostMapping
    public ResponseEntity<PacienteDto> agregarPaciente(@Valid @RequestBody PacienteDto pacienteDto) throws BadRequestException {
        pacienteService.crearPaciente(pacienteDto);
        logger.info("Se agrega Paciente desde el controller");
        return ResponseEntity.ok(pacienteDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacientes(@PathVariable int id) throws ResourceNotFoundException, OkException {
        pacienteService.eliminarPacientes(id);
        logger.info("Se logra eliminar el paciente");
        throw new OkException("Se encuentra y elimina el paciente");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PacienteDto>  modificarUsuarios(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto) throws BadRequestException {
        pacienteService.modificarPacientes(id, pacienteDto);
        return ResponseEntity.ok(pacienteDto);
    }



}

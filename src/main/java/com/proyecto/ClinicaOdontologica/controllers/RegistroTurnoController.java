package com.proyecto.ClinicaOdontologica.controllers;

import com.proyecto.ClinicaOdontologica.service.OdontologoService;
import com.proyecto.ClinicaOdontologica.service.RegistroTurnoService;
import com.proyecto.ClinicaOdontologica.dto.RegistroTurnoDto;
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
@RequestMapping("/turnos")
public class RegistroTurnoController {

    private static final Logger logger = LogManager.getLogger(OdontologoService.class);

    @Autowired
    private RegistroTurnoService registroTurnoService;

    @GetMapping
    public ResponseEntity<Set<RegistroTurnoDto>> listarTurnos() throws ResourceNotFoundException, OkException {
        Set<RegistroTurnoDto> registroTurnoDtos = registroTurnoService.listarTurnos();
        logger.info("Se listan los pacientes desde el controller");
        return ResponseEntity.status(HttpStatus.FOUND).body(registroTurnoDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> buscarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        RegistroTurnoDto registroTurnoDto = registroTurnoService.buscarTurno(id);
            logger.info("Se encuentra turno desde el controller");
           return ResponseEntity.status(HttpStatus.FOUND).body("Se encuentra turno desde el controller" + registroTurnoDto.toString());
    }

    @PostMapping
    public ResponseEntity<RegistroTurnoDto> agregarTurno(@Valid @RequestBody RegistroTurnoDto registroTurnoDto) throws BadRequestException {
        registroTurnoService.crearTurno(registroTurnoDto);
        logger.info("Se agrega turno desde el controller");
        return ResponseEntity.ok(registroTurnoDto);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id) throws ResourceNotFoundException, OkException {
            registroTurnoService.eliminarTurno(id);
            logger.info("Se logra eliminar el turno");
            throw new OkException("Se encuentra y elimina el turno");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RegistroTurnoDto>  modificarTurno(@PathVariable Integer id, @RequestBody RegistroTurnoDto registroTurnoDto) throws BadRequestException {
        registroTurnoService.modificarTurno(id, registroTurnoDto);
        return ResponseEntity.ok(registroTurnoDto);
    }


}

package com.proyecto.ClinicaOdontologica.controllers;

import com.proyecto.ClinicaOdontologica.service.OdontologoService;
import com.proyecto.ClinicaOdontologica.dto.OdontologoDto;
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
@RequestMapping("/odontologos")
public class OdontologoControllers {

    private static final Logger logger = LogManager.getLogger(OdontologoControllers.class);
    @Autowired
    private OdontologoService odontologoService;


   @GetMapping
    public ResponseEntity<Set<OdontologoDto>> listarUsuarios() throws ResourceNotFoundException {
       Set<OdontologoDto> odontologoDto = odontologoService.listarOdontologos();
           logger.info("Se listan los odontologos desde el controller");
           return ResponseEntity.status(HttpStatus.OK).body(odontologoDto);
   }


   @GetMapping("/{id}")
    public ResponseEntity<String> buscarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
       OdontologoDto odontologoDto = odontologoService.buscarOdontologo(id);
       logger.info("Se encuentra odontólogo desde el controller");
       return ResponseEntity.status(HttpStatus.FOUND).body("Se encuentra odontólogo: " + odontologoDto.nombre + " " + odontologoDto.apellido);
   }

    @PostMapping
    public ResponseEntity<OdontologoDto> agregarOdontologo(@Valid @RequestBody OdontologoDto odontologoDto) throws BadRequestException {
        odontologoService.crearOdontologo(odontologoDto);
        logger.info("Se agrega Odontólogo");
        return ResponseEntity.ok(odontologoDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuarios(@PathVariable int id) throws ResourceNotFoundException, OkException {
        odontologoService.eliminarOdontologos(id);
        logger.info("Se logra eliminar el odontólogo");
        throw new OkException("Se encuentra y elimina el odontólogo");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OdontologoDto>  modificarUsuarios(@PathVariable Integer id, @RequestBody OdontologoDto odontologoDto) throws BadRequestException {
        odontologoService.modificarOdontologos(id, odontologoDto);
        return ResponseEntity.ok(odontologoDto);
    }


}
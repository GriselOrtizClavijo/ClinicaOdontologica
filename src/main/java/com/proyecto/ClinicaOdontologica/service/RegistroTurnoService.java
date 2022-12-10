package com.proyecto.ClinicaOdontologica.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.ClinicaOdontologica.entity.RegistroTurno;
import com.proyecto.ClinicaOdontologica.dto.RegistroTurnoDto;
import com.proyecto.ClinicaOdontologica.exception.BadRequestException;
import com.proyecto.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.proyecto.ClinicaOdontologica.repository.RegistroTurnoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegistroTurnoService {


    private static final Logger logger = LogManager.getLogger(OdontologoService.class);
    @Autowired
    private RegistroTurnoRepository registroTurnoRepository;

    @Autowired
    ObjectMapper mapper;

    public Set<RegistroTurnoDto> listarTurnos() throws ResourceNotFoundException {
        List<RegistroTurno> registroTurnos = registroTurnoRepository.findAll();
        Set<RegistroTurnoDto> registroTurnoDtos = new HashSet<>();

        for (RegistroTurno turno : registroTurnos ){
            registroTurnoDtos.add(mapper.convertValue(turno, RegistroTurnoDto.class));
        }

        if(registroTurnoDtos.isEmpty()){
            String messageError = ("No se encuentra la lista de turnos");
            throw new ResourceNotFoundException(messageError);
        }
        logger.info("Se genera la lista de turnos desde el service");
        return registroTurnoDtos;

    }

    public RegistroTurnoDto buscarTurno(Integer id) throws ResourceNotFoundException {
        Optional<RegistroTurno> registroturnoEntity = registroTurnoRepository.findById(id);
        RegistroTurnoDto registroTurnoDto = null;
        if (registroturnoEntity.isPresent()){
            registroTurnoDto = mapper.convertValue(registroturnoEntity, RegistroTurnoDto.class);
            logger.info("Se encuentra el turno por id");
        } else {
            logger.error("No se ha encontrado el turno");
            String messageError = ("No se encuentra el turno solicitado");
            throw new ResourceNotFoundException(messageError);
        }
        return registroTurnoDto;

    }

    public RegistroTurnoDto crearTurno(RegistroTurnoDto registroTurnoDto) throws BadRequestException {
        RegistroTurno registroTurno = null;
        registroTurno = mapper.convertValue(registroTurnoDto, RegistroTurno.class);
        registroTurnoRepository.save(registroTurno);
        registroTurnoDto = mapper.convertValue(registroTurno, RegistroTurnoDto.class);
        logger.info("Se crea el turno en el service");

        if(registroTurnoDto.fecha == null || registroTurnoDto.hora == null || registroTurnoDto.paciente== null || registroTurnoDto.odontologo == null){
            String messageError = ("Error  al crear el turno, verifique los datos ingresados");
            throw new BadRequestException(messageError);
        }
        return registroTurnoDto;

    }

    public RegistroTurnoDto eliminarTurno(int id) throws ResourceNotFoundException {
        Optional<RegistroTurno> registroturnoEntity = registroTurnoRepository.findById(id);
        RegistroTurnoDto registroturnoDto = null;
        if (registroturnoEntity.isPresent()){
            registroTurnoRepository.deleteById(id);
            registroturnoDto = mapper.convertValue(registroturnoEntity, RegistroTurnoDto.class);
            logger.info("Se logra eliminar el turno");
        } else {
            logger.error("No se encuentra el turno");
            throw new ResourceNotFoundException("No existe el turno");
        }
        return registroturnoDto;
    }

    public RegistroTurnoDto modificarTurno(Integer id, RegistroTurnoDto registroTurnoDto) throws BadRequestException {
        Optional<RegistroTurno> registroTurnoEntity = registroTurnoRepository.findById(id);

        if(registroTurnoEntity.isPresent()){
            registroTurnoEntity.get().setFecha((registroTurnoDto.fecha != null ? registroTurnoDto.fecha : registroTurnoEntity.get().getFecha()));
            registroTurnoEntity.get().setHora((registroTurnoDto.hora != null ? registroTurnoDto.hora : registroTurnoEntity.get().getHora()));
            registroTurnoEntity.get().setOdontologo((registroTurnoDto.odontologo != null ? registroTurnoDto.odontologo: registroTurnoEntity.get().getOdontologo()));
            registroTurnoEntity.get().setPaciente((registroTurnoDto.paciente!= null ? registroTurnoDto.paciente: registroTurnoEntity.get().getPaciente()));

            registroTurnoRepository.save(registroTurnoEntity.get());
            registroTurnoDto = mapper.convertValue(registroTurnoEntity, RegistroTurnoDto.class);
            logger.info("Se logra modificar  el turno encontrado");

        } else {
            logger.error("No se encontró el turno y no se modificó correctamente");
            String messageError = ("Error  al modificar los datos o no se encuentra el Turno");
            throw new BadRequestException(messageError);
        }
        return registroTurnoDto;
    }
}

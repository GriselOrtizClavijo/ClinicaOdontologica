package com.proyecto.ClinicaOdontologica.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.dto.PacienteDto;
import com.proyecto.ClinicaOdontologica.exception.BadRequestException;
import com.proyecto.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.proyecto.ClinicaOdontologica.repository.PacienteRespository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {


    private static final Logger logger = LogManager.getLogger(OdontologoService.class);

    @Autowired
    private PacienteRespository pacienteRespository;

   // mapper.setSerializationInclusion(Include.NON_NULL)

    @Autowired
    ObjectMapper mapper;

    public PacienteService(PacienteRespository pacienteRespository) {
        this.pacienteRespository = pacienteRespository;
    }

    public Set<PacienteDto> listarPacientes() throws ResourceNotFoundException {

        List<Paciente> pacientes = pacienteRespository.findAll();
        Set<PacienteDto> pacienteDtoSet = new HashSet<>();

        for (Paciente paciente : pacientes ){
            pacienteDtoSet.add(mapper.convertValue(paciente, PacienteDto.class));
        }
        if(pacienteDtoSet.isEmpty()){
            String messageError = ("No se encuentra la lista de pacientes");
            throw new ResourceNotFoundException(messageError);
        }
        logger.info("Se genera la lista de pacientes desde el service");

        return pacienteDtoSet;
    };

    public PacienteDto buscarPacientes(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteEntity = pacienteRespository.findById(id);
        PacienteDto pacienteDto = null;
        if (pacienteEntity.isPresent()){
            pacienteDto = mapper.convertValue(pacienteEntity, PacienteDto.class);
            logger.info("Se encuentra el paciente por id");

        } else {
            logger.error("No se ha encontrado el paciente");
            String messageError = ("No se encuentra el paciente solicitado");
            throw new ResourceNotFoundException(messageError);
        }
        return pacienteDto;
    }

    public PacienteDto crearPaciente(PacienteDto pacienteDto) throws BadRequestException {
        Paciente pacienteEntity = null;
        pacienteEntity = mapper.convertValue(pacienteDto, Paciente.class);
        pacienteRespository.save(pacienteEntity);
        pacienteDto = mapper.convertValue(pacienteEntity, PacienteDto.class);
        if(pacienteDto.nombre == null || pacienteDto.apellido == null || pacienteDto.dni== null || pacienteDto.fechaAlta == null || pacienteDto.domicilio == null){
            String messageError = ("Error  al crear el odontólogo, verifique los datos ingresados");
            throw new BadRequestException(messageError);
        }
        return  pacienteDto;
    }

    public PacienteDto modificarPacientes(Integer id, PacienteDto pacienteDto) throws BadRequestException {;
        Optional<Paciente> pacienteEntity = pacienteRespository.findById(id);

        if(pacienteEntity.isPresent()){
            pacienteEntity.get().setNombre((pacienteDto.nombre != null ? pacienteDto.nombre : pacienteEntity.get().getNombre()));
            pacienteEntity.get().setApellido((pacienteDto.apellido != null ? pacienteDto.apellido : pacienteEntity.get().getApellido()));
            pacienteEntity.get().setDni((pacienteDto.dni != null ? pacienteDto.dni : pacienteEntity.get().getDni()));
            pacienteEntity.get().setDomicilio((pacienteDto.domicilio != null ? pacienteDto.domicilio: pacienteEntity.get().getDomicilio()));
            pacienteEntity.get().setFechaAlta ((pacienteDto.fechaAlta != null ? pacienteDto.fechaAlta : pacienteEntity.get().getFechaAlta()));

            pacienteRespository.save(pacienteEntity.get());
            pacienteDto = mapper.convertValue(pacienteEntity, PacienteDto.class);
            logger.info("Se logra modificar  el paciente encontrado");
        } else {
            logger.error("No se encontró el paciente y no se modificó correctamente");
            String messageError = ("Error  al modificar los datos o no se encuentra el odontólogo");
            throw new BadRequestException(messageError);
        }
        return pacienteDto ;
    }

    public PacienteDto eliminarPacientes(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteEntity = pacienteRespository.findById(id);
        PacienteDto pacienteDto = null;
        if (pacienteEntity.isPresent()){
            pacienteRespository.deleteById(id);
            pacienteDto = mapper.convertValue(pacienteEntity, PacienteDto.class);
            logger.info("Se logra eliminar el paciente");
        } else {
            logger.error("No se encuentra el paciente");
            throw new ResourceNotFoundException("No existe el paciente");
        }
        return pacienteDto;
    };



}

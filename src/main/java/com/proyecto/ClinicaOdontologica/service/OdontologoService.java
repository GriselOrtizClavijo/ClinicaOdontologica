package com.proyecto.ClinicaOdontologica.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.dto.OdontologoDto;
import com.proyecto.ClinicaOdontologica.exception.BadRequestException;
import com.proyecto.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.proyecto.ClinicaOdontologica.repository.OdontologoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService {

    private static final Logger logger = LogManager.getLogger(OdontologoService.class);
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Set<OdontologoDto> listarOdontologos() throws ResourceNotFoundException {

        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDto> odontologoDtoSet = new HashSet<>();

        for (Odontologo odontologo : odontologos ){
            odontologoDtoSet.add(mapper.convertValue(odontologo, OdontologoDto.class));
        }
        if(odontologoDtoSet.isEmpty()){
            String messageError = ("No se encuentra la lista de odontólogos");
            throw new ResourceNotFoundException(messageError);
        }

        logger.info("Se genera la lista de odontólogo desde el service");
        return odontologoDtoSet;
    };


    public OdontologoDto buscarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoEntity = odontologoRepository.findById(id);
        OdontologoDto odontologoDto = null;
        if (odontologoEntity.isPresent()){
            odontologoDto = mapper.convertValue(odontologoEntity, OdontologoDto.class);
            logger.info("Se encuentra el odontólogo por id");
        } else {
            logger.error("No se ha encontrado el odontólogo");
            String messageError = ("No se encuentra el odontólogo solicitado");
            throw new ResourceNotFoundException(messageError);
        }
        return odontologoDto;
    }

    public OdontologoDto crearOdontologo(OdontologoDto odontologoDto) throws BadRequestException {
        Odontologo odontologoEntity = null;
        odontologoEntity = mapper.convertValue(odontologoDto, Odontologo.class);
        odontologoRepository.save(odontologoEntity);
        odontologoDto = mapper.convertValue(odontologoEntity, OdontologoDto.class);
        if(odontologoDto.nombre == null || odontologoDto.apellido == null || odontologoDto.matricula == null){
            String messageError = ("Error  al crear el odontólogo, verifique los datos ingresados");
            throw new BadRequestException(messageError);
        }
        return  odontologoDto;
    }

    public OdontologoDto modificarOdontologos(Integer id, OdontologoDto odontologoDto) throws BadRequestException {;
        Optional<Odontologo> odontologoEntity = odontologoRepository.findById(id);

        if(odontologoEntity.isPresent()){
            odontologoEntity.get().setNombre((odontologoDto.nombre != null ? odontologoDto.nombre : odontologoEntity.get().getNombre()));
            odontologoEntity.get().setApellido((odontologoDto.apellido != null ? odontologoDto.apellido : odontologoEntity.get().getApellido()));
            odontologoEntity.get().setMatricula((odontologoDto.matricula != null ? odontologoDto.matricula : odontologoEntity.get().getMatricula()));

            odontologoRepository.save(odontologoEntity.get());
            odontologoDto = mapper.convertValue(odontologoEntity, OdontologoDto.class);
            logger.info("Se logra modificar  el odontologo encontrado");

        } else {
            logger.error("No se encontró el odontólogo y no se modificó correctamente");
            String messageError = ("Error  al modificar los datos o no se encuentra el odontólogo");
            throw new BadRequestException(messageError);
        }
        return odontologoDto;
    }


    public OdontologoDto eliminarOdontologos(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoEntity = odontologoRepository.findById(id);
        OdontologoDto odontologoDto = null;
        if (odontologoEntity.isPresent()){
            odontologoRepository.deleteById(id);
            odontologoDto = mapper.convertValue(odontologoEntity, OdontologoDto.class);
            logger.info("Se logra eliminar el odontólogo");
        } else {
            logger.error("No se encuentra el odontólogo");
            throw new ResourceNotFoundException("No existe el odontólogo");
        }
        return odontologoDto;
    };

}


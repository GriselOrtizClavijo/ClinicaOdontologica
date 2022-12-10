package com.proyecto.ClinicaOdontologica.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.ClinicaOdontologica.entity.Domicilio;
import com.proyecto.ClinicaOdontologica.repository.DomicilioRepository;
import com.proyecto.ClinicaOdontologica.repository.PacienteRespository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DomicilioService {

   private static final Logger logger = LogManager.getLogger(OdontologoService.class);

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    public DomicilioService(PacienteRespository pacienteRespository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Set<Domicilio> listarDomicilios(){
        List<Domicilio> domicilios = domicilioRepository.findAll();
        Set<Domicilio> domicilio = new HashSet<>();
        for (Domicilio domi: domicilios ){
            domicilio.add(domi);
        }
        return domicilio;
    };

    public Optional<Domicilio> buscarDomicilio(Integer id) {
        Optional<Domicilio> domicilioEntity = domicilioRepository.findById(id);
        if (domicilioEntity.isPresent()){
            logger.info("Se encuentra el domicilio por id");
            ResponseEntity.ok(domicilioEntity);
        }
        return domicilioEntity;
    }

    public Domicilio crearDomicilio(Domicilio domicilioEntity){
        domicilioRepository.save(domicilioEntity);
        return ResponseEntity.ok(domicilioEntity).getBody();
    }

    public Domicilio modificarDomicilios(Integer id, Optional<Domicilio> domicilioEntity){;
         domicilioEntity = domicilioRepository.findById(id);
         Domicilio domicilio = domicilioEntity.get();

         if(domicilioEntity.isPresent()){

            domicilio.setCalle((domicilio.getCalle() != null ? domicilio.getCalle() : domicilioEntity.get().getCalle()));
            domicilio.setNumero((domicilio.getNumero() != null ? domicilio.getNumero() : domicilioEntity.get().getNumero()));
            domicilio.setLocalidad((domicilio.getLocalidad() != null ? domicilio.getLocalidad() : domicilioEntity.get().getLocalidad()));
            domicilio.setProvincia((domicilio.getProvincia() != null ? domicilio.getProvincia() : domicilioEntity.get().getProvincia()));

            domicilioRepository.save(domicilioEntity.get());
            logger.info("Se logra modificar  el domicilio encontrado");
        } else {
            logger.error("No se encontró el domicilio y no se modificó correctamente");
        }
        return domicilio;
    }

    public Domicilio eliminarDomicilio(Integer id) {
        Optional<Domicilio> domicilioEntity = domicilioRepository.findById(id);
        if (domicilioEntity.isPresent()){
            domicilioRepository.deleteById(id);
            logger.info("Se logra eliminar el domicilio");
        }
        return domicilioEntity.get();
    };

}

package com.proyecto.ClinicaOdontologica.service;

import com.proyecto.ClinicaOdontologica.dto.OdontologoDto;
import com.proyecto.ClinicaOdontologica.exception.BadRequestException;
import com.proyecto.ClinicaOdontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;


    @Test
    public void test1_agregar_odontologo() throws ResourceNotFoundException, BadRequestException {
        //Arrange
        OdontologoDto odontologoDto1 = new OdontologoDto("Francisco", "Gutierrez", 123);
        OdontologoDto odontologoDto2 = new OdontologoDto("Harold", "Zuniga", 456);
        OdontologoDto odontologoDto3 = new OdontologoDto("Ana", "Duque", 789);

        //Act
        odontologoService.crearOdontologo(odontologoDto1);
        odontologoService.crearOdontologo(odontologoDto2);
        odontologoService.crearOdontologo(odontologoDto3);

        //Assertions
        Assertions.assertEquals(3,odontologoService.listarOdontologos().size());
    }


    @Test
    public void test2_buscar_odontologo() throws ResourceNotFoundException, BadRequestException {
        //Arrange
        OdontologoDto odontologoDto1 = new OdontologoDto("Francisco", "Gutierrez", 123);
        OdontologoDto odontologoDto2 = new OdontologoDto("Harold", "Zuniga", 456);
        OdontologoDto odontologoDto3 = new OdontologoDto("Ana", "Duque", 789);

        //Act
        odontologoService.crearOdontologo(odontologoDto1);
        odontologoService.crearOdontologo(odontologoDto2);
        odontologoService.crearOdontologo(odontologoDto3);

        //Assertions
        Assertions.assertNotNull(odontologoService.buscarOdontologo(2));
    }

    @Test
    public void test3_modificar_odontologo() throws BadRequestException, ResourceNotFoundException {
        //Arrange
        OdontologoDto odontologoDto1 = new OdontologoDto("Francisco", "Gutierrez", 123);
        OdontologoDto odontologoDto2 = new OdontologoDto("Harold", "Zuniga", 456);
        OdontologoDto odontologoDto3 = new OdontologoDto("Ana", "Duque", 789);

        //Act
        odontologoService.crearOdontologo(odontologoDto1);
        odontologoService.crearOdontologo(odontologoDto2);
        odontologoService.crearOdontologo(odontologoDto3);


        OdontologoDto odontologoEncontrado =   odontologoService.buscarOdontologo(3);
        odontologoEncontrado.setApellido("NuevoApellido");
        odontologoService.modificarOdontologos(3, odontologoEncontrado);
        odontologoEncontrado =   odontologoService.buscarOdontologo(3);

        //Assertions
        Assertions.assertEquals("NuevoApellido",odontologoService.buscarOdontologo(3).getApellido());
    }


    @Test
    public void test4_eliminar_odontologo() throws ResourceNotFoundException, BadRequestException {
        //Arrange
        OdontologoDto odontologoDto1 = new OdontologoDto("Francisco", "Gutierrez", 123);
        OdontologoDto odontologoDto2 = new OdontologoDto("Harold", "Zuniga", 456);
        OdontologoDto odontologoDto3 = new OdontologoDto("Ana", "Duque", 789);

        //Act
        odontologoService.crearOdontologo(odontologoDto1);
        odontologoService.crearOdontologo(odontologoDto2);
        odontologoService.crearOdontologo(odontologoDto3);

        odontologoService.listarOdontologos();
        odontologoService.eliminarOdontologos(1);

        //Assertions
        Assertions.assertTrue(odontologoService.listarOdontologos().size() == 11);
    }

    @Test
    public void test5_listar_odontologos() throws ResourceNotFoundException, BadRequestException {
        //Arrange
        OdontologoDto odontologoDto1 = new OdontologoDto("Francisco", "Gutierrez", 123);
        OdontologoDto odontologoDto2 = new OdontologoDto("Harold", "Zuniga", 456);
        OdontologoDto odontologoDto3 = new OdontologoDto("Ana", "Duque", 789);

        //Act
        odontologoService.crearOdontologo(odontologoDto1);
        odontologoService.crearOdontologo(odontologoDto2);
        odontologoService.crearOdontologo(odontologoDto3);


        //Assertions
        Assertions.assertEquals(14, odontologoService.listarOdontologos().size());
    }
}
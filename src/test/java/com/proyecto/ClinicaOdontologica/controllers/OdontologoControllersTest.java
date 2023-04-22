package com.proyecto.ClinicaOdontologica.controllers;

import com.proyecto.ClinicaOdontologica.service.OdontologoService;
import com.proyecto.ClinicaOdontologica.dto.OdontologoDto;
import com.proyecto.ClinicaOdontologica.util.JsonUtil;
import org.apache.catalina.filters.ExpiresFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.MethodName.class)

class OdontologoControllersTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void Test_1_agregarUsuarios() throws Exception {
        //Arrange
        OdontologoDto odontologoDto = new OdontologoDto("Jose", "Garcia", 124);

        //Act
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(odontologoDto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        //Assertions
        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void test_2_listarOdontologos() throws Exception {

        //Arrange
        List<OdontologoDto> odontologoDtoList = new ArrayList<>();
        odontologoDtoList.add(new OdontologoDto("Maria", "Castillo", 345));
        odontologoDtoList.add(new OdontologoDto("Ana", "Ortiz", 456));
        odontologoDtoList.add(new OdontologoDto("Valeria", "Duque", 567));

        //Act
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(odontologoDtoList)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        //Assertions
        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }


    @Test
    void test_3_buscarOdontologo() throws Exception {
        //Arrange
        OdontologoDto odontologoDto = new OdontologoDto("Miguel", "Hernandez", 124);
        OdontologoDto result = odontologoService.crearOdontologo(odontologoDto);

        //Act

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isFound())
                        .andReturn();

        //Assertions
        Assertions.assertFalse((HttpStatus.FOUND).isError());

    }

    @Test
    void test_4_eliminarUsuarios() throws Exception {

        //Arrange
        OdontologoDto odontologoDto = new OdontologoDto("Juana", "Marin", 987);

        //Act
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        //Assertions
        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());


    }

}
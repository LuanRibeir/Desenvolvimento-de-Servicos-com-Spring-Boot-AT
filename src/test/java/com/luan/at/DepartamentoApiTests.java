package com.luan.at;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luan.at.model.Departamento;
import com.luan.at.service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.luan.at.controller.DepartamentoController;

@WebMvcTest(DepartamentoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DepartamentoApiTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Departamento departamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNome("Dummy");
        departamento.setLocal("Dummy");
    }

    @Test
    @DisplayName("Adiciona um Departamento no DB.")
    public void saveDepartamento() throws Exception {
        given(departamentoService.save(any(Departamento.class))).willReturn(departamento);

        mockMvc.perform(post("/api/public/departamento/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Dummy"))
                .andExpect(jsonPath("$.local").value("Dummy"));
    }

    @Test
    @DisplayName("Acha todos os Departamentos")
    public void FindAllDepartamentosTest() throws Exception {
        given(departamentoService.findAll()).willReturn(Arrays.asList(departamento));

        mockMvc.perform(get("/api/public/departamento/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Dummy"))
                .andExpect(jsonPath("$[0].local").value("Dummy"));
    }

    @Test
    @DisplayName("Acha um Departamento pelo ID.")
    public void FindDepartamentoByIdTest() throws Exception {
        given(departamentoService.findById(1L)).willReturn(Optional.of(departamento));

        mockMvc.perform(get("/api/public/departamento/1", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Dummy"))
                .andExpect(jsonPath("$.local").value("Dummy"));
    }

    @Test
    @DisplayName("Deleta um Departamento no DB.")
    public void deleteDepartamentoTest() throws Exception {
        mockMvc.perform(delete("/api/public/departamento/1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Atualiza um Departamento pelo ID")
    public void updateDepartamentoByIdTest() throws Exception {
        Departamento updatedDepartamento = new Departamento();
        updatedDepartamento.setId(1L);
        updatedDepartamento.setNome("New Dummy");
        updatedDepartamento.setLocal("New Dummy");

        given(departamentoService.updateById(anyLong(), any(Departamento.class)))
                .willReturn(Optional.of(updatedDepartamento));

        mockMvc.perform(put("/api/public/departamento/1", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDepartamento)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("New Dummy"))
                .andExpect(jsonPath("$.local").value("New Dummy"));
    }

}

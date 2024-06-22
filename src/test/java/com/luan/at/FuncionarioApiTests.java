package com.luan.at;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luan.at.model.Funcionario;
import com.luan.at.service.FuncionarioService;

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

import com.luan.at.controller.FuncionarioController;

@WebMvcTest(FuncionarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FuncionarioApiTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2024-06-18T01:15:32.739+00:00",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Date date = Date.from(offsetDateTime.toInstant());

        funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Dummy");
        funcionario.setEndereco("Dummy");
        funcionario.setTelefone("Dummy");
        funcionario.setEmail("Dummy");
        funcionario.setDataNascimento(date);
    }

    @Test
    @DisplayName("Adiciona um Funcionario no DB.")
    public void saveFuncionario() throws Exception {
        given(funcionarioService.save(any(Funcionario.class))).willReturn(funcionario);

        mockMvc.perform(post("/api/public/funcionario/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Dummy"))
                .andExpect(jsonPath("$.endereco").value("Dummy"))
                .andExpect(jsonPath("$.telefone").value("Dummy"))
                .andExpect(jsonPath("$.email").value("Dummy"))
                .andExpect(jsonPath("$.dataNascimento").value("2024-06-18T01:15:32.739+00:00"));
    }

    @Test
    @DisplayName("Acha todos os Funcionario")
    public void FindAllFuncionariosTest() throws Exception {
        given(funcionarioService.findAll()).willReturn(Arrays.asList(funcionario));

        mockMvc.perform(get("/api/public/funcionario/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Dummy"))
                .andExpect(jsonPath("$[0].endereco").value("Dummy"))
                .andExpect(jsonPath("$[0].telefone").value("Dummy"))
                .andExpect(jsonPath("$[0].email").value("Dummy"))
                .andExpect(jsonPath("$[0].dataNascimento").value("2024-06-18T01:15:32.739+00:00"));
    }

    @Test
    @DisplayName("Acha um Funcionario pelo ID.")
    public void FindFuncionarioByIdTest() throws Exception {
        given(funcionarioService.findById(1L)).willReturn(Optional.of(funcionario));

        mockMvc.perform(get("/api/public/funcionario/1", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Dummy"))
                .andExpect(jsonPath("$.endereco").value("Dummy"))
                .andExpect(jsonPath("$.telefone").value("Dummy"))
                .andExpect(jsonPath("$.email").value("Dummy"))
                .andExpect(jsonPath("$.dataNascimento").value("2024-06-18T01:15:32.739+00:00"));
    }

    @Test
    @DisplayName("Deleta um Funcionario no DB.")
    public void deleteFuncionarioTest() throws Exception {
        mockMvc.perform(delete("/api/public/funcionario/1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Atualiza um Funcionario pelo ID")
    public void updateFuncionarioByIdTest() throws Exception {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("1970-06-18T01:15:32.739+00:00",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Date date = Date.from(offsetDateTime.toInstant());

        Funcionario newFuncionario = new Funcionario();
        newFuncionario.setId(1L);
        newFuncionario.setNome("New Dummy");
        newFuncionario.setEndereco("New Dummy");
        newFuncionario.setTelefone("New Dummy");
        newFuncionario.setEmail("New Dummy");
        newFuncionario.setDataNascimento(date);

        given(funcionarioService.updateById(anyLong(), any(Funcionario.class)))
                .willReturn(Optional.of(newFuncionario));

        mockMvc.perform(put("/api/public/funcionario/1", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newFuncionario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("New Dummy"))
                .andExpect(jsonPath("$.endereco").value("New Dummy"))
                .andExpect(jsonPath("$.telefone").value("New Dummy"))
                .andExpect(jsonPath("$.email").value("New Dummy"))
                .andExpect(jsonPath("$.dataNascimento").value("1970-06-18T01:15:32.739+00:00"));
    }

}

package com.luan.at;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.luan.at.controller.FuncionarioController;
import com.luan.at.model.Funcionario;
import com.luan.at.service.FuncionarioService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
public class FuncionarioServiceTests {
    @Mock
    FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

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
    public void saveFuncionario() {
        when(funcionarioService.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario savedFuncionario = funcionarioService.save(funcionario);

        assertEquals(funcionario, savedFuncionario);
        verify(funcionarioService, times(1)).save(funcionario);
    }

    @Test
    @DisplayName("Deleta um Funcionario no DB.")
    public void deleteFuncionarioTest() {
        when(funcionarioService.deleteById(1L)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> deletedFuncionario = funcionarioService.deleteById(1L);

        assertEquals(Optional.of(funcionario), deletedFuncionario);
        verify(funcionarioService, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Acha um Funcionario pelo ID.")
    public void FindFuncionarioByIdTest() {
        when(funcionarioService.findById(1L)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> foundDepartamento = funcionarioService.findById(1L);

        assertEquals(Optional.of(funcionario), foundDepartamento);
        verify(funcionarioService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Acha todos os Funcionarios")
    public void FindAllFuncionariosTest() {
        List<Funcionario> funcionarios = Arrays.asList(funcionario);
        when(funcionarioService.findAll()).thenReturn(funcionarios);

        List<Funcionario> foundFuncionarios = funcionarioService.findAll();

        assertEquals(funcionarios, foundFuncionarios);
        verify(funcionarioService, times(1)).findAll();
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

        when(funcionarioService.updateById(eq(1L), any(Funcionario.class))).thenReturn(Optional.of(newFuncionario));

        Optional<Funcionario> updatedFuncionario = funcionarioService.updateById(1L, newFuncionario);

        assertEquals(Optional.of(newFuncionario), updatedFuncionario);
        assertNotEquals(funcionario.getNome(), updatedFuncionario.get().getNome());
        assertNotEquals(funcionario.getEndereco(), updatedFuncionario.get().getEndereco());
        assertNotEquals(funcionario.getTelefone(), updatedFuncionario.get().getTelefone());
        assertNotEquals(funcionario.getEmail(), updatedFuncionario.get().getEmail());
        assertNotEquals(funcionario.getDataNascimento(), updatedFuncionario.get().getDataNascimento());

        verify(funcionarioService, times(1)).updateById(eq(1L), eq(newFuncionario));
    }
}

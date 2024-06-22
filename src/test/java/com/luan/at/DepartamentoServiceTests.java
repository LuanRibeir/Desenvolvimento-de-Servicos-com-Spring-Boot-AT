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
import com.luan.at.controller.DepartamentoController;
import com.luan.at.model.Departamento;
import com.luan.at.service.DepartamentoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DepartamentoServiceTests {
    @Mock
    DepartamentoService departamentoService;

    @InjectMocks
    private DepartamentoController departamentoController;

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
    public void saveDepartamento() {
        when(departamentoService.save(any(Departamento.class))).thenReturn(departamento);

        Departamento savedDepartamento = departamentoService.save(departamento);

        assertEquals(departamento, savedDepartamento);
        verify(departamentoService, times(1)).save(departamento);
    }

    @Test
    @DisplayName("Deleta um Departamento no DB.")
    public void deleteDepartamentoTest() {
        when(departamentoService.deleteById(1L)).thenReturn(Optional.of(departamento));

        Optional<Departamento> deletedDepartamento = departamentoService.deleteById(1L);

        assertEquals(Optional.of(departamento), deletedDepartamento);
        verify(departamentoService, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Acha um Departamento pelo ID.")
    public void FindDepartamentoByIdTest() {
        when(departamentoService.findById(1L)).thenReturn(Optional.of(departamento));

        Optional<Departamento> foundDepartamento = departamentoService.findById(1L);

        assertEquals(Optional.of(departamento), foundDepartamento);
        verify(departamentoService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Acha todos os Departamentos")
    public void FindAllDepartamentosTest() {
        List<Departamento> departamentos = Arrays.asList(departamento);
        when(departamentoService.findAll()).thenReturn(departamentos);

        List<Departamento> foundDepartamentos = departamentoService.findAll();

        assertEquals(departamentos, foundDepartamentos);
        verify(departamentoService, times(1)).findAll();
    }

    @Test
    @DisplayName("Atualiza um Departamento pelo ID")
    public void updateDepartamentoByIdTest() throws Exception {
        Departamento newDepartamento = new Departamento();
        newDepartamento.setId(1L);
        newDepartamento.setNome("New Dummy");
        newDepartamento.setLocal("New Dummy");

        when(departamentoService.updateById(eq(1L), any(Departamento.class))).thenReturn(Optional.of(newDepartamento));

        Optional<Departamento> updatedDepartamento = departamentoService.updateById(1L, newDepartamento);

        assertEquals(Optional.of(newDepartamento), updatedDepartamento);
        assertNotEquals(departamento.getNome(), updatedDepartamento.get().getNome());
        assertNotEquals(departamento.getLocal(), updatedDepartamento.get().getLocal());

        verify(departamentoService, times(1)).updateById(eq(1L), eq(newDepartamento));
    }
}

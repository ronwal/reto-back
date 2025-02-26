package ec.reto.persona.test;

import ec.reto.persona.entity.Cliente;
import ec.reto.persona.repository.ClienteRepository;
import ec.reto.persona.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RetoPersonaTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Pérez");
        cliente.setClienteId("C123");
        cliente.setPassword("1234");
        cliente.setEstado(true);
    }

    @Test
    void obtenerClientes_DeberiaRetornarLista() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.obtenerClientes();
        assertEquals(1, clientes.size());
    }

    @Test
    void obtenerClientePorId_DeberiaRetornarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Cliente encontrado = clienteService.obtenerClientePorId(1L);
        assertNotNull(encontrado);
        assertEquals("Juan Pérez", encontrado.getNombre());
    }

    @Test
    void guardarCliente_DeberiaGuardarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente guardado = clienteService.guardarCliente(cliente);
        assertNotNull(guardado);
        assertEquals("C123", guardado.getClienteId());
    }

    @Test
    void eliminarCliente_DeberiaEliminarCliente() {
        doNothing().when(clienteRepository).deleteById(1L);
        clienteService.eliminarCliente(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}

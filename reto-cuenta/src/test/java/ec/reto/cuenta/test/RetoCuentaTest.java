package ec.reto.cuenta.test;

import ec.reto.cuenta.entity.Cuenta;
import ec.reto.cuenta.entity.Movimiento;
import ec.reto.cuenta.event.KafkaProducer;
import ec.reto.cuenta.repository.CuentaRepository;
import ec.reto.cuenta.repository.MovimientoRepository;
import ec.reto.cuenta.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RetoCuentaTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private MovimientoService movimientoService;

    private Cuenta cuenta;
    private Movimiento movimiento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cuenta = new Cuenta(1L, "123456", "Ahorro", 1000.0, true, 1L);
        movimiento = new Movimiento(1L, cuenta, LocalDateTime.now(), "Depósito", 500.0, 1500.0);
    }

    @Test
    void registrarMovimiento_DeberiaActualizarSaldo() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(movimientoRepository.save(any())).thenReturn(movimiento);

        // ✅ Simulamos que Kafka envía correctamente el mensaje
        doNothing().when(kafkaProducer).enviarEvento(anyString());

        Movimiento resultado = movimientoService.registrarMovimiento(1L, movimiento);

        assertNotNull(resultado);
        assertEquals(1500.0, resultado.getSaldo());

        // ✅ Verificamos que KafkaProducer fue llamado
        verify(kafkaProducer, times(1)).enviarEvento(anyString());
    }

    @Test
    void registrarMovimiento_DeberiaFallarPorSaldoInsuficiente() {
        movimiento.setValor(-1500.0); // Intentar retirar más de lo disponible
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            movimientoService.registrarMovimiento(1L, movimiento);
        });

        assertEquals("Saldo no disponible", exception.getMessage());
    }
}

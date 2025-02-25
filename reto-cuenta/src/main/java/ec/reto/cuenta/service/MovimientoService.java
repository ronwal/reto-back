package ec.reto.cuenta.service;

import ec.reto.cuenta.entity.Cuenta;
import ec.reto.cuenta.entity.Movimiento;
import ec.reto.cuenta.event.KafkaProducer;
import ec.reto.cuenta.repository.CuentaRepository;
import ec.reto.cuenta.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    private final KafkaProducer kafkaProducer;


    public List<Movimiento> obtenerMovimientos(Long cuentaId, LocalDateTime start, LocalDateTime end) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, start, end);
    }

    public Movimiento registrarMovimiento(Long cuentaId, Movimiento movimiento) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(cuentaId);
        if (cuentaOpt.isEmpty()) {
            throw new RuntimeException("Cuenta no encontrada");
        }

        Cuenta cuenta = cuentaOpt.get();
        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();

        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setSaldo(nuevoSaldo);
        Movimiento nuevoMovimiento = movimientoRepository.save(movimiento);

        // 📢 Enviar evento Kafka
        kafkaProducer.enviarEvento("Nuevo movimiento en cuenta: " + cuenta.getNumeroCuenta());

        return nuevoMovimiento;
    }
}

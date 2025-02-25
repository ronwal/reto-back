package ec.reto.cuenta.controller;

import ec.reto.cuenta.entity.Movimiento;
import ec.reto.cuenta.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping("/{cuentaId}")
    public List<Movimiento> obtenerMovimientos(
            @PathVariable Long cuentaId,
            @RequestParam("inicio") LocalDateTime inicio,
            @RequestParam("fin") LocalDateTime fin) {
        return movimientoService.obtenerMovimientos(cuentaId, inicio, fin);
    }

    @PostMapping("/{cuentaId}")
    public Movimiento registrarMovimiento(@PathVariable Long cuentaId, @RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(cuentaId, movimiento);
    }
}

package ec.reto.cuenta.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void enviarEvento(String mensaje) {
        kafkaTemplate.send("movimientos", mensaje);
    }
}

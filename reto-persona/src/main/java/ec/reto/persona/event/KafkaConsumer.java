package ec.reto.persona.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "movimientos", groupId = "grupo-clientes")
    public void consumirEvento(String mensaje) {
        System.out.println("📥 Evento recibido en ms-clientes: " + mensaje);
    }
}

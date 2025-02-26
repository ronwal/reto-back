package ec.reto.persona.test.containers;

import ec.reto.persona.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos García");
        cliente.setClienteId("C456");
        cliente.setPassword("abcd");
        cliente.setEstado(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);

        ResponseEntity<String> response = restTemplate.exchange("/clientes", HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

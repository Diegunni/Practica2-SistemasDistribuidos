package mx.cua.collector_service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/datos")
public class CollectorController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity<String> recibirDatos(@RequestBody Map<String, Object> data) {
        String storageUrl = "http://storage_service:5002/guardar";
        String alertUrl = "http://alert_service:5003/analizar";

        restTemplate.postForEntity(storageUrl, data, String.class);
        restTemplate.postForEntity(alertUrl, data, String.class);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

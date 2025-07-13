package mx.cua.sensor_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorController {

    @GetMapping("/")
    public String index() {
        return "Sensor activo";
    }
}

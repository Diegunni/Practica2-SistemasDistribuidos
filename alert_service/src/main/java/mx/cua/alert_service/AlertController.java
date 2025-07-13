package mx.cua.alert_service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analizar")
public class AlertController {

    @PostMapping
    public ResponseEntity<String> analizar(@RequestBody Map<String, Object> data) {
        String barrio = (String) data.get("barrio");
        double temperatura = Double.parseDouble(data.get("temperatura").toString());
        String calidad = (String) data.get("calidad_aire");

        if (temperatura > 35) {
            System.out.println("[ALERTA] " + barrio + " - Temperatura crítica: " + temperatura + "°C");
        }

        if ("Mala".equalsIgnoreCase(calidad)) {
            System.out.println("[ALERTA] " + barrio + " - Calidad de aire MALA");
        }

        return new ResponseEntity<>("Analizado", HttpStatus.OK);
    }
}

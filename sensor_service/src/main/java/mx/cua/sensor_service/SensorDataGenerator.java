package mx.cua.sensor_service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Component
public class SensorDataGenerator {

    private static final String[] BARRIOS = {"Palermo", "Recoleta", "Belgrano", "Caballito"};
    private static final String COLLECTOR_URL = "http://collector_service:5001/datos";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @PostConstruct
    public void startSendingData() {
        new Thread(() -> {
            while (true) {
                Map<String, Object> data = new HashMap<>();
                data.put("barrio", BARRIOS[random.nextInt(BARRIOS.length)]);
                data.put("temperatura", Math.round((20 + random.nextDouble() * 20) * 100.0) / 100.0);
                data.put("humedad", Math.round((30 + random.nextDouble() * 50) * 100.0) / 100.0);
                data.put("calidad_aire", getCalidadAire());
                data.put("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

                try {
                    restTemplate.postForEntity(COLLECTOR_URL, data, String.class);
                    System.out.println("Enviado: " + data);
                } catch (Exception e) {
                    System.out.println("Error al enviar datos: " + e.getMessage());
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    private String getCalidadAire() {
        String[] opciones = {"Buena", "Moderada", "Mala"};
        return opciones[random.nextInt(opciones.length)];
    }
}

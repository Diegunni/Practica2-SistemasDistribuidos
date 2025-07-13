package mx.cua.storage_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    private final Map<String, List<Map<String, Object>>> datosPorBarrio = new HashMap<>();

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Map<String, Object> data) {
        String barrio = (String) data.get("barrio");
        datosPorBarrio.putIfAbsent(barrio, new ArrayList<>());
        datosPorBarrio.get(barrio).add(data);
        return ResponseEntity.ok("Guardado");
    }

    @GetMapping("/datos")
    public ResponseEntity<List<Map<String, Object>>> consultar(@RequestParam String barrio) {
        List<Map<String, Object>> datos = datosPorBarrio.getOrDefault(barrio, new ArrayList<>());
        return ResponseEntity.ok(datos);
    }
}

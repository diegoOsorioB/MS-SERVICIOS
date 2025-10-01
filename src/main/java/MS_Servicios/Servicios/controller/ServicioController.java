package MS_Servicios.Servicios.controller;

import MS_Servicios.Servicios.dtos.ServicioResponse;
import MS_Servicios.Servicios.entity.Servicio;
import MS_Servicios.Servicios.services.ServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Servicio>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Servicio> crear(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(service.crear(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizar(@PathVariable int id, @RequestBody Servicio servicio) {
        return ResponseEntity.ok(service.actualizar(id, servicio));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<ServicioResponse> getServicioCompleto(@PathVariable int id) {
        return ResponseEntity.ok(service.getServicioCompleto(id));
    }
}

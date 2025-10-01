package MS_Servicios.Servicios.controller;

import MS_Servicios.Servicios.entity.Categoria;
import MS_Servicios.Servicios.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Categoria> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return repository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable int id, @RequestBody Categoria categoriaDetails) {
        return repository.findById(id).map(categoria -> {
            categoria.setNombre(categoriaDetails.getNombre());
            categoria.setStatus(categoriaDetails.getStatus());
            return repository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repository.deleteById(id);
    }
}

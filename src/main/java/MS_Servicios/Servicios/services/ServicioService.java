package MS_Servicios.Servicios.services;

import MS_Servicios.Servicios.exception.Excepcion;
import org.springframework.dao.DataAccessException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import MS_Servicios.Servicios.dtos.CategoriaDTO;
import MS_Servicios.Servicios.dtos.InventarioDTO;
import MS_Servicios.Servicios.dtos.ServicioResponse;
import MS_Servicios.Servicios.entity.Servicio;
import MS_Servicios.Servicios.repository.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServicioService {

    private ServicioRepository repository;
    private RestTemplate restTemplate;

    public ServicioService(ServicioRepository servicioRepository, RestTemplate restTemplate)
    {
        this.repository = servicioRepository;
        this.restTemplate = restTemplate;
    }

    public List<Servicio> getAll() {
        try {
            return repository.findAll();
        }catch (Exception ex)
        {
            throw new Excepcion.DataBaseException("Ocurrio un error al consultar la base"+ ex.getMessage());
        }
    }



    public Servicio getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new Excepcion.ServicioNotFoundExcepcion(id));
    }


    public Servicio crear(Servicio servicio) {
        try {
            return repository.save(servicio);
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    public Servicio actualizar(int id, Servicio servicioDetails) {
        return repository.findById(id).map(servicio -> {
            servicio.setNombre_servicio(servicioDetails.getNombre_servicio());
            servicio.setPrecio_unitario(servicioDetails.getPrecio_unitario());
            servicio.setDescripcion(servicioDetails.getDescripcion());
            servicio.setId_item(servicioDetails.getId_item());
            servicio.setCategoria(servicioDetails.getCategoria());
            return repository.save(servicio);
        }).orElseThrow(() -> new Excepcion.ServicioNotFoundExcepcion(id));
    }

    public void eliminar(int id) {
        if (!repository.existsById(id)) {
            throw  new Excepcion.ServicioNotFoundExcepcion(id);

        }
        try {
            repository.deleteById(id);
        }catch (Exception ex){
            throw  new Excepcion.DataBaseException("Error al eliminar el servicio "+ex.getMessage());
        }
    }


    public ServicioResponse getServicioCompleto(int id) {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new Excepcion.ServicioNotFoundExcepcion(id));


        InventarioDTO inventario = restTemplate.getForObject(
                "http://localhost:8085/api/inventarios/" + servicio.getId_item(),
                InventarioDTO.class
        );


        ServicioResponse response = new ServicioResponse();
        response.setId_servicio(servicio.getId_servicio());
        response.setNombre_servicio(servicio.getNombre_servicio());
        response.setPrecio_unitario(servicio.getPrecio_unitario());
        response.setDescripcion(servicio.getDescripcion());


        response.setCategoria(new CategoriaDTO() {{
            setId_categoria(servicio.getCategoria().getId_categoria());
            setNombre(servicio.getCategoria().getNombre());
            setStatus(servicio.getCategoria().getStatus());
        }});

        response.setItem(inventario);

       try{
           return response;
       }catch (DataAccessException ex){
           throw new  Excepcion.DataBaseException("Ocurrio un error al obtener los datos "+ex.getMessage());
       }
    }
}

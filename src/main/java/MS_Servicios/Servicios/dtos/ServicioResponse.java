package MS_Servicios.Servicios.dtos;

import lombok.Data;

@Data
public class ServicioResponse {
    private int id_servicio;
    private String nombre_servicio;
    private double precio_unitario;
    private String descripcion;
    private CategoriaDTO categoria;
    private InventarioDTO item;

}

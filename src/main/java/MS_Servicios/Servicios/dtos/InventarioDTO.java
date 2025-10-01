package MS_Servicios.Servicios.dtos;


import lombok.Data;

@Data
public class InventarioDTO {
    public int id_item;
    public String nombre_item;
    public int cantidad_total;
    public int cantidad_disponible;
    public String estado_item;

}

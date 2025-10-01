package MS_Servicios.Servicios.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_servicio;
    public String nombre_servicio;
    @ManyToOne
    @JoinColumn(name = "id_categoria",referencedColumnName = "id_categoria")
    public Categoria categoria;
    public double precio_unitario;
    public int id_item;
    public String descripcion;
}

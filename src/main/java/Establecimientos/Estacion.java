package Establecimientos;

import Entidades.LineaDeTransporte;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Persistencia.EntidadPersistente;
import Servicios.Servicio;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Estaciones")
public class Estacion extends EntidadPersistente {

  @Column
  private String nombre;
  @Transient
  private Ubicacion ubicacion;
  @Transient
  private List<Servicio> servicios;
  @Transient
  private LineaDeTransporte lineaDeTransporte;

  public Estacion(){}
  public Estacion(String nombre, Ubicacion ubicacion, LineaDeTransporte lineaDeTransporte) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.lineaDeTransporte = lineaDeTransporte;
  }


  public String getNombre() {
    return nombre;
  }
}

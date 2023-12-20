package Modelo.Establecimientos;

import Modelo.Entidades.LineaDeTransporte;
import Modelo.Localizaciones.Ubicacion;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Estacion")
public class Estacion extends Establecimiento {
  @Transient
  private LineaDeTransporte lineaDeTransporte;

  public Estacion(){}
  public Estacion(String nombre, Ubicacion ubicacion, LineaDeTransporte lineaDeTransporte) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.lineaDeTransporte = lineaDeTransporte;
  }
}

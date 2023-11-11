package Modelo.Entidades;

import Modelo.Establecimientos.Estacion;
import Modelo.Localizaciones.Ubicacion;
import Modelo.Personas.Interesado;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LineaDeTransporte")
public class LineaDeTransporte extends Entidad {

  @OneToMany
  @JoinColumn(name = "id_LineaDeTransporte")
  List<Estacion> estaciones;
  @Enumerated
  MedioDeTransporte medioTransporte;


  public LineaDeTransporte(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados) {
    super(nombre, ubicacion, interesados);
  }
  public LineaDeTransporte(){}


}

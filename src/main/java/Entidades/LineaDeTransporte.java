package Entidades;

import Personas.Interesado;
import Personas.Usuario;
import Establecimientos.Estacion;
import Localizaciones.Ubicacion;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LineaDeTransporte")
public class LineaDeTransporte extends Entidad {
  String nombre;
   Estacion origen;
   Estacion destino;
   Ubicacion ubicacion;
   List<Estacion> estaciones;
   MedioDeTransporte medioTransporte;


  public LineaDeTransporte(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados) {
    super(nombre, ubicacion, interesados);
  }


}

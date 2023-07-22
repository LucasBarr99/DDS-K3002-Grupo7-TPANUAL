package Establecimientos;

import Personas.Interesado;
import Personas.Usuario;
import Localizaciones.Ubicacion;
import Servicios.Servicio;

import java.util.List;

public abstract class Establecimiento {
  public String nombre;
  public Ubicacion ubicacion;
  public List<Servicio> servicios;
  public List<Interesado> interesados;

  public void notificarInteresados(){
    interesados.forEach(interesado -> interesado.notificar());
  }
}

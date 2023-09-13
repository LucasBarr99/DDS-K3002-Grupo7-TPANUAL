package Entidades;

import Personas.Interesado;
import Personas.Usuario;
import Establecimientos.Sucursal;
import Localizaciones.Ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Organizacion")
public class Organizacion extends Entidad {

  @OneToMany
  @JoinColumn(name = "id_organizacion")
  List<Sucursal> sucursales;

  public Organizacion(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados) {
    super(nombre, ubicacion, interesados);
  }

  public Organizacion() {

  }
}

import Personas.Usuario;
import Entidades.Entidad;

import java.util.List;

public class OrganismoDeControl {
  String nombre;
  Usuario usuario;
  List<Entidad> entidades;
  Usuario usuarioDesignado;

  public OrganismoDeControl(Usuario usuario, List<Entidad> entidades, Usuario usuarioDesignado) {
    this.usuario = usuario;
    this.entidades = entidades;
    this.usuarioDesignado = usuarioDesignado;
  }
}

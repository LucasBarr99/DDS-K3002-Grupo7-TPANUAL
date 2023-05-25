import Comunidades.Usuario;
import Entidades.LineaDeTransporte;
import Servicios.Servicio;

import java.util.List;

public class OrganismoDeControl {
  String nombre;
  Usuario usuario;
  List<LineaDeTransporte> servicios;
  Usuario usuarioDesignado;

  public OrganismoDeControl(Usuario usuario, List<LineaDeTransporte> servicios, Usuario usuarioDesignado) {
    this.usuario = usuario;
    this.servicios = servicios;
    this.usuarioDesignado = usuarioDesignado;
  }
}

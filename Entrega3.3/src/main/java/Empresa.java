import Personas.Usuario;
import Entidades.Entidad;

import java.util.List;

public class Empresa {
  String nombre;
  Usuario usuario;
  List<Entidad> entidades;
  Usuario usuarioDesginado;

  public Empresa(Usuario usuario, List<Entidad> entidades, Usuario usuarioDesginado) {
    this.usuario = usuario;
    this.entidades = entidades;
    this.usuarioDesginado = usuarioDesginado;
  }

}

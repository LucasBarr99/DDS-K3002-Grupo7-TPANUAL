import Comunidades.Usuario;
import Entidades.LineaDeTransporte;

import java.util.List;

public class Empresa {
  String nombre;
  Usuario usuario;
  List<LineaDeTransporte> serviciosPublicos;
  Usuario usuarioDesginado;

  public Empresa(Usuario usuario, List<LineaDeTransporte> serviciosPublicos, Usuario usuarioDesginado) {
    this.usuario = usuario;
    this.serviciosPublicos = serviciosPublicos;
    this.usuarioDesginado = usuarioDesginado;
  }
}

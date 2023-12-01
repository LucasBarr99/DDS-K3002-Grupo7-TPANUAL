package Modelo.Personas;

import Modelo.Comunidades.Miembro;
import Modelo.Excepciones.ContraseñaInvalidaException;
import Persistencia.EntidadPersistente;
import Modelo.Validadores.ValidadorContrasenias;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario extends EntidadPersistente {

  private String nombre;
  private String contrasenia;

  @OneToMany
  @JoinColumn(name="idUsuario")
  private List<Miembro> membresias = new ArrayList<Miembro>();



  private  TipoUsuario tipo;


  public Usuario(String nombre, String contrasenia,TipoUsuario tipo) {
    this.nombre = nombre;
    this.tipo = tipo;
    ValidadorContrasenias validador = new ValidadorContrasenias();
    try {
      validador.validarContrasenia(contrasenia);
      this.contrasenia = contrasenia;
    } catch (
        ContraseñaInvalidaException s) {
      // Enviar a componente que se encargue de mostarlo en pantalla;
      System.out.println("CONTRASEÑA INVALIDA EXCEPTION: "+s);
    }

  }

  public Usuario() {

  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getContrasenia() {
    return this.contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  public TipoUsuario getTipo() {
    return tipo;
  }

  public void setTipo(TipoUsuario tipo) {
    this.tipo = tipo;
  }

  public void agregarMembresia(Miembro miembro1) {
    this.membresias.add(miembro1);
  }

  public void setMembresias(List<Miembro> membresias) {
    this.membresias = membresias;
  }
}

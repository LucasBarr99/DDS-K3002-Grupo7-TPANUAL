package Modelo.Personas;

import Modelo.Excepciones.ContraseñaInvalidaException;
import Persistencia.EntidadPersistente;
import Modelo.Validadores.ValidadorContrasenias;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario extends EntidadPersistente {

  private String nombre;
  private String contrasenia;


  public Usuario(String nombre, String contrasenia) {
    this.nombre = nombre;
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
}

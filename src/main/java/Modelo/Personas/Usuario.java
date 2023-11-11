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
    } catch (
        ContraseñaInvalidaException s) {
      // Enviar a componente que se encargue de mostarlo en pantalla;
    }

  }

  public Usuario() {

  }
}

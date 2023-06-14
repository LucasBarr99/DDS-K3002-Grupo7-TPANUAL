package Comunidades;

import Excepciones.ContraseñaInvalidaException;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Validadores.ValidadorContrasenias;

public class Usuario {
  String nombre;
  String contraseña;

  Ubicacion ubicacion;

  public Usuario(String nombre, String contraseña, Ubicacion ubicacion) {
    this.nombre = nombre;
    ValidadorContrasenias validador = new ValidadorContrasenias();
    try{
      validador.validarContrasenia(contraseña);
    }
    catch (ContraseñaInvalidaException s){
        // Enviar a componente que se encargue de mostarlo en pantalla;
    }

    this.contraseña = contraseña;
    this.ubicacion = ubicacion;
  }

  public void notificarIncidente(Incidente incidente){

  }
}

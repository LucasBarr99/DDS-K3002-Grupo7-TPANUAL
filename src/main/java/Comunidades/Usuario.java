package Comunidades;

import Excepciones.ContraseñaInvalidaException;
import Localizaciones.Localizacion;
import Validadores.ValidadorContrasenias;

import java.io.IOException;

public class Usuario {
  String nombre;
  String contraseña;

  Localizacion localizacion;

  public Usuario(String nombre, String contraseña, Localizacion localizacion) {
    this.nombre = nombre;
    ValidadorContrasenias validador = new ValidadorContrasenias();
    try{
      validador.validarContrasenia(contraseña);
    }
    catch (ContraseñaInvalidaException s){
        // Enviar a componente que se encargue de mostarlo en pantalla;
    }

    this.contraseña = contraseña;
    this.localizacion = localizacion;
  }
}

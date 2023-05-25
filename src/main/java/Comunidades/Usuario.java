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
    try{
      ValidadorContrasenias.getInstance().validarContrasenia(contraseña);
    }
    catch (ContraseñaInvalidaException s){
        System.out.println("La Contrseña no cumple con los requisitos de seguridad");
    }

    this.contraseña = contraseña;
    this.localizacion = localizacion;
  }
}

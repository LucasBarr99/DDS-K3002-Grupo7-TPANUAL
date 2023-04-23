import java.io.IOException;

public class Usuario {
  String nombre;
  String contraseña;

  public Usuario(String nombre, String contraseña) {
    this.nombre = nombre;
    try{
      ValidadorContrasenias.getInstance().validarContrasenia(contraseña);
    }
    catch (ContraseñaInvalidaException s){
        System.out.println("La Contrseña no cumple con los requisitos de seguridad");
    }

    this.contraseña = contraseña;
  }
}

package Modelo.Excepciones;

public class ContraseñaInvalidaException extends RuntimeException{

  public ContraseñaInvalidaException(String message) {
    super(message);
  }
}

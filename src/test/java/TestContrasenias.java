
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestContrasenias {
  ValidadorContrasenias validadorContrasenias = ValidadorContrasenias.getInstance();


 @Test
  void contrseniaNoCumpleConMinimaCantidadDeCaracteres(){
   ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("d"));
   assertEquals(exception.getMessage(),"La contraseña no es valida");
   List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("d");
   assertTrue(mensajes.contains("La contraseña debe contener de 8 a 64 caracteres."));

 }
 @Test
 void contraseniaSeEncuentraEnElTop10000DeContraseñasComunes(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("dragon"));
  assertEquals(exception.getMessage(),"La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("dragon");
  assertTrue(mensajes.contains("La contraseña no debe estar incluida en el top 10.000 de contraseñas mas frecuentes."));

 }


}

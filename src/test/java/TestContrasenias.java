
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


}

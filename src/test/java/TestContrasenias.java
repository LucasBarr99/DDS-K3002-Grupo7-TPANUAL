import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;

public class TestContrasenias {
  ValidadorContrasenias validadorContrasenias = ValidadorContrasenias.getInstance();


 @Test
  void contrseniaNoCumpleConMinimaCantidadDeCaracteres(){
   Assertions.assertThrows(ContraseñaInvalidaException.class,()-> new Usuario("pepito", "d"));
 }


}

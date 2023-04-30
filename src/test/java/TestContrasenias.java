
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


 @Test
 void contraseniaEstaVacia() {
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class, () -> validadorContrasenias.validarContrasenia(""));
  assertEquals(exception.getMessage(), "La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("");
  assertTrue(mensajes.contains("La contraseña no debe estar vacia."));

 }


 @Test
 void contraseniaTieneCaracteresRepetidos(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("ddda"));
  assertEquals(exception.getMessage(),"La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("ddda");
  assertTrue(mensajes.contains("La contraseña no debe tener caracteres seguidos repetidos."));

 }

 @Test
 void contraseniaTieneCaracteresConsecutivos(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("abcd1267"));
  assertEquals(exception.getMessage(),"La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("abcd1267");
  assertTrue(mensajes.contains("La contraseña no debe tener letras consecutivas."));

 }

 @Test
 void contraseniaTieneTodosLosCaracteresEnMayuscula(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("DRAGON"));
  assertEquals(exception.getMessage(),"La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("DRAGON");
  assertTrue(mensajes.contains("La contraseña debe contener al menos una letra minuscula."));

 }

 @Test
 void contraseniaTieneTodosLosCaracteresEnMinuscula(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("dragonaerty"));
  assertEquals(exception.getMessage(),"La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("dragonaerty");
  assertTrue(mensajes.contains("La contraseña debe contener al menos una letra mayuscula."));

 }

 @Test
 void contraseniaCumpleConTodosLosRequisitos(){
  Assertions.assertDoesNotThrow(()->validadorContrasenias.validarContrasenia("Strongpassword1"));
 }


 @Test
 void contraseniaTieneNumerosConsecutivos() {
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class, () -> validadorContrasenias.validarContrasenia("Dragon234"));
  assertEquals(exception.getMessage(), "La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("Dragon234");
  assertTrue(mensajes.contains("La contraseña no debe tener numeros consecutivos."));
 }

 @Test
 void codcontraseniaTieneCaracteresUnicode() {
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class, () -> validadorContrasenias.validarContrasenia("Dragon▼£"));
  assertEquals(exception.getMessage(), "La contraseña no es valida");
  List<String> mensajes = validadorContrasenias.mostrarMsjValidadorLista("Dragon▼£");
  assertTrue(mensajes.contains("La contraseña no debe contener caracteres unicode."));
 }

}

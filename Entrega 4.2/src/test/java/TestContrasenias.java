
import Excepciones.ContraseñaInvalidaException;
import Validadores.ValidacionesContrasenia;
import Validadores.ValidadorContrasenias;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestContrasenias {
  ValidadorContrasenias validadorContrasenias = new ValidadorContrasenias();


 @Test
  void contraseniaNoCumpleConMinimaCantidadDeCaracteres(){
   ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("d"));
   assertEquals("La contraseña no es valida",exception.getMessage());
   List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("d");
   assertTrue(mensajes.contains(ValidacionesContrasenia.NO_CUMPLE_LONGITUD));

 }
 @Test
 void contraseniaSeEncuentraEnElTop10000DeContraseñasComunes(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("dragon"));
  assertEquals("La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("dragon");
  assertTrue(mensajes.contains(ValidacionesContrasenia.CONTRASENIA_EN_TOP_10000));


 }


 @Test
 void contraseniaEstaVacia() {
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class, () -> validadorContrasenias.validarContrasenia(""));
  assertEquals( "La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("");
  assertTrue(mensajes.contains(ValidacionesContrasenia.CONTRASENIA_VACIA));

 }


 @Test
 void contraseniaTieneCaracteresRepetidos(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("ddda"));
  assertEquals("La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("ddda");
  assertTrue(mensajes.contains(ValidacionesContrasenia.CARACTERES_SEGUIDOS_REPETIDOS));

 }

 @Test
 void contraseniaTieneCaracteresConsecutivos(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("abcd1267"));
  assertEquals("La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("abcd1267");
  assertTrue(mensajes.contains(ValidacionesContrasenia.CARACTERES_CONSECUTIVOS));

 }

 @Test
 void contraseniaTieneTodosLosCaracteresEnMayuscula(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("DRAGON"));
  assertEquals("La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("DRAGON");
  assertTrue(mensajes.contains(ValidacionesContrasenia.SIN_LETRA_MINUSCULA));

 }

 @Test
 void contraseniaTieneTodosLosCaracteresEnMinuscula(){
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class,()-> validadorContrasenias.validarContrasenia("dragonaerty"));
  assertEquals("La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("dragonaerty");
  assertTrue(mensajes.contains(ValidacionesContrasenia.SIN_LETRA_MAYUSCULA));

 }

 @Test
 void contraseniaCumpleConTodosLosRequisitos(){
  Assertions.assertDoesNotThrow(()->validadorContrasenias.validarContrasenia("Strongpassword1"));
 }


 @Test
 void contraseniaTieneNumerosConsecutivos() {
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class, () -> validadorContrasenias.validarContrasenia("Dragon234"));
  assertEquals("La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("Dragon234");
  assertTrue(mensajes.contains(ValidacionesContrasenia.NUMEROS_CONSECUTIVOS));
 }

 @Test
 void codcontraseniaTieneCaracteresUnicode() {
  ContraseñaInvalidaException exception = assertThrows(ContraseñaInvalidaException.class, () -> validadorContrasenias.validarContrasenia("Dragon▼£"));
  assertEquals( "La contraseña no es valida",exception.getMessage());
  List<ValidacionesContrasenia> mensajes = validadorContrasenias.cargarValidaciones("Dragon▼£");
  assertTrue(mensajes.contains(ValidacionesContrasenia.TIENE_CARACTERES_UNICODE));
 }

}

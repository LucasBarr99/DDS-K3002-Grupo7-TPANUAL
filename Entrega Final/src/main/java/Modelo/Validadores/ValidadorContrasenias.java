package Modelo.Validadores;

import Modelo.Excepciones.ContraseñaInvalidaException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorContrasenias {

  public ValidadorContrasenias() {
  }

  public List<ValidacionesContrasenia> cargarValidaciones(String contrasenia)
  {
    Boolean[] lista = listaDeValidaciones(contrasenia);
    List<ValidacionesContrasenia> validaciones = new ArrayList<>();

    for (int i = 0; i < Arrays.stream(lista).count(); i++)
    {
      if (!lista[i])
      {
        switch (i)
        {
          case 0:
            validaciones.add(ValidacionesContrasenia.CONTRASENIA_VACIA);
            break;
          case 1:
            validaciones.add(ValidacionesContrasenia.TIENE_CARACTERES_UNICODE);
            break;
          case 2:
            validaciones.add(ValidacionesContrasenia.CONTRASENIA_EN_TOP_10000);
            break;
          case 3:
            validaciones.add(ValidacionesContrasenia.SIN_LETRA_MINUSCULA);
            break;
          case 4:
            validaciones.add(ValidacionesContrasenia.SIN_LETRA_MAYUSCULA);
            break;
          case 5:
            validaciones.add(ValidacionesContrasenia.SIN_NUMERO);
            break;
          case 6:
            validaciones.add(ValidacionesContrasenia.NO_CUMPLE_LONGITUD);
            break;
          case 7:
            validaciones.add(ValidacionesContrasenia.NUMEROS_CONSECUTIVOS);
            break;
          case 8:
            validaciones.add(ValidacionesContrasenia.CARACTERES_SEGUIDOS_REPETIDOS);
            break;
          case 9:
            validaciones.add(ValidacionesContrasenia.CARACTERES_CONSECUTIVOS);
            break;
        }
      }
    }
    return validaciones;
  }

  public void validarContrasenia(String contrasenia) throws ContraseñaInvalidaException
  {
    Boolean[] lista = listaDeValidaciones(contrasenia);
    if(!Arrays.stream(lista).allMatch(aBoolean -> aBoolean == true)){
     throw new ContraseñaInvalidaException("La contraseña no es valida");
    }
  }

  private Boolean[] listaDeValidaciones(String contrasenia){
    Boolean[] listaBool = new Boolean[10];

    listaBool[0] = !(contrasenia.isEmpty() || contrasenia == null);

    listaBool[1] = !EsUnicode(contrasenia);

    listaBool[2] = !EstaEnElTop10000(contrasenia);
    //listaBool[2] = true;


    Pattern paternMinusculas = Pattern.compile("[a-z]+", Pattern.UNICODE_CASE);
    Pattern paternMayusculas = Pattern.compile("[A-Z]+", Pattern.UNICODE_CASE);
    Pattern paternNumeros = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
    Pattern paternCantidad = Pattern.compile(".{8,64}", Pattern.CASE_INSENSITIVE);

    Matcher matcherMinusculas = paternMinusculas.matcher(contrasenia);
    Matcher matcherMayuscula = paternMayusculas.matcher(contrasenia);
    Matcher matcherNumeros = paternNumeros.matcher(contrasenia);
    Matcher matcherCantidad = paternCantidad.matcher(contrasenia);


    listaBool[3] = matcherMinusculas.find();

    listaBool[4] = matcherMayuscula.find();

    listaBool[5] = matcherNumeros.find();

    listaBool[6] = matcherCantidad.find();

    listaBool[7] = !TieneNumerosConsecutivos(contrasenia);

    listaBool[8] = !RepiteCaracteres(contrasenia);

    listaBool[9] = !TieneLetrasConsecutivas(contrasenia);

    return listaBool;
  }


  private boolean EstaEnElTop10000(String unString)
  {
    Path myPath = Paths.get("src/main/ListaContraseñas/top10000contraseñas");
    try{
      List < String > lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);
      return lines.stream().anyMatch(s -> s.equals(unString));
    }
    catch (IOException e){
      System.out.println(" Error al leer top 10000 de contraseñas debiles");
      return true;
    }

  }

  private boolean TieneNumerosConsecutivos(String otroString)
  {
    List<String> listaConsecutivos = Arrays.asList("012", "123", "234", "345", "456", "567", "678", "789");
    return listaConsecutivos.stream().anyMatch(otroString::contains);
  }

  private boolean TieneLetrasConsecutivas(String otroString)
  {
    char[] unString = otroString.toCharArray();

    for (int i = 0; i < unString.length - 2; i++)
    {
      if (((int)unString[i+1] == ((int)unString[i] +1)) && (((int)unString[i + 1] +1) == ((int)unString[i + 2])))
      {
        return true;
      }
    }
    return false;
  }

  private boolean RepiteCaracteres(String otroString)
  {
    char[] unString = otroString.toCharArray();

    for (int i = 0; i < unString.length -2; i++)
    {
      if (((int)unString[i] == (int)unString[i + 1]) && ((int)unString[i + 1] == (int)unString[i + 2]))
      {
        return true;
      }
    }

    return false;

  }

  private boolean EsUnicode(String entrada)
  {
    byte[] bytesASCII = entrada.getBytes(StandardCharsets.US_ASCII);
    byte[] bytesUnicode = entrada.getBytes(StandardCharsets.UTF_8);

    return bytesASCII.length != bytesUnicode.length;
  }

}
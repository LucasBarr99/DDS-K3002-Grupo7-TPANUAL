
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
  private static ValidadorContrasenias instance = null;

  public static ValidadorContrasenias getInstance()
  {
    if (instance == null)
    {
      instance = new ValidadorContrasenias();
    }
    return instance;
  }

  public void mostrarMsjValidador(String contrasenia) throws IOException {
    Boolean [] lista = listaDeValidaciones(contrasenia);

    for (int i = 0; i<lista.length; i++)
    {
      if (!lista[i])
      {
        switch (i)
        {
          case 0:
            System.out.println(" (X) La contraseña no debe estar vacia. \n");
            break;
          case 1:
            System.out.println(" (X) La contraseña no debe contener caracteres unicode. \n");
            break;
          case 2:
            System.out.println(" (X) La contraseña no debe estar incluida en el top 10.000 de contraseñas mas frecuentes. \n");
            break;
          case 3:
            System.out.println(" (X) La contraseña debe contener al menos una letra minuscula. \n");
            break;
          case 4:
            System.out.println(" (X) La contraseña debe contener al menos una letra mayuscula. \n");
            break;
          case 5:
            System.out.println(" (X) La contraseña debe contener al menos un numero. \n");
            break;
          case 6:
            System.out.println(" (X) La contraseña debe contener de 8 a 64 caracteres. \n");
            break;
          case 7:
            System.out.println(" (X) La contraseña no debe tener numeros consecutivos \n");
            break;
          case 8:
            System.out.println(" (X) La contraseña no debe tener caracteres seguidos repetidos \n");
            break;
          case 9:
            System.out.println(" (X) La contraseña no debe tener letras consecutivas \n");
            break;
        }
      }
    }
  }

  public List<String> mostrarMsjValidadorLista(String contrasenia)
  {
    Boolean[] lista = listaDeValidaciones(contrasenia);
    List<String> msj = new ArrayList<>();

    for (int i = 0; i < Arrays.stream(lista).count(); i++)
    {
      if (!lista[i])
      {
        switch (i)
        {
          case 0:
            msj.add("La contraseña no debe estar vacia.");
            break;
          case 1:
            msj.add("La contraseña no debe contener caracteres unicode.");
            break;
          case 2:
            msj.add("La contraseña no debe estar incluida en el top 10.000 de contraseñas mas frecuentes.");
            break;
          case 3:
            msj.add("La contraseña debe contener al menos una letra minuscula.");
            break;
          case 4:
            msj.add("La contraseña debe contener al menos una letra mayuscula.");
            break;
          case 5:
            msj.add("La contraseña debe contener al menos un numero.");
            break;
          case 6:
            msj.add("La contraseña debe contener de 8 a 64 caracteres.");
            break;
          case 7:
            msj.add("La contraseña no debe tener numeros consecutivos.");
            break;
          case 8:
            msj.add("La contraseña no debe tener caracteres seguidos repetidos." );
            break;
          case 9:
            msj.add("La contraseña no debe tener letras consecutivas.");
            break;
        }
      }
    }
    return msj;
  }

  public void validarContrasenia(String contrasenia) throws ContraseñaInvalidaException
  {
    Boolean[] lista = listaDeValidaciones(contrasenia);
    if(Arrays.stream(lista).allMatch(aBoolean -> !aBoolean)){
     throw new ContraseñaInvalidaException("La contraseña no es valida");
    }
  }

  private Boolean[] listaDeValidaciones(String contrasenia){
    Boolean[] listaBool = new Boolean[10];

    listaBool[0] = !(contrasenia.isEmpty() || contrasenia == null);

    listaBool[1] = !EsUnicode(contrasenia);

    listaBool[2] = !EstaEnElTop10000(contrasenia);
    //listaBool[2] = true;


    Pattern paternMinisculas = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
    Pattern paternMayusculas = Pattern.compile("[A-Z]+", Pattern.CASE_INSENSITIVE);
    Pattern paternNumeros = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
    Pattern paternCantidad = Pattern.compile(".{8,64}", Pattern.CASE_INSENSITIVE);

    Matcher matcherMinisculas = paternMinisculas.matcher(contrasenia);
    Matcher matcherMayuscula = paternMayusculas.matcher(contrasenia);
    Matcher matcherNumeros = paternNumeros.matcher(contrasenia);
    Matcher matcherCantidad = paternCantidad.matcher(contrasenia);


    listaBool[3] = matcherMinisculas.find();

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
    Path myPath = Paths.get("src/main/ListaContraseñas/top10000contraseñas.txt");
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
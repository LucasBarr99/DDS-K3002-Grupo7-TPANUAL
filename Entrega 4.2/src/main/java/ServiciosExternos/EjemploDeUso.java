package ServiciosExternos;

import ServiciosExternos.DatosAPI.Departamento;
import ServiciosExternos.DatosAPI.Municipio;
import ServiciosExternos.DatosAPI.Provincia;

import java.io.IOException;

public class EjemploDeUso {



  public static void main(String[] args) throws IOException {
      ServicioUbicacion servicio = ServicioGeoref.instancia();

      Provincia provincia = servicio.provinciaBuscadaPorNombre("Cordoba");

      System.out.println(provincia.nombre);
      System.out.println(provincia.id);
      System.out.println(provincia.centroide.getLatitud());
      System.out.println(provincia.centroide.getLongitud());

    Departamento departamento = servicio.deptoBuscadoPorNombre("PALPALÁ");
    System.out.println("-------");
    System.out.println(departamento.id);
    System.out.println(departamento.nombre);
    System.out.println(departamento.centroide.getLatitud());
    System.out.println(departamento.centroide.getLongitud());
    System.out.println(departamento.provincia.nombre);

    Municipio municipio = servicio.municipioBuscadoPorNombre("Taco Ralo");
    System.out.println("-------");
    System.out.println(municipio.id);
    System.out.println(municipio.nombre);
    System.out.println(municipio.centroide.getLatitud());
    System.out.println(municipio.centroide.getLongitud());
    System.out.println(municipio.provincia.nombre);


  }
}

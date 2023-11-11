package ServiciosExternos;

import ServiciosExternos.DatosGeoRef.*;

import java.io.IOException;

public abstract class ServicioUbicacion {


  public abstract Provincia provinciaBuscadaPorNombre(String nombre) throws IOException;



  public abstract Municipio municipioBuscadoPorNombre(String nombre) throws IOException;


  public abstract Departamento deptoBuscadoPorNombre(String nombre) throws IOException;



}

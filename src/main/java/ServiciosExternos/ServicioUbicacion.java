package ServiciosExternos;

import ServiciosExternos.DatosAPI.*;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public abstract class ServicioUbicacion {


  public abstract Provincia provinciaBuscadaPorNombre(String nombre) throws IOException;



  public abstract Municipio municipioBuscadoPorNombre(String nombre) throws IOException;


  public abstract Departamento deptoBuscadoPorNombre(String nombre) throws IOException;



}

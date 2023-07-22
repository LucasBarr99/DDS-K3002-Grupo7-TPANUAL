package ServiciosExternos;
import ServiciosExternos.DatosAPI.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref extends ServicioUbicacion {
  private static ServicioGeoref instancia = null;
  private static int maximaCantidadRegistrosDefault = 10;
  private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  private ServicioGeoref() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioGeoref instancia(){
    if(instancia== null){
      instancia = new ServicioGeoref();
    }
    return instancia;
  }

  public Provincia provinciaBuscadaPorNombre(String nombre) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoProvincias> requestProvincias = georefService.provincias(nombre);
    Response<ListadoProvincias> responseProvincia = requestProvincias.execute();
    return responseProvincia.body().provincias.get(0);
  }

  public Municipio municipioBuscadoPorNombre(String nombre) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoMunicipios> requestMunicipio = georefService.municipios(nombre);
    Response<ListadoMunicipios> responseMunicipio = requestMunicipio.execute();
    return responseMunicipio.body().municipios.get(0);
  }

  public Departamento deptoBuscadoPorNombre(String nombre) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDepartamentos> requestDeptos = georefService.departamentos(nombre);
    Response<ListadoDepartamentos> responseDeptos = requestDeptos.execute();
    return responseDeptos.body().departamentos.get(0);

  }

/*
  public ListadoProvincias listadoDeTodasLasProvincias() throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoProvincias> requestProvinciasArgentinas = georefService.provincias();
    Response<ListadoProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public ListadoMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }
  public ListadoDepartamentos listadoDeDepartamentosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoMunicipios> requestListadoDeDepartamentos = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }
  */

}

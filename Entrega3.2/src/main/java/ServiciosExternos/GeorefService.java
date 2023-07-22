package ServiciosExternos;

import ServiciosExternos.DatosAPI.ListadoDepartamentos;
import ServiciosExternos.DatosAPI.ListadoMunicipios;
import ServiciosExternos.DatosAPI.ListadoProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService{
  @GET("provincias")
  Call<ListadoProvincias> provincias();

  @GET("provincias")
  Call<ListadoProvincias> provincias(@Query("nombre") String nombre);

  @GET("municipios")
  Call<ListadoMunicipios> municipios(@Query("nombre") String nombre);

  @GET("departamentos")
  Call<ListadoDepartamentos> departamentos(@Query("nombre") String nombre);


}

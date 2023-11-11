
import ServiciosExternos.DatosGeoRef.Coordenadas;
import ServiciosExternos.DatosGeoRef.Departamento;
import ServiciosExternos.DatosGeoRef.Municipio;
import ServiciosExternos.DatosGeoRef.Provincia;
import ServiciosExternos.ServicioGeoref;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TestGeoRef {

  ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
  ServicioGeoref servGeoRef = mock(ServicioGeoref.class);

  @Test
  void consultaProvinciaPorNombre() throws IOException {

    Coordenadas coordenadas = new Coordenadas((float) -32.142933, (float) -63.801754);
    Provincia unaProvincia = new Provincia(14,"Córdoba", coordenadas);


    when(servGeoRef.provinciaBuscadaPorNombre("Córdoba")).thenReturn(unaProvincia);
    
    Provincia otraProvincia = servGeoRef.provinciaBuscadaPorNombre("Córdoba");
    Assertions.assertEquals(14, otraProvincia.id);

  }
  @Test
  void consultaDepartamentoPorNombre() throws IOException {

    Coordenadas coordenadas = new Coordenadas((float) -24.194923, (float) -65.12645);
    Provincia provincia = new Provincia(38,"Jujuy");
    Departamento unDepartamento = new Departamento(38042,"PALPALÁ", coordenadas, provincia);


    when(servGeoRef.deptoBuscadoPorNombre("PALPALÁ")).thenReturn(unDepartamento);

    Departamento otroDepto = servGeoRef.deptoBuscadoPorNombre("PALPALÁ");
    Assertions.assertEquals(38042, otroDepto.id);
    Assertions.assertEquals(38, otroDepto.provincia.id);

  }
  @Test
  void consultaMunicipioPorNombre() throws IOException {

    Coordenadas coordenadas = new Coordenadas((float) -27.816619, (float) -65.199594);
    Provincia provincia = new Provincia(90,"Tucumán");
    Municipio unMunicipio = new Municipio(908210,"Taco Ralo", coordenadas, provincia);


    when(servGeoRef.municipioBuscadoPorNombre("Taco Ralo")).thenReturn(unMunicipio);

    Municipio otroMunicipio = servGeoRef.municipioBuscadoPorNombre("Taco Ralo");
    Assertions.assertEquals(908210, otroMunicipio.id);
    Assertions.assertEquals(90, otroMunicipio.provincia.id);

  }




}

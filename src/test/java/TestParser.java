
import CargaDeDatosCSV.DatosParser;
import CargaDeDatosCSV.TipoEntidad;
import Excepciones.ExcepcionParseoCSV;
import CargaDeDatosCSV.DatoOrganismosEmpresasCSV;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {


  @Test
  void archivoCSVCumpleConLosRequisitos(){
    DatoOrganismosEmpresasCSV parser = new DatoOrganismosEmpresasCSV();
    List<DatosParser> datos = new ArrayList<>();
    datos = parser.parsearCSV("src/main/java/CargaDeDatosCSV/datos.csv");
    assertEquals(3, datos.size());
  }
  @Test
  void archivoCSVNoCumpleConLosRequisitos(){
    DatoOrganismosEmpresasCSV parser = new DatoOrganismosEmpresasCSV();
    ExcepcionParseoCSV exception = assertThrows(ExcepcionParseoCSV.class,()-> parser.parsearCSV("src/main/java/CargaDeDatosCSV/datosErroneos.csv"));
    assertTrue(exception.getMessage().contains("Estructura de archivo invalida"));
  }

  @Test
  void archivoCSVContieneUnaEmpresa(){
    DatoOrganismosEmpresasCSV parser = new DatoOrganismosEmpresasCSV();
    List<DatosParser> datos = new ArrayList<>();
    datos = parser.parsearCSV("src/main/java/CargaDeDatosCSV/datos.csv");
    DatosParser dato = datos.get(0);
    assertEquals("Empresa",dato.getTipo());
    assertEquals("Trenes S.A",dato.getNombre());
    assertEquals("Tren Mitre",dato.getEntidad());
    assertEquals(TipoEntidad.LINEA_DE_TRANSPORTE,dato.getTipoEntidad());
  }

}

package CargaDeDatosCSV;





import Excepciones.ExcepcionParseoCSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatoOrganismosEmpresasCSV {
  DatosParser dato;

  public List<DatosParser> parsearCSV(String csvPath) {
    File csvFile = new File(csvPath);
    List<DatosParser> datos = new ArrayList<>();
    try {
      InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(csvFile));
      CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
      boolean validarHeader = true ;
      for (CSVRecord csvRecord : csvParser) {
        if (!validarHeader) {
          String tipoEmpresa = csvRecord.get(0);
          String nombreEmpresa = csvRecord.get(1);
          String entidad = csvRecord.get(2);
          String tipoEntidad = csvRecord.get(3);


          Map<String, TipoEntidad> mapa = getMapConTransporte();
          DatosParser nuevoDato = new DatosParser(tipoEmpresa,
              nombreEmpresa, entidad, mapa.get(tipoEntidad));
          datos.add(nuevoDato);
        }
        else {
          validarHeader = false;
          validarEstructuraArchivo(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2),  csvRecord.get(3));
        }
      }
    } catch (Exception e) {
      throw new ExcepcionParseoCSV("Error in Parsing CSV File" + e);
    }
    return datos;
  }

  public void validarEstructuraArchivo(String campo1, String campo2, String campo3, String campo4){
    if(!campo1.equalsIgnoreCase("TIPO") || !campo2.equalsIgnoreCase("NOMBRE") || !campo3.equalsIgnoreCase("ENTIDAD") || !campo4.equalsIgnoreCase("TIPO_ENTIDAD")) {
      throw new RuntimeException("Estructura de archivo invalida");
    }
  }
  private Map<String, TipoEntidad> getMapConTransporte() {
    Map<String, TipoEntidad> mapa = new HashMap<String, TipoEntidad>();
    mapa.put("Organizacion", TipoEntidad.ORGANIZACION);
    mapa.put("Linea de Transporte", TipoEntidad.LINEA_DE_TRANSPORTE);
    return mapa;
  }
}

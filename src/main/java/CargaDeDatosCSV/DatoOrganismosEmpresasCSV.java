package CargaDeDatosCSV;





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
          String nombreEmpresa = csvRecord.get(1);
          String tipoEmpresa = csvRecord.get(0);
          String lineaDeTransporte = csvRecord.get(2);
          String estacionOrigen = csvRecord.get(3);
          String estacionDestino = csvRecord.get(4);
          String medioDeTransporte = csvRecord.get(4);
          Map<String, MedioDeTransporte> mapa = getMapConTransporte();
          DatosParser nuevoDato = new DatosParser(nombreEmpresa,
              tipoEmpresa, lineaDeTransporte, estacionOrigen, estacionDestino, mapa.get(medioDeTransporte));
          datos.add(nuevoDato);
        }
        else {
          validarHeader = false;
          validarEstructuraArchivo(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2),  csvRecord.get(3),  csvRecord.get(4),  csvRecord.get(5));
        }
      }
    } catch (Exception e) {
      throw new ExcepcionParseoCSV("Error in Parsing CSV File" + e);
    }
    return datos;
  }

  public void validarEstructuraArchivo(String campo1, String campo2, String campo3, String campo4, String campo5, String campo6 ){
    if(!campo1.equalsIgnoreCase("TIPO") || !campo2.equalsIgnoreCase("NOMBRE") || !campo3.equalsIgnoreCase("LINEA_DE_TRANSPORTE") || !campo4.equalsIgnoreCase("ESTACION_ORIGEN")|| !campo5.equalsIgnoreCase("ESTACION_DESTINO")|| !campo6.equalsIgnoreCase("MEDIO_DE_TRANSPORTE")) {
      throw new RuntimeException("Estructura de archivo invalida");
    }
  }
  private Map<String, MedioDeTransporte> getMapConTransporte() {
    Map<String, MedioDeTransporte> mapa = new HashMap<String, MedioDeTransporte>();
    mapa.put("Subterraneo", MedioDeTransporte.SUBTERRANEO);
    mapa.put("Ferrocarril", MedioDeTransporte.FERROCARRIL);
    return mapa;
  }
}

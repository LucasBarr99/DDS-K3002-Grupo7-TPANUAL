package main.ApiClientePesado;

import Modelo.CargaDeDatosCSV.DatosParser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.servlet.http.HttpServletResponse;
import main.ApiClientePesado.Servicios.ServicioSubidaArchivos;
import main.ApiClientePesado.dto.LoginRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
public class CargaMasivaController {

  @Autowired
  ServicioSubidaArchivos servicioSubidaArchivos;

  @PostMapping(value="/apiPesada/cargaMasiva", consumes = "multipart/form-data")
  public String postLogin(@RequestParam("myFile") MultipartFile file) {
    String json = "{\"respuesta\":\"\"}";
    try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      CsvToBean<DatosParser> csvToBean = new CsvToBeanBuilder(reader)
          .withType(DatosParser.class)
          .withIgnoreLeadingWhiteSpace(true)
          .build();
      List<DatosParser> datos = csvToBean.parse();

      servicioSubidaArchivos.validarArchivo(datos);
    } catch (Exception ex) {
      json = "{\"respuesta\":"+ "Error"+ "\"\"}";
      return json;
    }
      return json;
  }


}

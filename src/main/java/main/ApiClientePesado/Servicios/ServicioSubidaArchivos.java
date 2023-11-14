package main.ApiClientePesado.Servicios;

import Modelo.CargaDeDatosCSV.DatoOrganismosEmpresasCSV;
import Modelo.CargaDeDatosCSV.DatosParser;
import Modelo.CargaDeDatosCSV.TipoEntidad;
import Modelo.CargaDeDatosCSV.TipoPrestadorServicio;
import Modelo.PrestadoresDeServicios.Empresa;
import Modelo.PrestadoresDeServicios.OrganismoDeControl;
import Modelo.PrestadoresDeServicios.PrestadorDeServicio;
import Persistencia.Repositorios.RepoPrestadoresDeServicio;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioSubidaArchivos {

  public void validarArchivo(List<DatosParser> datos){
    RepoPrestadoresDeServicio repo = RepoPrestadoresDeServicio.instance();

    for(int i = 0; i < datos.size() - 1; i++){
      DatosParser dato = datos.get(i);
      System.out.println(dato.getNombre());
      System.out.println(dato.getTipo());
      if(dato.getTipo().equals("Empresa")){
        repo.agregarPrestadorDeServicio(new Empresa(dato.getNombre()));
      } else
        repo.agregarPrestadorDeServicio(new OrganismoDeControl(dato.getNombre()));
    }


  }
}

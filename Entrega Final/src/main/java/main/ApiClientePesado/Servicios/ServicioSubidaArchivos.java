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
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioSubidaArchivos {

  public void validarArchivo(List<DatosParser> datos){
    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    for(int i = 0; i < datos.size(); i++){
      DatosParser dato = datos.get(i);;
      if(dato.getTipo().equals("Empresa")){
        em.persist(new Empresa(dato.getNombre()));
      } else
        em.persist(new OrganismoDeControl(dato.getNombre()));
    }
    transaction.commit();

  }





}

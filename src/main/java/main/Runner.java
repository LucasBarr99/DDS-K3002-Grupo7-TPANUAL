package main;
import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Establecimientos.Estacion;
import Modelo.Localizaciones.TipoLocalizacion;
import Modelo.Localizaciones.Ubicacion;
import Modelo.PrestadoresDeServicios.Empresa;
import Modelo.PrestadoresDeServicios.PrestadorDeServicio;
import Modelo.Servicios.Servicio;
import Persistencia.Repositorios.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {

  public static void main(String[] args){

/*
    // Aca obtenemos las mebresias de un usuario en particular, la performance te la regalo, pero los devuelve, corta
    List<Miembro> membresias = RepoMiembros.instance().obtenerMembresiasUsuario(1);



    List<Comunidad> comunidades = RepoComunidades.instance().obtenerTodos();



    List<Comunidad> comunidadesUsuario = comunidades.stream().filter(comunidad -> comunidad.tieneMiembros(membresias)).toList();

    Comunidad comunidad1 = comunidadesUsuario.get(0);

    System.out.println(comunidad1.getId());
    System.out.println(comunidad1.getNombre());
*/
    List<Servicio> servicios = RepoServicios.instance().obtenerServiciosDeComunidad(1);

    System.out.println(servicios.size());

  }
}
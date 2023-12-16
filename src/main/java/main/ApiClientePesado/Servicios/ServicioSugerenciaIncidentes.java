
package main.ApiClientePesado.Servicios;


import Modelo.Entidades.Entidad;
import Modelo.Incidentes.Incidente;
import Modelo.Localizaciones.Ubicacion;
import Modelo.Personas.Interesado;
import Modelo.Personas.Persona;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoEntidades;
import Persistencia.Repositorios.RepoIncidentes;
import Persistencia.Repositorios.RepoInteresados;
import Persistencia.Repositorios.RepoUsuarios;
import main.ApiClientePesado.dto.IncidentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ServicioSugerenciaIncidentes {

  public IncidentResponse incidenteASugerir(){
    int idUsuario = 1; //Esto lo pasariamos por query param

    Incidente incidenteASugerir = null;
    Usuario usuario = RepoUsuarios.instance().obtenerUsuario(idUsuario);
    List<Persona> interesados = RepoInteresados.instance().obtenerTodasLasPersonas();

    Persona personaBuscada = interesados.stream().filter(interesado -> interesado.tieneUsuario(usuario)).collect(Collectors.toList()).get(0);

    Ubicacion ubicacionPersona = personaBuscada.getUbicacion();

    String nombreUbicacion = ubicacionPersona.getNombre();

    List<Entidad> entidades = RepoEntidades.instance().obtenerTodas();

    List<Entidad> entidadesEnUbicacion = entidades.stream().filter(entidad -> entidad.estaEnUbicacion(ubicacionPersona)).collect(Collectors.toList());

    List<Incidente> incidentesAbiertos = RepoIncidentes.instance().obtenerIncidentesAbiertos();

    if(entidadesEnUbicacion.isEmpty()){
      Random random = new Random();
      int numeroIncidente =  random.ints(0, incidentesAbiertos.size()).findFirst().getAsInt();
      incidenteASugerir = RepoIncidentes.instance().obtenerIncidentesAbiertos().get(numeroIncidente);
    } else {
      List<Incidente> incidentesEntidades = incidentesAbiertos.stream().filter(incidente -> incidente.getServicio().perteneceAalgunaEntidad(entidadesEnUbicacion)).collect(Collectors.toList());

      if (!incidentesEntidades.isEmpty()) {
        Random random2 = new Random();
        int numeroIncidenteEntidad = random2.ints(0, incidentesEntidades.size()).findFirst().getAsInt();
        incidenteASugerir = incidentesEntidades.get(numeroIncidenteEntidad);
      }
    }

    return new IncidentResponse(incidenteASugerir.getNombre(),incidenteASugerir.fechaAperturaBD, incidenteASugerir.fechaCierreBD,
        incidenteASugerir.getServicioAfectado().getDescripcion(), incidenteASugerir.getDescripcion(), incidenteASugerir.getEstado(),
        incidenteASugerir.getServicio().getEntidad().getNombre());

  }

}

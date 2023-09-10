package Establecimientos;

import Persistencia.EntidadPersistente;
import Personas.Interesado;
import Personas.Usuario;
import Localizaciones.Ubicacion;
import Servicios.Servicio;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Establecimientos")
public abstract class Establecimiento extends EntidadPersistente {
  @Column(name = "NombreE")
  public String nombre;

  @Embedded
  public Ubicacion ubicacion;
  public List<Servicio> servicios;
  public List<Interesado> interesados;

  public void notificarInteresados(){
    interesados.forEach(interesado -> interesado.notificar());
  }
}

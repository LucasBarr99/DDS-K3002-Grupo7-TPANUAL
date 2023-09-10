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

  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name = "idestablecimiento")
  public List<Servicio> servicios;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
            name = "EstablecimientoPorInteresado",
            joinColumns = { @JoinColumn(name = "idestablecimiento") },
            inverseJoinColumns = { @JoinColumn(name = "idinteresado") }
    )
  public List<Interesado> interesados;

  public void notificarInteresados(){
    interesados.forEach(interesado -> interesado.notificar());
  }

   public String getNombre(){
    return this.nombre;
  }
    public Ubicacion getUbicacion() {
      return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
      this.ubicacion = ubicacion;
    }

    public void setNombre(String nombre) {
    this.nombre = nombre;
  }


}

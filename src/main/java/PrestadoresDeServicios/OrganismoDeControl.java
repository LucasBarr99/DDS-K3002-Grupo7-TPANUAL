package PrestadoresDeServicios;

import Persistencia.EntidadPersistente;
import Personas.Usuario;
import Entidades.Entidad;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("OrganismoControl")
public class OrganismoDeControl extends PrestadorDeServicio {


  public OrganismoDeControl() {

  }
}

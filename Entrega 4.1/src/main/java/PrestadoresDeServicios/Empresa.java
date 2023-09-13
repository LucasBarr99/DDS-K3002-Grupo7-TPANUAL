package PrestadoresDeServicios;

import Personas.Usuario;
import Entidades.Entidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;
@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends PrestadorDeServicio {


  public Empresa(){}
}

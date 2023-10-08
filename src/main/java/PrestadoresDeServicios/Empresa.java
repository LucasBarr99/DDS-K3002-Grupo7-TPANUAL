package PrestadoresDeServicios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends PrestadorDeServicio {


  public Empresa(){}
}

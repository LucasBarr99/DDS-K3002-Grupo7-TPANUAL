package PrestadoresDeServicios;

import javax.persistence.*;

@Entity
@DiscriminatorValue("OrganismoControl")
public class OrganismoDeControl extends PrestadorDeServicio {


  public OrganismoDeControl() {

  }
}

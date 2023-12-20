package ServiciosExternos.Notifcaciones;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Table(name= "ServicioNotificacion")
public abstract class Notificacion extends EntidadPersistente {
  public void notificar(String mensaje, String numero, String mailDestino, String asunto){
  }
}

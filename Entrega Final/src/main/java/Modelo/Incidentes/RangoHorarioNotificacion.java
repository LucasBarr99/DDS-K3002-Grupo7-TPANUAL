package Modelo.Incidentes;

import Persistencia.EntidadPersistente;
import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.LocalTime;
@Embeddable
public class RangoHorarioNotificacion {
  public LocalTime inicio;
  public LocalTime fin;

  public RangoHorarioNotificacion(LocalTime inicio, LocalTime fin) {
    this.inicio = inicio;
    this.fin = fin;
  }

  public RangoHorarioNotificacion() {

  }

  public boolean estaDentroDeRango(LocalTime hora){
    return hora.isAfter(this.inicio) && hora.isBefore(this.fin);
  }

  public LocalTime getInicio() {
    return inicio;
  }

  public LocalTime getFin() {
    return fin;
  }


}

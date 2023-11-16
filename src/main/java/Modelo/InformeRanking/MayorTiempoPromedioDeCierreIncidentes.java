package Modelo.InformeRanking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TiempoPromedio")
public class MayorTiempoPromedioDeCierreIncidentes extends GeneradorInforme {

  @Override
  public Informe generarInforme() {
    return null;
  }


  public MayorTiempoPromedioDeCierreIncidentes(){

  }

  public MayorTiempoPromedioDeCierreIncidentes(String nombre){
    super(nombre);
  }
}

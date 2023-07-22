package ServiciosExternos.Notifcaciones;

public class EjemploDeUsoMail {

  public static void main(String[] args){
    NotificacionJavaMail servicioNotificacion = new NotificacionJavaMail();

    servicioNotificacion.enviarEmail("lucasmarcelobatalla01@gmail.com","Se ha reportado un incidente, favor de ir al servicio x", "Era joda pa");

  }
}

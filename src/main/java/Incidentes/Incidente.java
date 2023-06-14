package Incidentes;

import Comunidades.Miembro;
import Entidades.Entidad;
import Servicios.Servicio;

import java.time.LocalDate;

public class Incidente {
    LocalDate fechaApertura;
    LocalDate fechaCierre;
    Servicio servicioAfectado;
    String observaciones;
    EstadoIncidentes estado;
    Miembro miembro;


    void cerrar(){
        this.estado = EstadoIncidentes.CERRADO;
    }


}


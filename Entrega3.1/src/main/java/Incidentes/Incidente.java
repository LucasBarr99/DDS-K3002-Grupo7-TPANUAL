package Incidentes;

import Comunidades.Miembro;
import Entidades.Entidad;
import Servicios.Servicio;

import java.time.LocalDate;

public class Incidente {
    public String nombre;
    public LocalDate fechaApertura;
    public LocalDate fechaCierre;
    public Servicio servicioAfectado;
    public String descripcion;
    public EstadoIncidentes estado;
    public Miembro miembro;

    public Incidente(String nombre, LocalDate fechaApertura, LocalDate fechaCierre, Servicio servicioAfectado, String descripcion, EstadoIncidentes estado, Miembro miembro) {
        this.nombre = nombre;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.servicioAfectado = servicioAfectado;
        this.descripcion = descripcion;
        this.estado = estado;
        this.miembro = miembro;
    }

    void cerrar(){
        this.estado = EstadoIncidentes.CERRADO;
    }

    public String getNombre() {
        return nombre;
    }

    public Servicio getServicioAfectado() {
        return servicioAfectado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoIncidentes getEstado() {
        return this.estado;
    }

    public Servicio getServicio(){
        return servicioAfectado;
    }
}


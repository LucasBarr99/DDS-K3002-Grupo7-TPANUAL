package Incidentes;

import Comunidades.Comunidad;
import Comunidades.Miembro;
import Entidades.Entidad;
import Persistencia.EntidadPersistente;
import Servicios.Servicio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Incidente extends EntidadPersistente {

    public String nombre;

    public LocalDate fechaApertura;

    public LocalDate fechaCierre;
    @ManyToOne
    public Servicio servicioAfectado;

    public String descripcion;
    @Enumerated
    public EstadoIncidentes estado;
    @OneToOne
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

    public Incidente() {

    }

    void cerrar(){
        this.estado = EstadoIncidentes.CERRADO;
    }

    public boolean estaCerrado(){
        return estado.equals(EstadoIncidentes.CERRADO);
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

    public Comunidad comunidadInvolucradaEnIncidente(){
        return miembro.getComunidad();
    }


}


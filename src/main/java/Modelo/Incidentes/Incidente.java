package Modelo.Incidentes;

import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Persistencia.EntidadPersistente;
import Modelo.Servicios.Servicio;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Incidente extends EntidadPersistente {

    public String nombre;

    @Column(name = "fechaApertura")
    public Date fechaAperturaBD;
    @Column(name = "fechaCierre")
    public Date fechaCierreBD;
    @Transient
    public LocalDate fechaApertura;
    @Transient
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

    public Incidente(String nombre, Date fechaApertura, Date fechaCierre, Servicio servicioAfectado, String descripcion, EstadoIncidentes estado, Miembro miembro) {
        this.nombre = nombre;
        this.fechaAperturaBD = fechaApertura;
        this.fechaCierreBD = fechaCierre;
        this.servicioAfectado = servicioAfectado;
        this.descripcion = descripcion;
        this.estado = estado;
        this.miembro = miembro;
    }

    public Incidente(String nombre, Date fechaApertura, Servicio servicioAfectado, String descripcion, EstadoIncidentes estado, Miembro miembro) {
        this.nombre = nombre;
        this.fechaAperturaBD = fechaApertura;
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


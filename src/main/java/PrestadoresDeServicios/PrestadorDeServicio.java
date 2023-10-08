package PrestadoresDeServicios;

import Entidades.Entidad;
import Persistencia.EntidadPersistente;
import Personas.Persona;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class PrestadorDeServicio extends EntidadPersistente {

    String nombre;

    @OneToOne
    @JoinColumn(name= "idPrestadorServicio")
    Persona persona;
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "idPrestadorServicio")
    List<Entidad> entidades;
    @OneToOne
    @JoinColumn(name= "idPrestadorServicioDesignado")
    Persona personaDesignado;


    public PrestadorDeServicio(){}

    public PrestadorDeServicio(String nombre, Persona persona, List<Entidad> entidades, Persona personaDesignado) {
        this.nombre = nombre;
        this.persona = persona;
        this.entidades = entidades;
        this.personaDesignado = personaDesignado;
    }
}

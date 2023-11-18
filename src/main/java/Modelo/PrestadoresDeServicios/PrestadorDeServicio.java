package Modelo.PrestadoresDeServicios;

import Modelo.Entidades.Entidad;
import Modelo.Personas.Persona;
import Modelo.Personas.Usuario;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class PrestadorDeServicio extends EntidadPersistente {

    String nombre;

    @OneToOne
    @JoinColumn(name= "idUsuarioAdmin")
    Usuario usuarioAdmin;
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "idPrestadorServicio")
    List<Entidad> entidades;
    @OneToOne
    @JoinColumn(name= "idUsuarioDesignado")
    Usuario usuarioDesignado;


    public PrestadorDeServicio(){}

    public PrestadorDeServicio(String nombre){
        this.nombre = nombre;
    }

    public PrestadorDeServicio(String nombre, Usuario usuarioadmin, List<Entidad> entidades, Usuario usuarioDesignado) {
        this.nombre = nombre;
        this.usuarioAdmin = usuarioadmin;
        this.entidades = entidades;
        this.usuarioDesignado = usuarioDesignado;
    }
}

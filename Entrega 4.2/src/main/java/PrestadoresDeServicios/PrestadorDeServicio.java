package PrestadoresDeServicios;

import Entidades.Entidad;
import Persistencia.EntidadPersistente;
import Personas.Usuario;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class PrestadorDeServicio extends EntidadPersistente {

    String nombre;

    @OneToOne
    @JoinColumn(name= "idPrestadorServicio")
    Usuario usuario;
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "idPrestadorServicio")
    List<Entidad> entidades;
    @OneToOne
    @JoinColumn(name= "idPrestadorServicioDesignado")
    Usuario usuarioDesignado;


    public PrestadorDeServicio(){}

    public PrestadorDeServicio(String nombre, Usuario usuario, List<Entidad> entidades, Usuario usuarioDesignado) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.entidades = entidades;
        this.usuarioDesignado = usuarioDesignado;
    }
}

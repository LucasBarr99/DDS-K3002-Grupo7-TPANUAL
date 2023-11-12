package Persistencia.Repositorios;

import Modelo.Personas.Interesado;
import Modelo.Personas.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepoUsuarios extends Repositorio<Usuario>{
    private static final RepoUsuarios INSTANCE = new RepoUsuarios();
    private List<Usuario> usuarios = new ArrayList<>();

    public static RepoUsuarios instance() {
        return INSTANCE;
    }

    private RepoUsuarios() {
        super("Interesado");
    }

    public void agregarUsuario(Usuario usuario) {
        entityManager().persist(usuario);
    }

    public Usuario obtenerUsuario(String nombre) {

        String query = String.format("from Usuario where nombre='%s'", nombre);
        return (Usuario) entityManager().createQuery(query).getResultList().get(0);
    }

    public Usuario obtenerUsuario(int id) {

        String query = String.format("from usuarios where id='%s'", id);
        return (Usuario) entityManager().createQuery(query).getResultList().get(0);
    }

    public String obtenerContrasenia(String user) {

        String query = String.format("from Usuario where nombre='%s'", user);
        Usuario usuario = (Usuario) entityManager().createQuery(query).getResultList().get(0);
        return usuario.getContrasenia();
    }
}

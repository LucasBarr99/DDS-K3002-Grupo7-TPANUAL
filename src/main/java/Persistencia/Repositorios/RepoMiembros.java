package Persistencia.Repositorios;

import Modelo.Comunidades.Miembro;
import Modelo.Entidades.Entidad;

import java.util.ArrayList;
import java.util.List;

public class RepoMiembros extends Repositorio<Miembro>{
    private static final RepoMiembros INSTANCE = new RepoMiembros();
    private List<Miembro> miembros = new ArrayList<>();

    public static RepoMiembros instance() {
        return INSTANCE;
    }

    private RepoMiembros() {
        super("Miembros");
    }

    public void agregarMiembros(Miembro miembro) {
        entityManager().persist(miembro);
    }

    public Miembro obtenerMiembro(String id) {

        String query = String.format("from Miembro where id='%s'", id);
        return (Miembro) entityManager().createQuery(query).getResultList().get(0);
    }
}
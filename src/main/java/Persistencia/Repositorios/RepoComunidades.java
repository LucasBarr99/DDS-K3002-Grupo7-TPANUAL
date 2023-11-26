package Persistencia.Repositorios;

import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.InformeRanking.GeneradorInforme;

import java.util.ArrayList;
import java.util.List;

public class RepoComunidades extends Repositorio<Comunidad>{

    private static final RepoComunidades INSTANCE = new RepoComunidades();
    private List<RepoComunidades> criterios = new ArrayList<>();

    public static RepoComunidades instance() {
        return INSTANCE;
    }

    private RepoComunidades() {
        super("Comunidades");
    }

    public void agregarComunidad(Comunidad comunidad) {
        entityManager().persist(comunidad);
    }


    public List<Comunidad> obtenerComunidades() {

        String query = String.format("from Comunidad");
        return entityManager().createQuery(query).getResultList();
    }

    public List<Miembro> obtenerMiembrosDeComunidad(int idComunidad) {

        String query = String.format("from Miembro where idcomunidad='%s'", idComunidad);
        return entityManager().createQuery(query).getResultList();
    }

    public List<Comunidad> obtenerComunidad(int idComunidad) {

        String query = String.format("from Comunidad where id='%s'", idComunidad);
        return entityManager().createQuery(query).getResultList();
    }
    public List<Comunidad> obtenerTodos() {

        String query = String.format("from Comunidad");
        return entityManager().createQuery(query).getResultList();
    }


}

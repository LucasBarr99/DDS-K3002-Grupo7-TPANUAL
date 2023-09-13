package Repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class Repositorio<E> implements WithGlobalEntityManager {

  private final String nombreTabla;

  public Repositorio(String nombreTabla) {
    this.nombreTabla = nombreTabla;
  }

  public void actualizar(E objeto) {
    entityManager().persist(objeto);
  }

  public void agregar(E objeto) {

    if(this.contiene(objeto)){
      throw new RuntimeException("Ya fue registrado");
    }
    // EntityManagerHelper.beginTransaction();
    entityManager().persist(objeto);
    // EntityManagerHelper.commit();
  }

  public boolean contiene(E objeto) {
    return entityManager().contains(objeto);
  }

  public int cantidad() {
    return (int) todos().stream().count();
  }

  public List<E> todos() {
    return entityManager()
        .createQuery("from " + nombreTabla)
        .getResultList();
  }

  public E getUltimo() {
    return todos().get(this.cantidad()-1);
  }

  public E buscar(int id) {

    List lista = entityManager()
        .createQuery("from " + nombreTabla + " where id =: idBuscado")
        .setParameter("idBuscado", id)
        .getResultList();

    if(lista.isEmpty()){
      throw new RuntimeException("No se encontro al objeto de " + nombreTabla + " de id " + id);
    }
    else{
      return  (E) lista.get(0);
    }
  }

  public void eliminar(int id) {
    entityManager().remove(buscar(id));
  }


}
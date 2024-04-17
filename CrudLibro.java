package biblioteca;

import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;


public class CrudLibro {
private SessionFactory sessionFactory;

public CrudLibro (SessionFactory sessionFactory) {
  this.sessionFactory = sessionFactory;
}

public void nuevoLibro (Libro libro) {
  Session session = sessionFactory.openSession();
  Transaction tx = null;
  try {
    tx= session.beginTransaction();
    session.save(libro);
    tx.commit();
    
  }catch (Exception e) {
    e.printStackTrace();
  
  }
}
public Libro verLibro(Long id) {
    try (Session session = sessionFactory.openSession()) {
        return session.get(Libro.class, id);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public void actualizarLibro(Libro libro) {
    try (Session session = sessionFactory.openSession()) {
        Transaction tx = session.beginTransaction();
        session.update(libro);
        tx.commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void eliminarLibro(Libro libro) {
    try (Session session = sessionFactory.openSession()) {
        Transaction tx = session.beginTransaction();
        session.delete(libro);
        tx.commit();
    } catch (Exception e) {
        e.printStackTrace();
      }
  }

public List <Libro> obtenerLibrosDisponibles() {
    try (Session session = sessionFactory.openSession()) {
        Query<Libro> query = session.createQuery("FROM Libro WHERE disponible = true", Libro.class);
        return query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
  }
public Libro obtenerLibroPorId(Long id) {
    try (Session session = sessionFactory.openSession()) {
        return session.get(Libro.class, id);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
  }
public List<Libro> obtenerTodosLosLibros() {
    try (Session session = sessionFactory.openSession()) {
        String hql = "FROM Libro";
        Query<Libro> query = session.createQuery(hql, Libro.class);
        return query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}

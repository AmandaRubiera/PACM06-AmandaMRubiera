package biblioteca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;



public class CrudLector {
    private SessionFactory sessionFactory;

    public CrudLector(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarLector(UsuarioLector lector) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(lector);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UsuarioLector obtenerLector(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(UsuarioLector.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarLector(UsuarioLector lector) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(lector);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarLector(UsuarioLector lector) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(lector);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UsuarioLector obtenerLectorPorId(long lectorId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(UsuarioLector.class, lectorId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<UsuarioLector> obtenerTodosLosLectores() {
        try (Session session = sessionFactory.openSession()) {
            Query<UsuarioLector> query = session.createQuery("FROM UsuarioLector", UsuarioLector.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
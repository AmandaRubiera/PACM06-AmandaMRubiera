package biblioteca;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Transaction; 

public class CrudPrestamo {

    private Session session;

    public CrudPrestamo (Session session) {
        this.session = session;
    }

    public List<Prestamo> obtenerPrestamosNoDevueltosPorLector(long idLector) {
        try {
            String hql = "FROM Prestamo WHERE idUsuarioLector = :idLector AND fechaDevolucion IS NULL";
            Query<Prestamo> query = session.createQuery(hql, Prestamo.class);
            query.setParameter("idLector", idLector);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Prestamo> obtenerHistorialPrestamosPorLector(long idLector) {
    	
        try {
            String hql = "FROM Prestamo WHERE idUsuarioLector = :idLector";
            Query<Prestamo> query = session.createQuery(hql, Prestamo.class);
            query.setParameter("idLector", idLector);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void agregarPrestamo(Prestamo prestamo) {
        try {
            session.save(prestamo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Prestamo obtenerPrestamoPorId(long idPrestamo) {
        try {
            return session.get(Prestamo.class, idPrestamo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        try {
            Transaction tx = session.beginTransaction();
            session.update(prestamo);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

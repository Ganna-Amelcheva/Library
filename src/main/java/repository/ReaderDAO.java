package repository;

import model.Reader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Util;

import java.util.List;

public class ReaderDAO {
    public void addReader(Reader reader) {
        Transaction transaction = null;
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            transaction = session.beginTransaction();
            session.save(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Reader> findAllReaders() {
        try (
                Session session = Util.getSessionFactory().openSession();
        ) {
            return session.createQuery("From Reader", Reader.class).stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Reader findReaderById(int id) {
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            return session.get(Reader.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
//123
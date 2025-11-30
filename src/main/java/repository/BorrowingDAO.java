package repository;

import model.Borrowing;
import model.Reader;
import org.hibernate.Session;
import model.Book;
import org.hibernate.Transaction;
import util.Util;

import java.time.LocalDate;
import java.util.List;

public class BorrowingDAO {

    public void borrowBook(Borrowing borrowing) {
        Transaction transaction = null;
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            session.save(borrowing);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public void returnBook(int borrowingId) {
        Transaction transaction = null;
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            Borrowing borrowing = session.get(Borrowing.class, borrowingId);
            borrowing.setExpected_return_date(LocalDate.now());
            session.update(borrowing);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public List<Borrowing> findActiveBorrowings() {
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            return session.createQuery("From Borrowing Where return_date IS NULL", Borrowing.class).stream().toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Borrowing> findOverdueBorrowings() {
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            return session.createQuery("From Borrowing Where return_date IS NULL AND expected_return_date<:toDay",
                    Borrowing.class).setParameter("toDay", LocalDate.now()).stream().toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
    public boolean isBookAvailable(int bookId) {
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            Borrowing borrowing = session.get(Borrowing.class, bookId);
            if (borrowing.getReturn_date() == null) {
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


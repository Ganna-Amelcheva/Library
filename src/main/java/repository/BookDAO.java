package repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Util;
import model.Book;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession();
        ) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }

    public void updateBook(Book book) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession();
        ) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }

    public void updateBook2(Book book) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession();
        ) {
            transaction = session.beginTransaction();
            String sql = "Update Book set title=:title, author_id=:author_id, year=:year Where id=:id";
            session.createQuery(sql).setParameter("title", book.getTitle())
                    .setParameter("author_id", book.getAuthor_id())
                    .setParameter("year", book.getYear())
                    .setParameter("id", book.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }

    public void deleteBook(int id) {
        Transaction transaction = null;
        try (
                Session session = Util.getSessionFactory().openSession()
        ) {
            transaction = session.beginTransaction();
            String sql = "Delete From Book Where id=:id";
            session.createQuery(sql).setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }

    public Book findBookById(int id) {
        try (
                Session session = Util.getSessionFactory().openSession()) {

            return session.get(Book.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findAllBooks() {
        try (
                Session session = Util.getSessionFactory().openSession()) {

            return session.createQuery("From Book", Book.class).stream().toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Book> findBooksAuthors(int author_id){
        try (
                Session session = Util.getSessionFactory().openSession()) {

            return session.createQuery("From Book Where Book.author_id=:author_id", Book.class)
                    .setParameter("author_id", author_id).stream().toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List <Book>  findBooksByTitle(String title){
        try (
                Session session = Util.getSessionFactory().openSession()) {

            return session.createQuery("From Book Where Book.title Like :title", Book.class)
                    .setParameter("title", "%"+title+"%").stream().toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
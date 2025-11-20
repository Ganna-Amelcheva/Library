package repository;

import model.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Util;

import java.util.List;

public class AuthorDAO {

    public void addAuthor(Author author){
        Transaction transaction = null;
        try(
                Session session = Util.getSessionFactory().openSession()
                ){
            transaction= session.beginTransaction();
            session.save(author);
            transaction.commit();
        }catch(Exception e){
            if(transaction !=null && transaction.isActive())
                transaction.rollback();
        }
    }
    public List<Author> findAllAuthors(){
        try(
                Session session = Util.getSessionFactory().openSession()
                ){
            return session.createQuery("From Author", Author.class).stream().toList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Author findAuthorById(int id){
        try(
                Session session = Util.getSessionFactory().openSession()
                ){
            return session.get(Author.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Author> findAuthorsByCountry(String country){
        try(
                Session session = Util.getSessionFactory().openSession()
                ){
            return session.createQuery("From Author Where Author.country=:country", Author.class)
                    .setParameter("country", country).stream().toList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

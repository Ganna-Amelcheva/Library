package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Author;
import model.Borrowing;
import model.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Book;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Util {

    private static final SessionFactory SESSION_FACTORY;

    static {
SESSION_FACTORY = new Configuration()
        .configure("hibernate.properties")
        .addAnnotatedClass(Author.class)
        .addAnnotatedClass(Book.class)
        .addAnnotatedClass(Reader.class)
        .addAnnotatedClass(Borrowing.class)
        .buildSessionFactory();
    }
    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }
}

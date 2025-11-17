import org.hibernate.Session;
import util.Util;

public class Main {
    public static void main(String[] args) {
        Session session = Util.getSessionFactory().openSession();
    }
}

import model.Author;
import model.Reader;
import org.hibernate.Session;
import service.LibraryService;
import service.ReportService;
import util.Util;
import model.Book;

public class Main {
    public static void main(String[] args) {
       
        LibraryService libraryService = new LibraryService();
        ReportService reportService = new ReportService();
        Author author = new Author("Достоевский", "Russia", "10.11.1867");
        Book book = new Book("Преступление и наказание", author, 1234, 1896, 1);
        Reader reader = new Reader("Ivanov", "Ivan", "ads@yandex.ru", "8-967-888-3480");

        libraryService.reserveishon(book, reader);
        System.out.println(libraryService.findBook(book));


    }
}

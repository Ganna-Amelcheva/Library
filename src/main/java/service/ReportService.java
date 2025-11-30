package service;

import model.Borrowing;
import repository.AuthorDAO;
import repository.BookDAO;
import repository.BorrowingDAO;
import model.Book;
import java.time.LocalDate;
import java.time.Period;

import java.util.HashMap;
import java.util.List;

public class ReportService {
    AuthorDAO authorDAO = new AuthorDAO();
    BorrowingDAO borrowingDAO = new BorrowingDAO();
    BookDAO bookDAO = new BookDAO();

    public void booksAfterExpectedDate(){
        List<Borrowing> booksBorrowing = borrowingDAO.findOverdueBorrowings();
       for(Borrowing borrow: booksBorrowing){
           String title = borrow.getBook().getTitle();
           String readerName = borrow.getReader().getFirst_name();
           int daysOverDue = Period.between(borrow.getExpected_return_date(), LocalDate.now()).getDays();
           System.out.println(title+ " "+readerName+" "+daysOverDue);
       }
    }

    public HashMap<String, Integer> statisticForAuthor(){
        List<Book> books = bookDAO.findAllBooks();
        HashMap<String, Integer> statistic = new HashMap<>();
        for(Book book: books){
            String author = book.getAuthor().getName();
            statistic.put(author, statistic.getOrDefault(author, 0)+1);
        }
        return statistic;
    }
}
//ReportService - для генерации отчетов:
//список просроченных книг
//статистика по авторам

package service;

import model.Borrowing;
import model.Reader;
import repository.AuthorDAO;
import repository.BookDAO;
import repository.BorrowingDAO;
import repository.ReaderDAO;
import model.Book;
import java.time.LocalDate;


public class LibraryService {

    AuthorDAO authorDAO = new AuthorDAO();
    BookDAO bookDAO = new BookDAO();
    ReaderDAO readerDAO = new ReaderDAO();
    BorrowingDAO borrowingDAO = new BorrowingDAO();


    public void reserveishon(Book book, Reader reader) {
        if (book == null) {
            return;
        }
        if (borrowingDAO.isBookAvailable(book.getId())) {
            Borrowing borrowing = new Borrowing();
            borrowing.setBorrow_date(LocalDate.now());
            borrowing.setBook(book);
            borrowing.setReader(reader);
            borrowing.setExpected_return_date(LocalDate.now().plusWeeks(2));

            borrowingDAO.borrowBook(borrowing);
        }
    }
    public void returnBook(Book book){
       borrowingDAO.returnBook(book.getId());
    }

    public boolean findBook(Book book){
        if(book ==null){
            return false;
        }
        return borrowingDAO.isBookAvailable(book.getId());
    }
}

//выдача книги читателю
//возврат книги
//проверка доступности книги
//поиск книг по различным критериям
//

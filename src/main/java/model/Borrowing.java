package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "borrowings")
@AllArgsConstructor
@NoArgsConstructor
public class Borrowing {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader")
    private Reader reader;
    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrow_date;
    @Column(name = "return_date")
    private LocalDate return_date;
    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expected_return_date;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book_id) {
        this.book = book_id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader_id) {
        this.reader = reader_id;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public LocalDate getExpected_return_date() {
        return expected_return_date;
    }

    public void setExpected_return_date(LocalDate expected_return_date) {
        this.expected_return_date = expected_return_date;
    }
}

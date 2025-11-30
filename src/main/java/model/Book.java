package model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author author;
    @Column(name="isbn", unique = true)
    private int isbn;
    @Column(name = "year")
    private int year;
    @Column(name = "quantity")
    private int quantity = 1;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
   private List<Book> books = new ArrayList<>();

    public Book(String title, Author author_id, int isbn, int year, int quantity) {
        this.title = title;
        this.author = author_id;
        this.isbn = isbn;
        this.year = year;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author_id) {
        this.author = author_id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

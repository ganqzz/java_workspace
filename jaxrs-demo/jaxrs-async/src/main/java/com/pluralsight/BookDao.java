package com.pluralsight;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class BookDao {

    private Map<String, Book> books;
    private ListeningExecutorService service;

    BookDao() {
        books = new ConcurrentHashMap<String, Book>();
        service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        fixtures();
    }

    private void fixtures() {
        Book book1 = new Book();
        book1.setTitle("Hoge");
        book1.setAuthor("Author Hoge");
        book1.setPublished(new Date());

        Book book2 = new Book();
        book2.setTitle("Fuga");
        book2.setAuthor("Author Fuga");
        book2.setPublished(new Date());

        addBook(book1);
        addBook(book2);
    }

    Collection<Book> getBooks() {
        return (books.values());
    }

    ListenableFuture<Collection<Book>> getBooksAsync() {
        ListenableFuture<Collection<Book>> future =
                service.submit(new Callable<Collection<Book>>() {
                    @Override
                    public Collection<Book> call() throws Exception {
                        return getBooks();
                    }
                });
        return (future);
    }

    Book getBook(String id) throws BookNotFoundException {
        if (books.containsKey(id)) {
            return (books.get(id));
        } else {
            throw new BookNotFoundException("Book " + id + " is not found");
        }
    }

    ListenableFuture<Book> getBookAsync(final String id) {
        ListenableFuture<Book> future =
                service.submit(new Callable<Book>() {
                    @Override
                    public Book call() throws Exception {
                        return getBook(id);
                    }
                });
        return (future);
    }

    Book addBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(), book);
        return (book);
    }

    ListenableFuture<Book> addBookAsync(final Book book) {
        ListenableFuture<Book> future =
                service.submit(new Callable<Book>() {
                    @Override
                    public Book call() throws Exception {
                        return addBook(book);
                    }
                });
        return (future);
    }

    Book updateBook(String id, Book updates) throws BookNotFoundException {
        if (books.containsKey(id)) {
            Book book = books.get(id);
            if (updates.getTitle() != null) { book.setTitle(updates.getTitle()); }
            if (updates.getAuthor() != null) { book.setAuthor(updates.getAuthor()); }
            if (updates.getPublished() != null) { book.setPublished(updates.getPublished()); }
            if (updates.getExtras() != null) {
                for (String key : updates.getExtras().keySet()) {
                    book.set(key, updates.getExtras().get(key));
                }
            }
            return(book);
        } else {
            throw new BookNotFoundException("Book " + id + " is not found");
        }
    }

    ListenableFuture<Book> updateBookAsync(final String id, final Book book) {
        ListenableFuture<Book> future =
                service.submit(new Callable<Book>() {
                    public Book call() throws Exception {
                        return updateBook(id, book);
                    }
                });
        return(future);
    }
}

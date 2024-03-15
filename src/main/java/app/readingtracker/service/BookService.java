package app.readingtracker.service;

import app.readingtracker.entity.Book;
import app.readingtracker.model.response.BookShortDetailResponse;

import java.util.List;

public interface BookService {
    Book create(Book book);
    List<Book> getAll();
    List<BookShortDetailResponse> searchAuthorOrTitle(String search);
    Book get(String id);
    void delete(String id);
    Book update(Book book);

    List<BookShortDetailResponse> getAllShortResponse(String listId);
}

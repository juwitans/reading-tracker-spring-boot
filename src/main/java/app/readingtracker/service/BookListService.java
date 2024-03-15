package app.readingtracker.service;

import app.readingtracker.entity.BookList;
import app.readingtracker.model.request.BookListRequest;
import app.readingtracker.model.response.BookListResponse;

import java.util.List;

public interface BookListService {
    BookList create(BookListRequest list);
    List<BookListResponse> getAll();
    BookListResponse get(String id);
    BookListResponse update(String id, BookListRequest list);
    void delete(String id);
}

package app.readingtracker.service.impl;

import app.readingtracker.entity.Book;
import app.readingtracker.entity.BookList;
import app.readingtracker.model.request.BookListRequest;
import app.readingtracker.model.response.BookListResponse;
import app.readingtracker.model.response.BookShortDetailResponse;
import app.readingtracker.repository.BookListRepository;
import app.readingtracker.service.BookService;
import app.readingtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookListServiceImpl implements app.readingtracker.service.BookListService {
    private final BookListRepository bookListRepository;
    private final BookService bookService;
    private final UserService userService;

    @Override
    public BookList create(BookListRequest list) {
        List<String> bookIds = list.getBookId();
        List<Book> books = bookIds.stream()
                .map(bookService::get)
                .collect(Collectors.toList());
        BookList bookList = BookList.builder()
                .name(list.getName())
                .description(list.getDescription())
                .books(books)
                .user(userService.get(list.getUserId()))
                .build();
        return bookListRepository.save(bookList);
    }

    @Override
    public List<BookListResponse> getAll() {
        List<BookList> bookLists = bookListRepository.findAll();
        return bookLists.stream().map(list -> toBookListResponse(list))
                .collect(Collectors.toList());
    }

    @Override
    public BookListResponse get(String id) {
        Optional<BookList> optionalBookList = bookListRepository.findById(id);
        if (optionalBookList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list not found");
        return toBookListResponse(optionalBookList.get());
    }

    @Override
    public BookListResponse update(String id, BookListRequest list) {
        Optional<BookList> optionalBookList = bookListRepository.findById(id);
        if (optionalBookList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list not found");
        List<String> bookIds = list.getBookId();
        List<Book> books = bookIds.stream()
                .map(bookService::get)
                .collect(Collectors.toList());
        BookList bookList = BookList.builder()
                .id(id)
                .user(userService.get(list.getUserId()))
                .name(list.getName())
                .description(list.getDescription())
                .books(books)
                .build();
        bookListRepository.save(bookList);
        return toBookListResponse(bookList);
    }

    @Override
    public void delete(String id) {
        bookListRepository.deleteById(id);
    }

    private BookListResponse toBookListResponse(BookList list) {
        return BookListResponse.builder()
                .name(list.getName())
                .description(list.getDescription())
                .books(bookService.getAllShortResponse(list.getId()))
                .build();
    }
}

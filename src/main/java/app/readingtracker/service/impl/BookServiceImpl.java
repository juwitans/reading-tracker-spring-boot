package app.readingtracker.service.impl;

import app.readingtracker.entity.Book;
import app.readingtracker.model.response.BookResponse;
import app.readingtracker.model.response.BookShortDetailResponse;
import app.readingtracker.repository.BookRepository;
import app.readingtracker.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book create(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookShortDetailResponse> searchAuthorOrTitle(String search) {
        List<Book> bookList = bookRepository.findByAuthorOrTitleLike(search);
        return bookList.stream()
                .map(book -> BookShortDetailResponse.builder()
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Book get(String id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book update(Book book) {
        if (book.getId().isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found");
        return bookRepository.save(book);
    }

    @Override
    public List<BookShortDetailResponse> getAllShortResponse(String listId) {
        return bookRepository.getShortDescription(listId);
    }
}

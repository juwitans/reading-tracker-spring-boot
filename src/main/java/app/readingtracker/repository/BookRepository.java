package app.readingtracker.repository;

import app.readingtracker.entity.Book;
import app.readingtracker.model.response.BookShortDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query(value = "SELECT * FROM book b WHERE b.title LIKE %?1% OR b.author LIKE %?1%", nativeQuery = true)
    List<Book> findByAuthorOrTitleLike(String title);

    @Query(value = "SELECT b.title, b.author, b.description FROM book b JOIN book_list l ON b.id = l.book_id WHERE l.id = ?1", nativeQuery = true)
    List<BookShortDetailResponse> getShortDescription(String listId);
}

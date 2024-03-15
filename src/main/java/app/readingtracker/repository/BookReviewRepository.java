package app.readingtracker.repository;

import app.readingtracker.entity.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, String> {
    @Query(value = "SELECT * FROM book b WHERE b.user_id = ?1", nativeQuery = true)
    List<BookReview> findAllByUserId(String id);
}

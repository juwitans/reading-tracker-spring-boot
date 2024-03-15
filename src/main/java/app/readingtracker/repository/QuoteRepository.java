package app.readingtracker.repository;

import app.readingtracker.entity.Quote;
import app.readingtracker.model.response.QuoteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {
    @Query(value = "SELECT * FROM quote q WHERE q.tracker_id = ?1", nativeQuery = true)
    List<Quote> findAllByTrackerId(String id);
    @Query(value = "SELECT b.title, b.author, q.quote FROM quote q JOIN tracker t ON q.tracker_id = t.id " +
            "JOIN book b ON t.book_id = b.id WHERE q.user_id = ?1", nativeQuery = true)
    List<QuoteResponse> findAllByUserId(String id);
}

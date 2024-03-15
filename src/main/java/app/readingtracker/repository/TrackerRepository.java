package app.readingtracker.repository;

import app.readingtracker.entity.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM tracker t where t.user_id = ?1")
    List<Tracker> findAllByUserId(String id);
}

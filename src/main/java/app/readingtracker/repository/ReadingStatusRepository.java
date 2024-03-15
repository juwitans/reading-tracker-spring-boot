package app.readingtracker.repository;

import app.readingtracker.constant.EReadingStatus;
import app.readingtracker.entity.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadingStatusRepository extends JpaRepository<ReadingStatus, String> {
    Optional<ReadingStatus> findByStatus(EReadingStatus status);
}

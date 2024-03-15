package app.readingtracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tracker")
public class Tracker {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
    private Duration timeSpent;
    private Integer progress;
    private String notes;
    @OneToMany
    @JsonManagedReference
    private List<Quote> quotes;
    @OneToOne
    @JoinColumn(name = "reading_status_id")
    private ReadingStatus status;
}

package app.readingtracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<BookReview> bookReviews;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Quote> quotes;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<BookList> bookLists;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Tracker> trackers;
}
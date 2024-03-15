package app.readingtracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String author;
    private Long isbn;
    private String publisher;
    private String description;
    @Column(columnDefinition = "INT CHECK (pages >= 1)")
    private Integer pages;
    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    private List<BookList> bookLists;
}

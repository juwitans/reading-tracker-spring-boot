package app.readingtracker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListResponse {
    private String name;
    private String description;
    private List<BookShortDetailResponse> books;
}

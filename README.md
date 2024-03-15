## Technologies
This project using Spring Boot 3.2.3 and Java 17

## API Overview
The Reading Tracker API provides endpoints for managing book records, reading trackers, quotes, book lists, and book reviews. It allows users to perform CRUD operations on these entities to track their reading progress and manage their reading lists.

### Some Endpoints:

Users
* `POST /api/users`: To create user account
* `GET /api/users/{id}`: To get user detail of an account
* `PUT /api/users/{id}`: To update user detail of an account
* `DELETE /api/users/{id}`: To delete user detail of an account

Books
* `POST /api/books`: To create new book record
* `GET /api/books`: To get all book records
* `GET /api/books/search`: To search for specific books
* `GET /api/books/{id}`: To get book record by id
* `PUT /api/books/{id}`: To edit book record
* `DELETE /api/books/{id}`: To delete book record

Trackers
* `POST /api/trackers`: To create new reading trackers
* `GET /api/trackers`: To get all reading trackers
* `GET /api/trackers/{id}`: To delete reading tracker
* `PUT /api/trackers/{id}`: To update reading tracker
* `DELETE /api/trackers/{id}`: To delete reading tracker
* `GET /api/trackers/:{userId}`: To get all reading trackers by userId

Quotes `/api/quotes` has POST PUT GET DELETE mapping

Review `/api/reviews` has POST PUT GET DELETE mapping

Book List `/api/booklist` has POST PUT GET DELETE mapping

Error Handling:
The API returns appropriate HTTP status codes and error messages in case of invalid requests or server errors.

## Native Queries Implementation
This API implements native query to customize finding result in repositories. Some example of them are:
<pre>
    @Query(value = "SELECT * FROM book b WHERE b.title LIKE %?1% OR b.author LIKE %?1%", nativeQuery = true)
    List<Book> findByAuthorOrTitleLike(String title);

    @Query(value = "SELECT b.title, b.author, b.description FROM book b JOIN book_list l ON b.id = l.book_id WHERE l.id = ?1", nativeQuery = true)
    List<BookShortDetailResponse> getShortDescription(String listId);
</pre>

## Java Stream
This API also using Java Stream for transform data using map, collect, forEach, filter method. Here are some of them:
<pre>
      public List<BookShortDetailResponse> searchAuthorOrTitle(String search) {
        List<Book> bookList = bookRepository.findByAuthorOrTitleLike(search);
        return bookList.stream()
                .map(book -> BookShortDetailResponse.builder()
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .build())
                .collect(Collectors.toList());
    }

      public TrackerResponse update(UpdateTrackerRequest request) {
        Optional<Tracker> optionalTracker = trackerRepository.findById(request.getId());
        if (optionalTracker.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tracker not found");
        request.getQuotes().stream()
                .forEach(quote -> quoteService.createOrUpdate(quote));
        //other codes
    }

        trackers.stream()
                    .filter(tracker -> tracker.getStatus() == readingStatusService.getOrSave(request.getReadingStatus()))
                    .map(tracker -> toTrackerResponse(tracker))
                    .collect(Collectors.toList());
</pre>

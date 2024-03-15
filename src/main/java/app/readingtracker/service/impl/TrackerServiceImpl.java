package app.readingtracker.service.impl;

import app.readingtracker.constant.EReadingStatus;
import app.readingtracker.entity.Book;
import app.readingtracker.entity.ReadingStatus;
import app.readingtracker.entity.Tracker;
import app.readingtracker.entity.User;
import app.readingtracker.model.request.NewTrackerRequest;
import app.readingtracker.model.request.SearchTrackerRequest;
import app.readingtracker.model.request.UpdateTrackerRequest;
import app.readingtracker.model.response.QuoteResponse;
import app.readingtracker.model.response.TrackerResponse;
import app.readingtracker.repository.TrackerRepository;
import app.readingtracker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackerServiceImpl implements TrackerService {
    private final TrackerRepository trackerRepository;
    private final BookService bookService;
    private final UserService userService;
    private final QuoteService quoteService;
    private final ReadingStatusService readingStatusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TrackerResponse create(NewTrackerRequest request) {
        Book book = bookService.get(request.getBookId());
        User user = userService.get(request.getUserId());
        Duration duration = Duration.ofHours(0).plusMinutes(0).plusSeconds(0);
        Integer progress = 0;
        ReadingStatus readingStatus = readingStatusService.getOrSave(EReadingStatus.TO_READ);
        Tracker tracker = Tracker.builder()
                .user(user)
                .book(book)
                .timeSpent(duration)
                .progress(progress)
                .status(readingStatus)
                .build();

        trackerRepository.saveAndFlush(tracker);

        return toTrackerResponse(tracker);
    }

    @Override
    public List<TrackerResponse> getAll() {
        List<Tracker> trackers = trackerRepository.findAll();
        return trackers.stream()
                .map(TrackerServiceImpl::toTrackerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackerResponse> getAllByUserId(SearchTrackerRequest request) {
        List<Tracker> trackers = trackerRepository.findAllByUserId(request.getUserId());
        if (request.getReadingStatus() == null) {
            return trackers.stream()
                    .map(tracker -> toTrackerResponse(tracker))
                    .collect(Collectors.toList());
        } else {
            return trackers.stream()
                    .filter(tracker -> tracker.getStatus() == readingStatusService.getOrSave(request.getReadingStatus()))
                    .map(tracker -> toTrackerResponse(tracker))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Tracker get(String id) {
        return trackerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tracker not found"));
    }

    @Override
    public TrackerResponse update(UpdateTrackerRequest request) {
        Optional<Tracker> optionalTracker = trackerRepository.findById(request.getId());
        if (optionalTracker.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tracker not found");
        request.getQuotes().stream()
                .forEach(quote -> quoteService.createOrUpdate(quote));
        Tracker tracker = Tracker.builder()
                .id(request.getId())
                .book(bookService.get(request.getBookId()))
                .user(userService.get(request.getUserId()))
                .timeSpent(request.getTimeSpent())
                .progress(request.getProgress())
                .quotes(request.getQuotes())
                .notes(request.getNotes())
                .status(request.getStatus())
                .build();
        trackerRepository.save(tracker);
        return toTrackerResponse(tracker);
    }

    @Override
    public void delete(String id) {
        trackerRepository.deleteById(id);
    }

    private static TrackerResponse toTrackerResponse(Tracker tracker) {
        List<String> quotes = tracker.getQuotes().stream().map(quote -> quote.getQuote())
                .collect(Collectors.toList());
        return TrackerResponse.builder()
                .bookTitle(tracker.getBook().getTitle())
                .bookAuthor(tracker.getBook().getAuthor())
                .bookDescription(tracker.getBook().getDescription())
                .progress(tracker.getProgress())
                .timeSpent(tracker.getTimeSpent())
                .notes(tracker.getNotes())
                .quotes(quotes)
                .status(tracker.getStatus())
                .build();
    }
}

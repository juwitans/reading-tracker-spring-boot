package app.readingtracker.controller;

import app.readingtracker.constant.EReadingStatus;
import app.readingtracker.entity.Tracker;
import app.readingtracker.model.request.NewTrackerRequest;
import app.readingtracker.model.request.SearchTrackerRequest;
import app.readingtracker.model.request.UpdateTrackerRequest;
import app.readingtracker.model.response.TrackerResponse;
import app.readingtracker.model.response.WebResponse;
import app.readingtracker.service.TrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/tracker")
public class TrackerController {
    private final TrackerService trackerService;

    @PostMapping
    public ResponseEntity<?> createTracker(@RequestBody NewTrackerRequest request) {
        TrackerResponse tracker = trackerService.create(request);
        WebResponse<TrackerResponse> response = WebResponse.<TrackerResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create tracker")
                .data(tracker)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getReadingTracker(@PathVariable String id) {
        Tracker tracker = trackerService.get(id);
        WebResponse<Tracker> response = WebResponse.<Tracker>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get tracker")
                .data(tracker)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TrackerResponse> readingTrackers = trackerService.getAll();
        WebResponse<List<TrackerResponse>> response = WebResponse.<List<TrackerResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get trackers")
                .data(readingTrackers)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/:{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable String id,
                                            @RequestParam(required = false) EReadingStatus status) {
        List<TrackerResponse> trackers = trackerService.getAllByUserId(SearchTrackerRequest.builder()
                .userId(id).readingStatus(status).build());

        WebResponse<List<TrackerResponse>> response = WebResponse.<List<TrackerResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get trackers")
                .data(trackers)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTracker(@RequestBody UpdateTrackerRequest request) {
        TrackerResponse tracker = trackerService.update(request);
        WebResponse<TrackerResponse> response = WebResponse.<TrackerResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully update tracker")
                .data(tracker)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTracker(@PathVariable String id) {
        trackerService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully delete tracker")
                .data("deleted")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

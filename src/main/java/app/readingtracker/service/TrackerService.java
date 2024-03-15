package app.readingtracker.service;

import app.readingtracker.entity.Tracker;
import app.readingtracker.model.request.NewTrackerRequest;
import app.readingtracker.model.request.SearchTrackerRequest;
import app.readingtracker.model.request.UpdateTrackerRequest;
import app.readingtracker.model.response.TrackerResponse;

import java.util.List;

public interface TrackerService {
    TrackerResponse create(NewTrackerRequest request);
    List<TrackerResponse> getAll();
    List<TrackerResponse> getAllByUserId(SearchTrackerRequest request);
    Tracker get(String id);
    TrackerResponse update(UpdateTrackerRequest tracker);
    void delete(String id);
}

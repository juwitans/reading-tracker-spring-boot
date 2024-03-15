package app.readingtracker.service;

import app.readingtracker.entity.Quote;
import app.readingtracker.model.request.QuoteRequest;
import app.readingtracker.model.response.QuoteResponse;

import java.util.List;

public interface QuoteService {
    Quote createOrUpdate(Quote quote);
    Quote get(String id);
    List<QuoteResponse> getAllByTrackerId(String trackerId);
    List<QuoteResponse> getAllByUserId(String userId);
    void delete(String id);
}

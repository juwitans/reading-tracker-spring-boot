package app.readingtracker.service.impl;

import app.readingtracker.entity.Quote;
import app.readingtracker.model.request.QuoteRequest;
import app.readingtracker.model.response.QuoteResponse;
import app.readingtracker.repository.QuoteRepository;
import app.readingtracker.service.BookService;
import app.readingtracker.service.QuoteService;
import app.readingtracker.service.TrackerService;
import app.readingtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    @Override
    public Quote createOrUpdate(Quote quote) {
        return quoteRepository.saveAndFlush(quote);
    }

    @Override
    public Quote get(String id) {
        return quoteRepository.findById(id).orElseThrow();
    }

    @Override
    public List<QuoteResponse> getAllByTrackerId(String trackerId) {
        List<Quote> quoteList = quoteRepository.findAllByTrackerId(trackerId);
        return quoteList.stream().map(quote -> QuoteResponse.builder()
                        .quote(quote.getQuote())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<QuoteResponse> getAllByUserId(String userId) {
        return quoteRepository.findAllByUserId(userId);
    }

    @Override
    public void delete(String id) {
        quoteRepository.deleteById(id);
    }

}

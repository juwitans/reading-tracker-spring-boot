package app.readingtracker.controller;

import app.readingtracker.entity.Quote;
import app.readingtracker.entity.User;
import app.readingtracker.model.response.QuoteResponse;
import app.readingtracker.model.response.WebResponse;
import app.readingtracker.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping(path = "/:{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable String userId) {
        List<QuoteResponse> quoteResponseList = quoteService.getAllByUserId(userId);
        WebResponse<List<QuoteResponse>> response = WebResponse.<List<QuoteResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get quotes by user id")
                .data(quoteResponseList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getQuote(@PathVariable String id) {
        Quote quote = quoteService.get(id);
        WebResponse<Quote> response = WebResponse.<Quote>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get quote by id")
                .data(quote)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable String id) {
        quoteService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully delete quote")
                .data("deleted")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

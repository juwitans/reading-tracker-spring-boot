package app.readingtracker.service.impl;

import app.readingtracker.constant.EReadingStatus;
import app.readingtracker.entity.ReadingStatus;
import app.readingtracker.repository.ReadingStatusRepository;
import app.readingtracker.service.ReadingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadingStatusImpl implements ReadingStatusService {
    private final ReadingStatusRepository readingStatusRepository;
    @Override
    public ReadingStatus getOrSave(EReadingStatus readingStatus) {
        Optional<ReadingStatus> optional = readingStatusRepository.findByStatus(readingStatus);
        if (optional.isPresent()) return optional.get();

        ReadingStatus status = ReadingStatus.builder()
                .status(readingStatus)
                .build();
        readingStatusRepository.saveAndFlush(status);
        return status;
    }
}

package app.readingtracker.service;

import app.readingtracker.constant.EReadingStatus;
import app.readingtracker.entity.ReadingStatus;

public interface ReadingStatusService {
    ReadingStatus getOrSave(EReadingStatus readingStatus);
}

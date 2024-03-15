package app.readingtracker.service;

import app.readingtracker.entity.User;

public interface UserService {
    User create(User user);
    User update(User user);
    void delete(String id);
    User get(String id);
    User getByEmail(String email);
}

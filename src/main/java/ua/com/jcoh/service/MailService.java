package ua.com.jcoh.service;

import ua.com.jcoh.entity.User;

public interface MailService {
    void send(User user);
}

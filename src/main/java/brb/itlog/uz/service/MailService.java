package brb.itlog.uz.service;

import brb.itlog.uz.model.dto.email.EmailSendRequestDTO;

public interface MailService {
    String send(EmailSendRequestDTO request);
}

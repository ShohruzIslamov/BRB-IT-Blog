package brb.itlog.uz.service;

import brb.itlog.uz.model.dto.newsletter.CreateNewsLetterRequestDTO;
import brb.itlog.uz.model.dto.newsletter.UpdateNewsLetterRequestDTO;

public interface NewsLetterService {

    String createNewsLetter(CreateNewsLetterRequestDTO request);

    String updateNewsLetter(Long id,UpdateNewsLetterRequestDTO request);
}

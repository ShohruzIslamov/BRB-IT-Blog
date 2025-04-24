package brb.itlog.uz.service.impl;

import brb.itlog.uz.exception.AppBadException;
import brb.itlog.uz.model.dto.newsletter.CreateNewsLetterRequestDTO;
import brb.itlog.uz.model.dto.newsletter.UpdateNewsLetterRequestDTO;
import brb.itlog.uz.model.entity.newsletter.Newsletters;
import brb.itlog.uz.model.mapper.NewsLetterMapper;
import brb.itlog.uz.repository.NewsLetterRepository;
import brb.itlog.uz.service.NewsLetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsLetterServiceImpl implements NewsLetterService {

    private final NewsLetterRepository newsLetterRepository;
    private final NewsLetterMapper newsLetterMapper;

    @Override
    public String createNewsLetter(CreateNewsLetterRequestDTO request) {

        List<Newsletters> newsletters = newsLetterMapper.toEntityCreate(request.getNewsletters());

        for (Newsletters newsletter : newsletters) {
            newsLetterRepository.save(newsletter);
        }

        log.info("Newsletter created");
        return "News letter created";
    }

    @Override
    public String updateNewsLetter(Long id, UpdateNewsLetterRequestDTO request) {

        //ToDo check new sender_emal

        newsLetterRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("NewsLetter not found with ID: {}", id);
                    return new AppBadException("NewsLetter not found with ID: " + id);
                });

        List<Newsletters> newsletters = newsLetterMapper.toEntityUpdate(request.getNewsletters());

        for (Newsletters newsletter : newsletters) {
            newsLetterRepository.save(newsletter);
        }

        log.info("Newsletter updated");
        return "Newsletter succesfully updated";
    }

}

package brb.itlog.uz.util;

import brb.itlog.uz.model.PostStatus;
import brb.itlog.uz.model.entity.post.Post;
import brb.itlog.uz.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class ScheduledUtil {

    @Autowired
    private PostRepository postRepository;

    @Scheduled(fixedRate = 60000) // every 60 sekund
    public void checkAndUpdatePosts() {

        LocalDateTime now = LocalDateTime.now();
        List<Post> scheduledPosts = postRepository.findByStatus(PostStatus.SCHEDULED);

        for (Post post : scheduledPosts) {
            if (post.getPublishedAt().isBefore(now) || post.getPublishedAt().isEqual(now)) {
                post.setStatus(PostStatus.PUBLISHED);
                postRepository.save(post);
                log.info("Post {} status updated to PUBLISHED", post.getId());
            }
        }
    }
}

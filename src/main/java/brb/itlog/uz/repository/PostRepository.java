package brb.itlog.uz.repository;

import brb.itlog.uz.model.PostStatus;
import brb.itlog.uz.model.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsBySlug(String newSlug);
    
    List<Post> findByStatus(PostStatus postStatus);
}

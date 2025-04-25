package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsBySlug(String newSlug);
}

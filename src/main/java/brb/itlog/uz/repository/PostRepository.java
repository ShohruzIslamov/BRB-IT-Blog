package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    boolean existsBySlug(String newSlug);
}

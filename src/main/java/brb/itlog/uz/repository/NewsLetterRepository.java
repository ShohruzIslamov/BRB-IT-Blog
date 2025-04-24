package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.newsletter.Newsletters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLetterRepository extends JpaRepository<Newsletters, Long> {
}

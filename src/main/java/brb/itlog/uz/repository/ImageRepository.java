package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}

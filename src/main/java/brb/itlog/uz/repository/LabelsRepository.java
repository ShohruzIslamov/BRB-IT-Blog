package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.label.Labels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelsRepository extends JpaRepository<Labels, Long> {
    Optional<Labels> findByName(String name);
}

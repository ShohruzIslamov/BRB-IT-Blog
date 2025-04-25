package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.label.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LabelsRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByName(String name);
}

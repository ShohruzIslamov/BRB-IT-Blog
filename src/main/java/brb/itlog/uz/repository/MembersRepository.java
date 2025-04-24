package brb.itlog.uz.repository;

import brb.itlog.uz.model.entity.member.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {


}

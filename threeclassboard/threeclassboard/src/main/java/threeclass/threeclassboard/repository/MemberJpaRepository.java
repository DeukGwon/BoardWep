package threeclass.threeclassboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import threeclass.threeclassboard.domain.Member;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {
}

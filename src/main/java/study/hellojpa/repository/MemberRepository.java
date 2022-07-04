package study.hellojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.hellojpa.domain.Member;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    List<Member> findByName(String name);

}

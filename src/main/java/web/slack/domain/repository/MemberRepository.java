package web.slack.domain.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {

    Optional<Member> findByEmail(String email);

}

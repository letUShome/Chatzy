package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Member;

public interface MemberInviteRepository extends MongoRepository<Member, String> {
}

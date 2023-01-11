package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Profile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {

    Optional<Profile> findProfileByMemberIdAndAndWorkspaceId(String member, String workspace);
    Optional<Profile> findById(String id);

    List<Map<String, String>> findByMemberId(String memberId);
}

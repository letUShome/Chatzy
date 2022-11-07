package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Channel;
import web.slack.domain.entity.Member;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends MongoRepository<Channel, String> {
    List<Channel> findChannelsByWorkspaceId(String workspaceId);
    void deleteChannelsByWorkspaceId(String workspaceId);
}

package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Chatroom;

import java.util.List;

public interface ChatroomRepository extends MongoRepository<Chatroom, String> {
    List<Chatroom> findChannelsByWorkspaceId(String workspaceId);
    void deleteChannelsByWorkspaceId(String workspaceId);
}

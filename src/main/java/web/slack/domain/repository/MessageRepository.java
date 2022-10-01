package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Message;

import java.util.List;


public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAllByChannel(String ChannelId);
}

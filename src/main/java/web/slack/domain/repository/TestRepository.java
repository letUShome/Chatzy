package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Test;


public interface TestRepository extends MongoRepository<Test, String> {
}

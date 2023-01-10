package web.slack.domain.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.GoogleCode;

public interface GoogleCodeRepository extends MongoRepository<GoogleCode, String> {
}

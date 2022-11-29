package web.slack.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.slack.domain.entity.Workspace;

import java.util.Optional;

public interface WorkspaceRepository extends MongoRepository<Workspace, String> {
    Optional<Workspace> findById(String id);
}



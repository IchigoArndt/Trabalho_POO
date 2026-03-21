package com.trabalho.projetofaculdade.repository.Log;

import com.trabalho.projetofaculdade.model.ActionLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionLogRepository extends MongoRepository<ActionLog, String> {
}

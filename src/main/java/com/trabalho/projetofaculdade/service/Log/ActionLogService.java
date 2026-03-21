package com.trabalho.projetofaculdade.service.Log;

import com.trabalho.projetofaculdade.model.ActionLog;
import com.trabalho.projetofaculdade.repository.Log.ActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActionLogService {

    private final ActionLogRepository actionLogRepository;

    public void log(String entity, String action, String description) {
        ActionLog actionLog = ActionLog.builder()
                .entity(entity)
                .action(action)
                .description(description)
                .timestamp(LocalDateTime.now())
                .build();

        actionLogRepository.save(actionLog);
    }
}

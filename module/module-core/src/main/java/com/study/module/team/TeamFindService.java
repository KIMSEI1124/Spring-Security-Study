package com.study.module.team;

import com.study.module.global.service.EntityFindable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TeamFindService implements EntityFindable<Team> {
    private final TeamRepository repository;

    @Override
    public Team findById(int id) {
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}

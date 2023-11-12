package com.core.team;

import com.core.global.service.EntityFindable;
import com.data.team.Team;
import com.data.team.TeamRepository;
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

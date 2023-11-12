package com.core.team;

import com.data.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
}

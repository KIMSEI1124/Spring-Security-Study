package com.study.module.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
}

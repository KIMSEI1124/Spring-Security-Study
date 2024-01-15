package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.domain.UserRepository;
import com.devsei.userservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.generatedUserId();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserJpaEntity userJpaEntity = mapper.map(userDto, UserJpaEntity.class);
        userJpaEntity.setEncryptedPassword(encoder.encode(userDto.getPassword()));

        UserJpaEntity save = userRepository.save(userJpaEntity);
        return mapper.map(save, UserDto.class);
    }
}

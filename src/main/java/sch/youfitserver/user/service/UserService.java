package sch.youfitserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.youfitserver.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
}

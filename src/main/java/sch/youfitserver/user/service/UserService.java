package sch.youfitserver.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.youfitserver.kakaoOauth.dto.KakaoUserInfoResponseDto;
import sch.youfitserver.user.dto.UserRequestDto;
import sch.youfitserver.user.entity.User;
import sch.youfitserver.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long kakaosave(KakaoUserInfoResponseDto response){
        return userRepository.save(User.builder()
                .nickname(response.getKakaoAccount().getProfile().getNickname())
                .email(response.getKakaoAccount().getEmail())
                .build()).getUserId();
    }

    public User findByEmail(String email){
            return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public User update(Long userId, UserRequestDto request){
        User user = userRepository.findById(userId).orElse(null);
        user.update(request.toEntity().getDateOfBirth(), request.toEntity().getFull_name(),
                    request.toEntity().getGender(), request.getProfileImg());
        return user;
    }
    public User findById(Long userId){
        if(userId == null) {
            return null;
        }
        return userRepository.findById(userId)
                .orElse(null);
    }
}

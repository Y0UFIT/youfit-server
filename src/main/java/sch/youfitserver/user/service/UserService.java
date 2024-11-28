package sch.youfitserver.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.youfitserver.kakaoOauth.dto.KakaoUserInfoResponseDto;
import sch.youfitserver.kakaoOauth.dto.UserRequestDto;
import sch.youfitserver.user.entity.User;
import sch.youfitserver.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long kakaosave(KakaoUserInfoResponseDto dto){
        if(dto.getKakaoAccount().getEmail() == null || dto.getKakaoAccount().getProfile().getNickname() == null){
            throw new IllegalArgumentException("프로필에 정보가 없습니다.");
        }
        return userRepository.save(User.builder()
                .nickname(dto.getKakaoAccount().getProfile().getNickname())
                .email(dto.getKakaoAccount().getEmail())
                .build()).getUserId();
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("정보를 찾을 수 없습니다."));
    }
    @Transactional
    public User update(Long userId, UserRequestDto request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("정보를 찾을 수 없습니다."));
        user.update(request.toEntity().getDateOfBirth(), request.toEntity().getFull_name(), request.toEntity().getGender());
        return user;
    }
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다"));
    }
}

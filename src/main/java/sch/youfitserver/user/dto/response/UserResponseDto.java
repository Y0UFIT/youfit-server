package sch.youfitserver.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.youfitserver.user.entity.User;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String profileImg;
    private String dateOfBirth;
    private String email;
    private String full_name;
    private String gender;
    private String nickname;

    public UserResponseDto(User user) {
        this.profileImg = user.getProfileImg();
        this.dateOfBirth = user.getDateOfBirth();
        this.full_name = user.getFull_name();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}

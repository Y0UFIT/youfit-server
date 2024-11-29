package sch.youfitserver.user.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;
import sch.youfitserver.user.entity.User;

@Getter
@Service
public class UserRequestDto {

    private String profileImg;
    private String dateOfBirth;
    private String full_name;
    private String gender;

    public User toEntity(){
        return User.builder()
                .dateOfBirth(dateOfBirth)
                .full_name(full_name)
                .gender(gender)
                .profileImg(profileImg)
                .build();
    }
}

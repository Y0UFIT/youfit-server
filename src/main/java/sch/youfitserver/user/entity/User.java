package sch.youfitserver.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Builder
    public User(String email, String nickname, String full_name, String gender, String dateOfBirth) {
        this.email = email;
        this.nickname = nickname;
        this.full_name = full_name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    public void update(String dateOfBirth, String full_name, String gender) {
        this.dateOfBirth = dateOfBirth;
        this.full_name = full_name;
        this.gender = gender;
    }
}

package sch.youfitserver.user.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "full_name", nullable = false, unique = true)
    private String full_name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "dateOfBirth", nullable = false)
    private String dateOfBirth;

    @Builder
    public User(String email, String nickname, String full_name, String gender, String dateOfBirth) {
        this.email = email;
        this.nickname = nickname;
        this.full_name = full_name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
}

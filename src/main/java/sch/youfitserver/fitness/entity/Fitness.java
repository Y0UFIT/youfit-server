package sch.youfitserver.fitness.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sch.youfitserver.user.entity.User;

@Entity(name = "fitness")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fitness {

    @Id
    @Column(name = "fitness_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fitnessId;

    private String date;
    private String run20;
    private String treadmilStep;
    private String gripStrength;
    private String sitUp;
    private String bendForward;
    private String run10;
    private String reaction;
    private String longJump;
    private String flightTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


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

    @Column(name = "date")
    private String date;
    @Column(name = "run_20")
    private String run20;
    @Column(name = "treadmil_step")
    private String treadmilStep;
    @Column(name = "grip_strength")
    private String gripStrength;
    @Column(name = "sit_up")
    private String sitUp;
    @Column(name = "bend_forward")
    private String bendForward;
    @Column(name = "run_10")
    private String run10;
    @Column(name = "reaction")
    private String reaction;
    @Column(name = "long_jump")
    private String longJump;
    @Column(name = "flight_time")
    private String flightTime;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


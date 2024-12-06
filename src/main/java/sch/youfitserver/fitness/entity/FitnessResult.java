package sch.youfitserver.fitness.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "fitness_result")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FitnessResult {

    @Id
    @Column(name = "fitness_result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fitnessResultId;

    private String percent;
    private String cardio;
    private String muscularStrength;
    private String muscularEndurance;
    private String flexibility;
    private String agility;
    private String power;
    private String changeChart;

    @ManyToOne
    @JoinColumn(name = "fitness_id", nullable = false)
    private Fitness fitness;
}

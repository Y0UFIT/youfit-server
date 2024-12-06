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

    @Column(name = "percent")
    private String percent;
    @Column(name = "cardio")
    private String cardio;
    @Column(name = "muscular_strength")
    private String muscularStrength;
    @Column(name = "muscular_endurance")
    private String muscularEndurance;
    @Column(name = "flexibility")
    private String flexibility;
    @Column(name = "agility")
    private String agility;
    @Column(name = "power")
    private String power;
    @Column(name = "change_Chart")
    private String changeChart;

    @ManyToOne
    @JoinColumn(name = "fitness_id", nullable = false)
    private Fitness fitness;
}

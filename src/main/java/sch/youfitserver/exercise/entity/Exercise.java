package sch.youfitserver.exercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sch.youfitserver.fitness.entity.FitnessResult;

@Entity(name = "exercise")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise {

    @Id
    @Column(name = "exercise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "exercise_url", nullable = false)
    private String exerciseUrl;

    @ManyToOne
    @JoinColumn(name = "fitness_result_id")
    private FitnessResult fitnessResult;
}

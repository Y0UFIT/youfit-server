package sch.youfitserver.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.youfitserver.exercise.entity.Exercise;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByFitnessResult_fitnessResultId(Long fitnessResultId);
}

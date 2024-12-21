package sch.youfitserver.fitness.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.youfitserver.exercise.dto.ExerciseDto;
import sch.youfitserver.exercise.entity.Exercise;
import sch.youfitserver.exercise.repository.ExerciseRepository;
import sch.youfitserver.fitness.dto.response.FitnessResponseDto;
import sch.youfitserver.fitness.entity.FitnessResult;
import sch.youfitserver.fitness.repository.FitnessResultRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FitnessService {

    private final FitnessResultRepository fitnessResultRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public FitnessResponseDto getFitnessResult(Long userId, Long fitnessId) {

        FitnessResult fitnessResult = fitnessResultRepository.findByFitness_fitnessId(fitnessId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 체력분석 결과를 찾을 수 없습니다."));

        List<Exercise> exercises = exerciseRepository.findByFitnessResult_fitnessResultId(fitnessResult.getFitnessResultId());

        return FitnessResponseDto.builder()
                .userId(userId)
                .fitnessId(fitnessId)
                .cardio(fitnessResult.getCardio())
                .muscularStrength(fitnessResult.getMuscularStrength())
                .muscularEndurance(fitnessResult.getMuscularEndurance())
                .flexibility(fitnessResult.getFlexibility())
                .agility(fitnessResult.getAgility())
                .power(fitnessResult.getPower())
                .exercises(exercises.stream().map(this::convertToExerciseDto).collect(Collectors.toList()))
                .build();
    }

    private ExerciseDto convertToExerciseDto(Exercise exercise) {

        //Exercise -> ExerciseDto 변환
        return ExerciseDto.builder()
                .exerciseName(exercise.getExerciseName())
                .exerciseUrl(exercise.getExerciseUrl())
                .build();
    }
}

package sch.youfitserver.fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sch.youfitserver.exercise.dto.ExerciseDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FitnessResponseDto {

    private Long userId;
    private Long fitnessId;

    // 결과 그래프에 대한 정보
    private String cardio;
    private String muscularStrength;
    private String muscularEndurance;
    private String flexibility;
    private String agility;
    private String power;
    private List<ExerciseDto> exercises;
}

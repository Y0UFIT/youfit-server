package sch.youfitserver.fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FitnessResponseDto {

    private Long userId;
    private Long fitnessId;
}

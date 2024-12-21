package sch.youfitserver.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sch.youfitserver.fitness.dto.response.FitnessResponseDto;
import sch.youfitserver.fitness.service.FitnessService;

@RestController
@RequiredArgsConstructor
public class FitnessController {

    private final FitnessService fitnessService;

    // 체력 분석 결과 상세 보여주기
    @GetMapping("/fitness/{userId}/{fitnessId}")
    public ResponseEntity<FitnessResponseDto> getFitnessResult(@PathVariable Long userId, @PathVariable Long fitnessId) {

        FitnessResponseDto result = fitnessService.getFitnessResult(userId, fitnessId);
        return ResponseEntity.ok(result);
    }
}

package sch.youfitserver.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sch.youfitserver.fitness.dto.response.FitnessResponseDto;
import sch.youfitserver.fitness.service.FitnessService;

@RestController
@RequiredArgsConstructor
public class FitnessController {

    private final FitnessService fitnessService;

    @GetMapping("/fitness/{userId}/{fitnessId}")
    public FitnessResponseDto getFitness(@PathVariable String userId, @PathVariable String fitnessId) {
        return new FitnessResponseDto();
    }
}

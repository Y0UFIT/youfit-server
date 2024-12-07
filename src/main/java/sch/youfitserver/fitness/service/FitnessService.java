package sch.youfitserver.fitness.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.youfitserver.fitness.repository.FitnessRepository;
import sch.youfitserver.fitness.repository.FitnessResultRepository;

@Service
@RequiredArgsConstructor
public class FitnessService {

    private final FitnessRepository fitnessRepository;
    private final FitnessResultRepository fitnessResultRepository;
}

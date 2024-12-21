package sch.youfitserver.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.youfitserver.fitness.entity.FitnessResult;

import java.util.List;

@Repository
public interface FitnessResultRepository extends JpaRepository<FitnessResult, Long> {

    Optional<FitnessResult> findByFitness_fitnessId(Long fitnessId);
}
package sch.youfitserver.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.youfitserver.fitness.entity.FitnessResult;

@Repository
public interface FitnessResultRepository extends JpaRepository<FitnessResult, Long> {
}

package sch.youfitserver.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.youfitserver.fitness.entity.Fitness;

import java.util.Optional;

@Repository
public interface FitnessRepository extends JpaRepository<Fitness, Long> {
        Optional<Fitness> findByUser_UserId(Long userId);
}

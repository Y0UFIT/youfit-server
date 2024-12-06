package sch.youfitserver.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.youfitserver.center.entity.Center;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
}

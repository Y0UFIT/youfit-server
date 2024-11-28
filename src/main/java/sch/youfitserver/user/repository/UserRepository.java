package sch.youfitserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.youfitserver.user.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

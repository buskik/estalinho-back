package Estalinho.estalinho.repository;

import Estalinho.estalinho.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

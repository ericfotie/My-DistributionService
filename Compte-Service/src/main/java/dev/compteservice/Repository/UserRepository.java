package dev.compteservice.Repository;

import dev.compteservice.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel , Long> {
}

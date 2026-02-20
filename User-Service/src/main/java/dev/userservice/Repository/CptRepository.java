package dev.userservice.Repository;

import dev.userservice.Model.CptModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CptRepository extends JpaRepository<CptModel , Long > {
}

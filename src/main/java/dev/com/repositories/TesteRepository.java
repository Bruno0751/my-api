package dev.com.repositories;

import dev.com.models.TesteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<TesteModel, Long>{

//    default void saveOne(Object dto) {}
}

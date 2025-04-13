package com.example.demo.repositories;

import com.example.demo.models.TesteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<TesteModel, Long>{

//    default void saveOne(Object dto) {}
}

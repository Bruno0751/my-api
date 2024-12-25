package com.example.demo.repositories;

import com.example.demo.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

//@Component
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {

//    @Query("SELECT altura FROM ClienteModel cl")
//    public List<String> findAllAltura ();
}

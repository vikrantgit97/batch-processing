package com.example.repositories;


import com.example.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorsRepository extends JpaRepository<Visitors, Long> {

}
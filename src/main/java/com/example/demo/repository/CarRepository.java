package com.example.demo.repository;

import com.example.demo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByProductionYearGreaterThan(Integer year); // this approach is called hibernate derived queries

    @Query("SELECT c FROM CAR c WHERE c.productionYear = ?1")
    List<Car> anyNameIsFineHere(Integer year);
}

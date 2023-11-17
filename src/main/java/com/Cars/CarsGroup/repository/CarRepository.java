package com.Cars.CarsGroup.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cars.CarsGroup.model.CarModel;

@Repository
public interface CarRepository extends JpaRepository<CarModel,UUID>{

}

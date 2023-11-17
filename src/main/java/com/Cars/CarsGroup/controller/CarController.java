package com.Cars.CarsGroup.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Cars.CarsGroup.dtos.CarRecordDto;
import com.Cars.CarsGroup.model.CarModel;
import com.Cars.CarsGroup.repository.CarRepository;

import jakarta.validation.Valid;

@RestController
public class CarController {

	@Autowired
	CarRepository carRepository;

	@PostMapping("/cars")
	public ResponseEntity<CarModel> saveCars(@RequestBody @Valid CarRecordDto carRecordDto) {
		var carModel = new CarModel();
		BeanUtils.copyProperties(carRecordDto, carModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(carModel));
	}

	@GetMapping("/cars")
	public ResponseEntity<List<CarModel>> getAll() {
		List<CarModel> carmodels = carRepository.findAll();
		if (!carmodels.isEmpty()) {
			for (CarModel model : carmodels) {
				UUID id = model.getIdCar();
				model.add(linkTo(methodOn(CarController.class).getOneCar(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(carmodels);
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") UUID id) {
		Optional<CarModel> car0 = carRepository.findById(id);
		if (car0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(car0.get());
		}
		return ResponseEntity.status(HttpStatus.OK).body(car0.get());
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Object> updateCar(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid CarRecordDto carRecordDto) {
		Optional<CarModel> car0 = carRepository.findById(id);
		if (car0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
		}
		var carModel = car0.get();
		BeanUtils.copyProperties(carRecordDto, carModel);
		return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(carModel));
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") UUID id) {
		Optional<CarModel> car0 = carRepository.findById(id);
		if(car0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
		}
		carRepository.delete(car0.get());
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
	}
}

package com.Cars.CarsGroup.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "TB_CAR")
public class CarModel extends RepresentationModel<CarModel> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID idCar;
	private String nameCar;
	private String modelCar;
	private int yearCar;
	
	
	public UUID getIdCar() {
		return idCar;
	}
	public void setIdCar(UUID idCar) {
		this.idCar = idCar;
	}
	public String getNameCar() {
		return nameCar;
	}
	public void setNameCar(String nameCar) {
		this.nameCar = nameCar;
	}
	public String getModelCar() {
		return modelCar;
	}
	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
	public int getYearCar() {
		return yearCar;
	}
	public void setYearCar(int yearCar) {
		this.yearCar = yearCar;
	}
	
	
	
}

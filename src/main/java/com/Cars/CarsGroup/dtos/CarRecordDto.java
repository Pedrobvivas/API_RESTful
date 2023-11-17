package com.Cars.CarsGroup.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarRecordDto(@NotBlank String nameCar,@NotBlank String modelCar,@NotNull int yearCar) {

}

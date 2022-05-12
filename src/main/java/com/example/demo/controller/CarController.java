package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/create")
    public String registerCar(String model, String make, Integer productionYear) {
        Car newCar = new Car(model, make, productionYear);
        carRepository.save(newCar);
        return "Successfully added a car!";
    }

    @GetMapping("/byId")
    public String lookupCar(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return "Found " + car;
        } else {
            return "Car with ID " + id + " not found in DB";
        }
    }

    @GetMapping
    public String lookupAllCars() {
        List<Car> cars = carRepository.findAll();

        StringBuilder result = new StringBuilder();
        for (Car car : cars) {
            result.append(car.toString());
        }

        System.out.println("Marija's izmainas 21:48");

        return result.toString();
    }
}

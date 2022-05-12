package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/newcar")
    public String carForm(Model model) {
        model.addAttribute("car", new Car());
        return "newcar"; // name of the thymeleaf template
    }

    @PostMapping("/newcar")
    public String carSubmit(@ModelAttribute Car car, Model model) {
        carRepository.save(car);
        model.addAttribute("car", car);
        return "result"; // name of the thymeleaf template
    }

    @GetMapping
    public String listAllCars(Model model) {
        List<Car> allCars = carRepository.findAll();
        model.addAttribute("cars", allCars);
        return "allCars"; // name of the thymeleaf template
    }

    @GetMapping("/findCarsNewerThan")
    public String fetchCarsNewerThan(Integer year) {
        List<Car> allCars = carRepository.findByProductionYearGreaterThan(year);

        StringBuilder result = new StringBuilder();
        for (Car car : allCars) {
            if (car.getProductionYear() > year) {
                result.append(car.toString());
            }
        }

        return result.toString();
    }
}

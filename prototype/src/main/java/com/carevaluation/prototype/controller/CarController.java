package com.carevaluation.prototype.controller;


import com.carevaluation.prototype.dto.CarForm;
import com.carevaluation.prototype.neuralnetwork.NeuralNetwork;
import com.carevaluation.prototype.utility.CarOneHotEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("carForm", new CarForm());
        return "car-form";
    }

    @PostMapping("/evaluate")
    public String evaluate(@ModelAttribute CarForm carForm, Model model) {
        double[] features = CarOneHotEncoder.encode(
                carForm.getBuying(),
                carForm.getMaint(),
                carForm.getDoors(),
                carForm.getPersons(),
                carForm.getLugBoot(),
                carForm.getSafety()
        );

        int idx = NeuralNetwork.predict(features);

        String label = switch (idx) {
            case 0 -> "unacc";
            case 1 -> "acc";
            case 2 -> "good";
            case 3 -> "vgood";
            default -> "unknown";
        };

        model.addAttribute("carForm", carForm);
        model.addAttribute("predictedClass", label);
        model.addAttribute("classIndex", idx);
        return "car-form";
    }
}
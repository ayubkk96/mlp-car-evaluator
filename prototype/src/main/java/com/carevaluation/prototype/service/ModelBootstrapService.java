package com.carevaluation.prototype.service;

import com.carevaluation.prototype.neuralnetwork.NeuralNetwork;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ModelBootstrapService {

    @PostConstruct
    public void init() {
        NeuralNetwork.loadModelFromResources("model/weights.txt");
    }
}

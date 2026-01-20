package com.carevaluation.prototype.neuralnetwork;

import java.io.*;
import java.util.Scanner;

public class NeuralNetwork {
    // 16 neurons, 21 weights each

    private static final int HIDDEN_LAYER_1_SIZE = 16;
    private static final int HIDDEN_LAYER_2_SIZE = 12;
    private static final int OUTPUT_LAYER_SIZE = 4;
    private static double[][] weightsHidden1;
    private static double[] biasHidden1;
    private static double[][] weightsHidden2;
    private static double[] biasHidden2;
    private static double[][] weightsOutputLayer;
    private static double[] biasOutputLayer;

    public static double[] forwardPropagate(double[] inputVector, double[][] weightsHidden,
                                            double[] biasHidden, int hiddenLayerSize) {
        double[] hiddenOutput = new double[hiddenLayerSize];
        for (int neuron = 0; neuron < hiddenOutput.length; neuron++) {
            double sum = 0;
            for (int i = 0; i < inputVector.length; i++) {
                sum += inputVector[i] * weightsHidden[neuron][i];
            }
            sum += biasHidden[neuron];
            hiddenOutput[neuron] = sigmoid(sum); // apply activation
        }
        return hiddenOutput;
    }

    static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private static int argMax(double[] v) {
        int idx = 0;
        double best = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] > best) {
                best = v[i];
                idx = i;
            }
        }
        return idx;
    }

    public static int predict(double[] feature) {
        double[] h1 = forwardPropagate(feature, weightsHidden1, biasHidden1, HIDDEN_LAYER_1_SIZE);
        double[] h2 = forwardPropagate(h1, weightsHidden2, biasHidden2, HIDDEN_LAYER_2_SIZE);
        double[] out = forwardPropagate(h2, weightsOutputLayer, biasOutputLayer, OUTPUT_LAYER_SIZE);
        return argMax(out);
    }

    public static void loadModelFromResources(String resourcePath) {
        try (InputStream is = NeuralNetwork.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new RuntimeException("Model file not found: " + resourcePath);
            }

            try (Scanner sc = new Scanner(is)) {
                weightsHidden1 = loadMatrix(sc);
                biasHidden1    = loadVector(sc);

                weightsHidden2 = loadMatrix(sc);
                biasHidden2    = loadVector(sc);

                weightsOutputLayer = loadMatrix(sc);
                biasOutputLayer    = loadVector(sc);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load model from resources", e);
        }
    }

    private static double[][] loadMatrix(Scanner sc) {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        double[][] m = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = sc.nextDouble();
            }
        }
        return m;
    }

    private static double[] loadVector(Scanner sc) {
        int size = sc.nextInt();
        double[] v = new double[size];
        for (int i = 0; i < size; i++) {
            v[i] = sc.nextDouble();
        }
        return v;
    }
}
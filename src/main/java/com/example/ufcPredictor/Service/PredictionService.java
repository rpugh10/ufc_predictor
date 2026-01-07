package com.example.ufcPredictor.Service;

import org.springframework.stereotype.Service;

import com.example.ufcPredictor.DTO.PredictionDTO;
import com.example.ufcPredictor.DTO.PredictionResultDTO;

@Service
public class PredictionService {

    public PredictionResultDTO predict(PredictionDTO predictionDTO){
        String winner = predictionDTO.getFighterA();
        double confidence = 0.99;

        return new PredictionResultDTO(winner, confidence);
    }
}

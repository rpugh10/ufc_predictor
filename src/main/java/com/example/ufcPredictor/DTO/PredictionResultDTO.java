package com.example.ufcPredictor.DTO;

import lombok.Data;

@Data
public class PredictionResultDTO {

    private String winner;
    private double confidence;

    public PredictionResultDTO(String winner, double confidence){
        this.winner = winner;
        this.confidence = confidence;
    }
}

package com.example.ufcPredictor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResultDTO {

    private String winner;
    private double confidence;
}

package com.example.ufcPredictor.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ufcPredictor.DTO.PredictionDTO;
import com.example.ufcPredictor.DTO.PredictionResultDTO;
import com.example.ufcPredictor.Service.PredictionService;


@RestController
@RequestMapping("/predict")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService){
        this.predictionService = predictionService;
    }

    @PostMapping
    public ResponseEntity<PredictionResultDTO> predict(@RequestBody PredictionDTO dto){
        return ResponseEntity.ok(predictionService.predict(dto));
    }
    


}

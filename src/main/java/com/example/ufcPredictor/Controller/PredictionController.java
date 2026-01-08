package com.example.ufcPredictor.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ufcPredictor.DTO.PredictionRequestDTO;
import com.example.ufcPredictor.Service.PredictionService;


@RestController
@CrossOrigin(origins = {
    "http://localhost:8080",
    "http://127.0.0.1:8080"
})
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService){
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody PredictionRequestDTO request) {
        return predictionService.predict(request.getFighterA(), request.getFighterB());
    }

    @GetMapping("/fighters/search")
    public ResponseEntity<String> search(@RequestParam String q) {
        return predictionService.searchFighters(q);
    }
}

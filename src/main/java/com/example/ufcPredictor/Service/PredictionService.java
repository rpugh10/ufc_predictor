package com.example.ufcPredictor.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.ufcPredictor.DTO.PredictionResultDTO;

@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();

     public ResponseEntity<?> predict(String fighterA, String fighterB) {
        
        fighterA = fighterA.trim();
        fighterB = fighterB.trim();
        
        String url = "http://127.0.0.1:5000/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("{\"fighterA\":\"%s\",\"fighterB\":\"%s\"}",
                fighterA, fighterB);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<PredictionResultDTO> response =
                    restTemplate.postForEntity(url, request, PredictionResultDTO.class);

            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException e) {
            // Flask returned 400 (like fighter not found)
            return ResponseEntity.status(e.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getResponseBodyAsString());

        } catch (HttpServerErrorException e) {
            // Flask returned 500
            return ResponseEntity.status(e.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getResponseBodyAsString());
        }
    }

    public ResponseEntity<String> searchFighters(String q) {
        String url = UriComponentsBuilder
                .fromUriString("http://127.0.0.1:5000/fighters/search")
                .queryParam("q", q)
                .toUriString();

        try {
            return restTemplate.getForEntity(url, String.class);
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getResponseBodyAsString());
        }
    }
   
}

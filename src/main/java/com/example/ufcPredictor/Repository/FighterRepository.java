package com.example.ufcPredictor.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ufcPredictor.Model.Fighter;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long>{
    Optional<Fighter> findByName(String name);
}

package com.example.ufcPredictor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ufcPredictor.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
 
}

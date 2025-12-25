package com.example.ufcPredictor.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fighter")
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY Uses the database's built-in auto-increment feature 
    private Long id;

    @Column(name = "name")
    private String name;

    //Each fighter has a unique name
    @Column(unique = true, nullable = false, name = "age")
    private int age;

    @Column(name = "height")
    private double height;

    @Column(name = "reach")
    private double reach;

    @Column(name = "record")
    private String fighterRecord;

    @Column(name = "strikes")
    private double strikesPerMin;

    @Column(name = "takeDownD")
    private double takeDownDefence;

    @Column(name = "takeDownA")
    private double takeDownAccuracy;

    @Column(name = "winStreak")
    private int winStreak;

    @Column(name = "weightClass")
    private String weightClass;

    @Column(name = "rounds")
    private int rounds;

    @Column(name = "shortNotice")
    private boolean shortNotice;

    @Column(name = "country")
    private String country;

}

package com.plantwatchersspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int water_level;
    private String name;
    private String type;
    private int maturity;
    private long userId;

    protected Plant(){}

    public Plant(String name, String type, long userId){
        this.name = name;
        this.type = type;
        this.userId = userId;

    }

    public Plant(long plant_id, int water_level, String name, String type, int maturity, long userId) {
        this.id = plant_id;
        this.water_level = water_level;
        this.name = name;
        this.type = type;
        this.maturity = maturity;
        this.userId = userId;
    }

    @Override
    public String toString(){
        return String.format(
                "Plant[id=%d, name='%s', type='%s', water_level=%d, maturity=%d, userId=%d]",
                id, name, type, water_level, maturity, userId);
    }

    public long getPlant_id() {
        return id;
    }

    public void setPlant_id(int plant_id) {
        this.id = plant_id;
    }

    public int getWater() {
        return water_level;
    }

    public void setWater(int newWater) {
        this.water_level = newWater;
        if (this.water_level < 0 ) this.water_level = 0;
        if (this.water_level > 100 ) this.water_level = 100;
    }

    public void addWater(int newWater) {
        this.water_level += newWater;
        if (this.water_level < 0 ) this.water_level = 0;
        if (this.water_level > 100 ) this.water_level = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaturity() {
        return maturity;
    }

    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }

    public void addMaturity(int newMaturity) {
        this.maturity += newMaturity;
        if (this.maturity < 0 ) this.maturity = 0;
        if (this.maturity > 100 ) this.maturity = 100;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}

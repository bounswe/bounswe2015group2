package edu.boun.cmpe451.group2.android.api;

import java.util.List;
import java.util.Map;

public class Ingredient {

    public Long id = null;
    public String name = "";
    public Long amount = Long.valueOf(0);
    public double protein = 0;
    public double fat = 0;
    public double carbohydrate = 0;
    public double calories = 0;
    public String unitName = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}

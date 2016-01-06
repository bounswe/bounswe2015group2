package edu.boun.cmpe451.group2.client;

import edu.boun.cmpe451.group2.dao.IngredientDao;

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
    public IngredientDao ingDao = null;

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

    public IngredientDao getIngDao() {
        return ingDao;
    }

    public void setIngDao(IngredientDao ingDao) {
        this.ingDao = ingDao;
    }

    /*public List<Ingredient> getIngredients() {
        List<Ingredient> allIngredients = null;
        List<Map<String, Object>> ingredients = ingDao.getAllIngredients();
        for(int i = 0; i < ingredients.size(); i ++) {
            Ingredient tempIng = null;
            tempIng.id = Long.parseLong(ingredients.get(i).get("id").toString());
            tempIng.name = ingredients.get(i).get("name").toString();
            tempIng.protein = Double.parseDouble(ingredients.get(i).get("protein").toString());
            tempIng.fat = Double.parseDouble(ingredients.get(i).get("fat").toString());
            tempIng.carbohydrate = Double.parseDouble(ingredients.get(i).get("carbonhydrate").toString());
            tempIng.calories = Long.parseLong(ingredients.get(i).get("calories").toString());
            tempIng.unitName = ingredients.get(i).get("unitName").toString();
            allIngredients.add(tempIng);
        }
        return allIngredients;
    }*/
}

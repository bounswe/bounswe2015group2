package edu.boun.cmpe451.group2.model;

import edu.boun.cmpe451.group2.dao.IngredientDao;

import java.util.List;
import java.util.Map;

public class Ingredient {

    public Long id = null;
    public String name = "";
    public double protein = 0;
    public double fat = 0;
    public double carbohydrate = 0;
    public Long calories = 0L;
    public Long unitID = null;
    public String unitName= "";
    public IngredientDao ingDao = null;

    public List<Ingredient> getIngredients() {
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
            tempIng.unitID = Long.parseLong(ingredients.get(i).get("unitId").toString());
            tempIng.unitName = ingredients.get(i).get("unitName").toString();
            allIngredients.add(tempIng);
        }
        return allIngredients;
    }
}

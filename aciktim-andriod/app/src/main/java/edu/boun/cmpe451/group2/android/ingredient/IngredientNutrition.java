package edu.boun.cmpe451.group2.android.ingredient;

/**
 * Created by Cafer Tayyar YORUK on 01.01.2016.
 */
public class IngredientNutrition {

    String name;
    String ndbno;
    int quantity = 100;
    String energy = "";
    String carb = "";
    String prot = "";
    String fat = "";

    public IngredientNutrition( String name, String ndbno, int quantity, String energy, String carb, String prot, String fat){
        this.name = name;
        this.ndbno = ndbno;
        this.quantity = quantity;
        this.energy = energy;
        this.carb = carb;
        this.prot = prot;
        this.fat = fat;
    }

    public String getName(){
        return name;
    }

    public String getNDBNO(){
        return ndbno;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getEnergy(){
        return energy;
    }

    public String getCarbohydrate(){
        return carb;
    }

    public String getProtein(){
        return prot;
    }

    public String getFat(){
        return fat;
    }
}

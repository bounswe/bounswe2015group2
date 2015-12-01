package edu.boun.cmpe451.group2.android.api;

import java.util.ArrayList;
import java.util.Map;

/**
 * Menu model class
 * this class processes all works related to menus.
 * this is also the variable type to be used.
 */
public class Menu {

    public ArrayList<Recipe> recipes;
    public Long ownerID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String name = "";
}

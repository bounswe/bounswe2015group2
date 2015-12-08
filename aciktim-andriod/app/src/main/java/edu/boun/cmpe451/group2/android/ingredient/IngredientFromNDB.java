package edu.boun.cmpe451.group2.android.ingredient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cafer Tayyar YORUK on 06.12.2015.
 */

public class IngredientFromNDB {

    @SerializedName("list")
    @Expose
    private IngredientList list;

    /**
     *
     * @return
     * The list
     */
    public IngredientList getList() {
        return list;
    }

    /**
     *
     * @param list
     * The list
     */
    public void setList(IngredientList list) {
        this.list = list;
    }

}
package edu.boun.cmpe451.group2.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.boun.cmpe451.group2.dao.RecipeDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

/**
 * Recipe Model Class
 * This class process all works related to Recipe.
 */
@Service
@Scope("request")
public class RecipeModel {

    @Qualifier("recipeDao")
    @Autowired
    private RecipeDao recipeDao = null;

    /**
     * controls the recipe and sends it to the dao to be added to the db
     * @param recipe recipe to be added
     * @throws Exception when name is empty, ownerid is null,ingredient map is empty
     */
    public void addRecipe(Recipe recipe)throws Exception{
        if(StringUtil.isEmpty(recipe.getName()))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if(recipe.getOwnerID() == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if(StringUtil.isEmpty(recipe.getPictureAddress()))
            recipe.pictureAddress="";
        if(recipe.getIngredientAmountMap().size() == 0)
            throw new ExException(ExError.E_RECIPELIST_EMPTY_OR_NULL);

        for(Map.Entry<Ingredient,Long> entry: recipe.getIngredientAmountMap().entrySet()){
            recipe.totalCal += entry.getKey().calories*entry.getValue();
            recipe.totalCarb += entry.getKey().carbohydrate*entry.getValue();
            recipe.totalFat += entry.getKey().fat*entry.getValue();
            recipe.totalProtein += entry.getKey().protein*entry.getValue();
        }
        recipeDao.addRecipe(recipe);
    }

    
    /**
     * returns a list of recipes belong to a user
     * @param users_id
     * @return arraylist of recipemodels
     */
    public List<Recipe> getRecipes(Long users_id) {
        return recipeDao.getRecipes(users_id);
    }


    public void deleteRecipe(Long recipeID) throws Exception {
        recipeDao.getRecipe(recipeID);

        recipeDao.deleteRecipe(recipeID);
    }

    public void updateRecipe(Long recipeID, String recipeName, Long ownerID, JsonObject ingredientMapJ, String pictureAddress, String description) throws Exception {
        if (StringUtil.isEmpty(recipeName))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if (ownerID == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if (StringUtil.isEmpty(pictureAddress))
            pictureAddress = "";

        Map<Long, Long> ingredientMap = new HashMap<Long, Long>();
        for (Map.Entry entry : ingredientMapJ.entrySet()) {
            ingredientMap.put((Long) entry.getKey(), (Long) entry.getValue());
        }
        recipeDao.updateRecipe(recipeID, recipeName, ownerID, ingredientMap, pictureAddress, description);
    }

    public RecipeDao getRecipeDao() {
        return recipeDao;
    }

    /**
     *  use this method when you want to view a recipe
     * @param recipe_id
     * @return recipe object that has the id
     * @throws Exception when there is no recipe of that id
     */
    public Recipe getRecipe(Long recipe_id) throws Exception{
        return recipeDao.getRecipe(recipe_id);
    }
}

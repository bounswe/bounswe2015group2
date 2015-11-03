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

    public Long id = null;
    public String name = "";
    public String pictureAddress = "";
    public Long ownerID = null;
    public Long likes = 0L;
    public List<Comment> commentList = new ArrayList<Comment>();
    public HashMap<Ingredient, Long> IngredientAmountMap = new HashMap<Ingredient,Long>();
    public List<Tag> tagList = null;
    public String description = "";

    @Qualifier("recipeDao")
    @Autowired
    private RecipeDao recipeDao = null;

    /**
     * Adds a new recipe
     * @param recipeName recipe name to be created
     * @param ownerID userID of the owner of the recipe
     * @param ingredientMap ingredient map Key:ingredientID Value:amount
     * @param pictureAddress address of the picture of the recipe
     * @param description description of the recipe
     * @throws Exception
     */
    public void addRecipe(String recipeName, Long ownerID, Map<Ingredient,Long> ingredientMap, String pictureAddress,String description)throws Exception{
        if(StringUtil.isEmpty(recipeName))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if(ownerID == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if(StringUtil.isEmpty(pictureAddress))
            pictureAddress="";

        recipeDao.addRecipe(recipeName, ownerID, ingredientMap, pictureAddress, description);
    }
    
    /**
     * returns a list of recipes belong to a user
     * @param users_id
     * @return arraylist of recipemodels
     */
    public List<Map<String, Object>> getRecipes(Long users_id) {
        return recipeDao.getRecipes(users_id);
    }


    public void deleteRecipe(Long recipeID) throws Exception {
        recipeDao.getRecipe(recipeID);

        recipeDao.deleteRecipe(recipeID);
    }

    public void updateRecipe(Long recipeID, String recipeName, Long ownerID, JsonObject ingredientMapJ, String pictureAddress) throws Exception {
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
        recipeDao.updateRecipe(recipeID, recipeName, ownerID, ingredientMap, pictureAddress);
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
    public RecipeModel getRecipe(Long recipe_id) throws Exception{
        return recipeDao.getRecipe(recipe_id);
    }
}

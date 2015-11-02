package edu.boun.cmpe451.group2.model;

import com.google.gson.JsonObject;
import edu.boun.cmpe451.group2.dao.RecipeDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
     * Adds a new recipe
     * @param recipeName recipe name to be created
     * @param ownerID userID of the owner of the recipe
     * @param ingredientMapJ ingredient map Key:ingredientID Value:amount
     * @param pictureAddress address of the picture of the recipe
     * @throws Exception
     */
    public void addRecipe(String recipeName, Long ownerID, JsonObject ingredientMapJ, String pictureAddress)throws Exception{
        if(StringUtil.isEmpty(recipeName))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if(ownerID == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if(StringUtil.isEmpty(pictureAddress))
            pictureAddress="";

        Map<Long,Long> ingredientMap = new HashMap<Long,Long>();
        for(Map.Entry entry: ingredientMapJ.entrySet()){
            ingredientMap.put((Long)entry.getKey(),(Long)entry.getValue());
        }
        recipeDao.addRecipe(recipeName,ownerID,ingredientMap,pictureAddress);
    }

    public void deleteRecipe(Long recipeID) throws Exception{
        if(recipeDao.getRecipe(recipeID).size() == 0)
            throw new ExException(ExError.E_RECIPE_NOT_FOUND);

        recipeDao.deleteRecipe(recipeID);
    }
    public void updateRecipe(Long recipeID) {}

    public RecipeDao getRecipeDao(){return recipeDao;}
}

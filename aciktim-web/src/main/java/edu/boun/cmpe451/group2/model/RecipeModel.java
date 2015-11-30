package edu.boun.cmpe451.group2.model;

import edu.boun.cmpe451.group2.client.Ingredient;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.dao.RecipeDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
     * default search function
     * @param name name of the recipe
     * @return returns a list of recipes
     * @throws ExException when the name is empty or null
     */
    public ArrayList<Recipe> searchRecipes(String name) throws ExException{
        if(name==null || StringUtil.isEmpty(name)){
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        }
        return recipeDao.searchRecipes(name);
    }

    public List<Recipe> searchRecipesRandom(int amount) throws ExException{
        return recipeDao.searchRecipesRandom(amount);
    }

    /**
     * advanced search function by ingredient list filter
     * @param name name of the recipe
     * @param ingredients names of the ingredients
     * @return a list of recipes that contains all the ingredients
     * @throws ExException when the list is null or empty
     */
    public ArrayList<Recipe> searchRecipes(String name,List<String> ingredients) throws ExException{
        if(ingredients==null || ingredients.size() == 0){
            throw new ExException(ExError.E_INGREDIENT_LIST_EMPTY_OR_NULL);
        }

        return recipeDao.searchRecipes(name,ingredients);
    }
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

        int counter=0;
        for(Map.Entry<Ingredient,Long> entry: recipe.getIngredientAmountMap().entrySet()){
            if(((Ingredient)entry.getKey()).id == null){
                throw new ExException(ExError.E_INGREDIENT_ID_NULL);
            }
            System.out.println("Counter : " + counter);
            System.out.println("CALORIES : "+entry.getKey().calories);
            System.out.println("INGREDIENT AMOUNT : "+entry.getValue());
            recipe.totalCal += entry.getKey().calories*entry.getValue();
            recipe.totalCarb += entry.getKey().carbohydrate*entry.getValue();
            recipe.totalFat += entry.getKey().fat*entry.getValue();
            recipe.totalProtein += entry.getKey().protein*entry.getValue();
            counter++;
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

    public void updateRecipe(Recipe recipe) throws Exception {
        if (StringUtil.isEmpty(recipe.getName()))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if (recipe.ownerID == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if (StringUtil.isEmpty(recipe.pictureAddress))
            recipe.pictureAddress = "";
        if(recipe.getIngredientAmountMap().size() == 0)
            throw new ExException(ExError.E_RECIPELIST_EMPTY_OR_NULL);

        recipe.totalProtein = 0;
        recipe.totalFat=0;
        recipe.totalCarb=0;
        recipe.totalCal=0;

        for(Map.Entry<Ingredient,Long> entry: recipe.getIngredientAmountMap().entrySet()){
            recipe.totalCal += entry.getKey().calories*entry.getValue();
            recipe.totalCarb += entry.getKey().carbohydrate*entry.getValue();
            recipe.totalFat += entry.getKey().fat*entry.getValue();
            recipe.totalProtein += entry.getKey().protein*entry.getValue();
        }

        recipeDao.updateRecipe(recipe);
    }

    /**
     * returns 5 recipes according to daily consumption of the user
     * @param user user to be recommended
     * @return arraylist of recipes that contains maximum 5 recipes
     * @throws Exception
     */
    public ArrayList<Recipe> getRecommendations(User user) throws Exception{
        List<Map<String, Object>> list = recipeDao.getRecommendations(user);
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        for(Map<String,Object> row : list){
            Recipe r = getRecipe(Long.parseLong(row.get("id").toString()));
            recipes.add(r);
        }
        return recipes;
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

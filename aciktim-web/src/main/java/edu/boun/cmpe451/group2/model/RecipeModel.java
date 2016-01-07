package edu.boun.cmpe451.group2.model;

import edu.boun.cmpe451.group2.client.Ingredient;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.Tag;
import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.dao.RecipeDao;
import edu.boun.cmpe451.group2.dao.UserDao;
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

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao = null;
    /**
     * default search function
     *
     * @param name name of the recipe
     * @return returns a list of recipes
     * @throws ExException when the name is null(can be empty)
     */
    public ArrayList<Recipe> searchRecipes(String name) throws ExException {
        if (name == null ) {
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        }
        return recipeDao.searchRecipes(name);
    }

    /**
     * this method uses default search function with the preference filter of the given user
     * @param name name of the recipe
     * @param userID id of the user that preferences will be taken of
     * @return a list of recipes that matches the name after reordering preferences
     * @throws ExException when the name is null (can be empty)
     */
    public ArrayList<Recipe> searchRecipes(String name,long userID) throws ExException{
        ArrayList<Recipe> list = searchRecipes(name);
        return reOrderByPreferences(list, userID);
    }

    /**
     * reorders the given recipes by distracting allergens
     * putting liked list to the top
     * putting disliked list to the bottom
     * @param list list to be reordered
     * @param userID id of the user that the preferences will come from
     * @return reordered list of recipes
     */
    private ArrayList<Recipe> reOrderByPreferences(ArrayList<Recipe> list,long userID){
        ArrayList<Tag> likes = userDao.getLikes(Long.toString(userID));
        ArrayList<Tag> dislikes = userDao.getDislikes(Long.toString(userID));
        ArrayList<Tag> allergies = userDao.getAllergies(Long.toString(userID));
        ArrayList<Recipe> reOrdered = new ArrayList<Recipe>();
        //removing allergies
        for(Recipe r : list){
            boolean contains = false;
            for(Tag t: r.tagList){
                for(Tag t2:allergies){
                    if(t.name.equals(t2.name)){
                        contains = true;
                        break;
                    }
                }
                if(contains) break;
            }
            if(!contains){
                reOrdered.add(r);
            }
        }
        //ordering results
        ArrayList<Recipe> likedRecipes = new ArrayList<Recipe>();
        ArrayList<Recipe> dislikedRecipes = new ArrayList<Recipe>();
        ArrayList<Recipe> notrRecipes = new ArrayList<Recipe>();
        for(Recipe r : reOrdered){
            // check disliked
            boolean isDisliked= false;
            for(Tag t: r.tagList){
                for(Tag t2:dislikes){
                    if(t.name.equals(t2.name)){
                        isDisliked = true;
                        break;
                    }
                }
                if(isDisliked) break;
            }
            if(isDisliked) //no need for further check
            {
                dislikedRecipes.add(r);
                continue;
            }
            //check liked
            boolean isLiked = false;
            for(Tag t: r.tagList){
                for(Tag t2:likes){
                    if(t.name.equals(t2.name)){
                        isLiked = true;
                        break;
                    }
                }
                if(isLiked) break;
            }
            if(isLiked) //no need for further check
            {
                likedRecipes.add(r);
                continue;
            }
            //else it goes to notr list
            notrRecipes.add(r);
        }
        //write back to reOrdered list
        reOrdered.clear();
        for(Recipe r: likedRecipes){
            reOrdered.add(r);
        }
        for(Recipe r:notrRecipes){
            reOrdered.add(r);
        }
        for(Recipe r:dislikedRecipes){
            reOrdered.add(r);
        }
        return reOrdered;
    }

    /**
     * this method randomly brings recipes for the main page
     * @param amount amount of the random recipes
     * @return random recipes
     * @throws ExException
     */
    public List<Recipe> searchRecipesRandom(int amount) throws ExException {
        return recipeDao.searchRecipesRandom(amount);
    }

    /**
     * advanced search function by ingredient list filter
     *
     * @param name        name of the recipe
     * @param ingredients names of the ingredients
     * @return a list of recipes that contains all the ingredients
     * @throws ExException when the list is null or empty
     */
    public ArrayList<Recipe> searchRecipes(String name, List<String> ingredients,Long userID) throws ExException {
        if (ingredients == null || ingredients.size() == 0) {
            throw new ExException(ExError.E_INGREDIENT_LIST_EMPTY_OR_NULL);
        }

        ArrayList<Recipe> list = recipeDao.searchRecipes(name, ingredients, null);
        return reOrderByPreferences(list, userID);
    }


    /**
     * advanced search function by ingredient list filter
     *
     * @param name        name of the recipe
     * @param ingredients names of the ingredients
     * @return a list of recipes that contains all the ingredients
     * @throws ExException when the list is null or empty
     */
    public ArrayList<Recipe> advancedSearchRecipes(String name, List<String> ingredients, Boolean isInst, Double totalFatUpper, Double totalCarbUpper, Double totalProteinUpper, Double totalCalUpper, Double totalFatLower, Double totalCarbLower, Double totalProteinLower, Double totalCalLower, List<String> tags,Long userID) throws ExException {
        ArrayList<Recipe> list = recipeDao.advancedSearch(name, ingredients, isInst, totalFatUpper, totalCarbUpper, totalProteinUpper, totalCalUpper, totalFatLower, totalCarbLower, totalProteinLower, totalCalLower, tags);
        return reOrderByPreferences(list, userID);
    }

    /**
     *this method is used for advanced search by filtering results by including ingredients and tags in OR manner
     * @param name keyword to be searched (CAN BE EMPTY)
     * @param ingredients list of keywords of ingredients that the result list will contain in OR manner
     * @param tags list of keywords of tags that the result will contain in OR manner
     * @param userID id of the user that does the search (this is required for reordering)
     * @return advanced search result after reordering
     * @throws ExException
     */
    public ArrayList<Recipe> searchRecipes(String name, List<String> ingredients, List<String> tags,Long userID) throws ExException {
        ArrayList<Recipe> list =  recipeDao.searchRecipes(name, ingredients, tags);
        return reOrderByPreferences(list, userID);
    }

    /**
     * controls the recipe and sends it to the dao to be added to the db
     *
     * @param recipe recipe to be added
     * @throws Exception when name is empty, ownerid is null,ingredient map is empty
     */
    public void addRecipe(Recipe recipe) throws Exception {
        if (StringUtil.isEmpty(recipe.getName()))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if (recipe.getOwnerID() == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if (StringUtil.isEmpty(recipe.getPictureAddress()))
            recipe.pictureAddress = "";
        if (recipe.getIngredientList().size() == 0)
            throw new ExException(ExError.E_RECIPELIST_EMPTY_OR_NULL);

        int counter = 0;
        for (Ingredient ingredient : recipe.getIngredientList()) {
            if (ingredient.id == null) {
                throw new ExException(ExError.E_INGREDIENT_ID_NULL);
            }
            recipe.totalCal += ingredient.calories * ingredient.amount;
            recipe.totalCarb +=ingredient.carbohydrate * ingredient.amount;
            recipe.totalFat += ingredient.fat * ingredient.amount;
            recipe.totalProtein += ingredient.protein * ingredient.amount;
            counter++;
        }
        recipeDao.addRecipe(recipe);
    }


    /**
     * returns a list of recipes belong to a user
     *
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

    /**
     * this method updates a recipe
     * @param recipe recipe object that has the correct values for update
     * @throws Exception
     */
    public void updateRecipe(Recipe recipe) throws Exception {
        if (StringUtil.isEmpty(recipe.getName()))
            throw new ExException(ExError.E_RECIPE_NAME_EMPTY);
        if (recipe.ownerID == null)
            throw new ExException(ExError.E_NULL_OWNERID);
        if (StringUtil.isEmpty(recipe.pictureAddress))
            recipe.pictureAddress = "";
        if (recipe.getIngredientList().size() == 0)
            throw new ExException(ExError.E_RECIPELIST_EMPTY_OR_NULL);

        recipe.totalProtein = 0;
        recipe.totalFat = 0;
        recipe.totalCarb = 0;
        recipe.totalCal = 0;

        for (Ingredient ingredient : recipe.getIngredientList()) {
            recipe.totalCal += ingredient.calories * ingredient.amount;
            recipe.totalCarb += ingredient.carbohydrate * ingredient.amount;
            recipe.totalFat += ingredient.fat * ingredient.amount;
            recipe.totalProtein += ingredient.protein * ingredient.amount;
        }

        recipeDao.updateRecipe(recipe);
    }

    /**
     * returns 5 recipes according to daily consumption of the user
     *
     * @param userID userID of the user to be recommended
     * @return arraylist of recipes that contains maximum 5 recipes
     * @throws Exception
     */
    public ArrayList<Recipe> getRecommendations(String userID) throws Exception {
        List<Map<String, Object>> list = recipeDao.getRecommendations(userID);
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        for (Map<String, Object> row : list) {
            Recipe r = getRecipe(Long.parseLong(row.get("id").toString()));
            recipes.add(r);
        }
        return reOrderByPreferences(recipes,Long.parseLong(userID));
    }

    /**
     * this method returns maximum 5 random recipes that this user likes, doesn't dislike,
     * and don't have allergies to
     *
     * @param user user to be recommended
     * @return arraylist of maximum 5 random recipes
     * @throws Exception when an integer cannot be converted to long
     */
    public ArrayList<Recipe> getRecommendationsPreferences(User user) throws ExException {
        return recipeDao.getRecommendationsPreferences(user.id);
    }

    public RecipeDao getRecipeDao() {
        return recipeDao;
    }

    /**
     * use this method when you want to view a recipe
     *
     * @param recipe_id
     * @return recipe object that has the id
     * @throws Exception when there is no recipe of that id
     */
    public Recipe getRecipe(Long recipe_id) throws Exception {
        return recipeDao.getRecipe(recipe_id);
    }

    /**
     * this method bring all recipes
     * @return all recipes in a list
     * @throws Exception
     */
    public List<Recipe> getRecipesAll() throws Exception {
        return recipeDao.getRecipesAll();
    }

}

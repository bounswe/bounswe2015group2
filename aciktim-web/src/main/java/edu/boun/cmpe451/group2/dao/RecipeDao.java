package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.Ingredient;
import edu.boun.cmpe451.group2.model.RecipeModel;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Recipe Dao class for User's Database Related operation.
 */
@Repository
@Scope("request")
public class RecipeDao extends BaseDao {
    /**
     * gets recipe ids and recipe names of a user
     * @param users_id id of user whose recipes to be get
     * @return recipe ids and recipe names of a user
     */
    public ArrayList<RecipeModel> getRecipes(Long users_id) {
        String sql = "SELECT id,name FROM recipes WHERE ownerID = ?";
        ArrayList<RecipeModel> recipeList = new ArrayList<RecipeModel>();
        List<Map<String,Object>> resultList = this.jdbcTemplate.queryForList(sql,users_id);
        for(Map<String,Object> resultMap: resultList){
            RecipeModel recipe = new RecipeModel();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();
            recipeList.add(recipe);
        }
        return recipeList;
    }

    /**
     * Returns a recipe if id has a match.
     * @param id id of the recipe
     * @return recipe (if found)
     */
    public RecipeModel getRecipe(Long id) throws ExException {
        String sql = "SELECT * FROM recipes WHERE recipes.id = ? ";

        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, id);
        if(map.size()==0){
            throw new ExException(ExError.E_RECIPE_NOT_FOUND);
        }
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.id = Long.parseLong(map.get("id").toString());
        recipeModel.name = map.get("name").toString();
        recipeModel.pictureAddress = map.get("pictureAddress").toString();
        recipeModel.ownerID = Long.parseLong(map.get("ownerID").toString());
        recipeModel.description = map.get("description").toString();
        recipeModel.likes = Long.parseLong(map.get("likes").toString());

        String sql2 = "SELECT A.*,B.name as unitName FROM (SELECT * FROM recipeIngredient JOIN ingredients ON ingredients.id = recipeIngredient.ingredientID WHERE recipeIngredient.recipeID = ? ) as A JOIN ingredientUnits as B WHERE A.unitID =B.id";

        List<Map<String, Object>> map2 = this.jdbcTemplate.queryForList(sql2, id);
        HashMap<Ingredient,Long> ingredientMap = new HashMap<Ingredient,Long>();
        for(Map<String,Object> ingredientEntry:map2){
            Ingredient ingredient = new Ingredient();
            ingredient.id = Long.parseLong(ingredientEntry.get("id").toString());
            ingredient.name = ingredientEntry.get("name").toString();
            ingredient.protein = Double.parseDouble(ingredientEntry.get("protein").toString());
            ingredient.fat = Double.parseDouble(ingredientEntry.get("fat").toString());
            ingredient.carbohydrate = Double.parseDouble(ingredientEntry.get("carbohydrate").toString());
            ingredient.calories = Long.parseLong(ingredientEntry.get("calories").toString());
            ingredient.unitID = Long.parseLong(ingredientEntry.get("unitID").toString());
            ingredient.unitName = ingredientEntry.get("unitName").toString();
            Long amount = Long.parseLong(ingredientEntry.get("amount").toString());
            ingredientMap.put(ingredient,amount);
        }
        recipeModel.IngredientAmountMap = ingredientMap;

        return recipeModel;
    }

    /**
     * Adds a recipe with recipeName,ownerID and an ingredient list
     *
     * @param recipeName    name of the recipe
     * @param ownerID       user id of the owner of the recipe
     * @param IngredientMap ingredient list
     */
    public void addRecipe(String recipeName,Long ownerID,Map<Ingredient,Long> IngredientMap,String pictureAdress,String description){
        String sql = "INSERT INTO recipes(name,ownerID,pictureAddress,description) VALUES(?,?,?,?)";
        this.jdbcTemplate.update(sql,recipeName,ownerID,pictureAdress,description);
        sql = "SELECT id FROM recipes ORDER BY id DESC LIMIT 1";
        Long recipeID=Long.parseLong(this.jdbcTemplate.queryForMap(sql).get("id").toString());
        if(IngredientMap.size()>0){
            sql = "INSERT INTO recipeIngredient(recipeID,ingredientID,amount) VALUES(?,?,?)";
            for (Map.Entry entry : IngredientMap.entrySet()) {
                this.jdbcTemplate.update(sql, recipeID, ((Ingredient) (entry.getKey())).id, entry.getValue());
            }
        }
    }

    /**
     * Deletes a recipe by recipe ID
     * @param recipeID id of the recipe to be deleted
     */
    public void deleteRecipe(Long recipeID) {

        String sql = "DELETE FROM recipeIngredient WHERE recipeID=?";
        this.jdbcTemplate.update(sql, recipeID);

        sql = "DELETE FROM  recipeTag WHERE recipeID=?";
        this.jdbcTemplate.update(sql, recipeID);

        sql = "DELETE  FROM  recipeComment WHERE recipeID=?";
        this.jdbcTemplate.update(sql, recipeID);

        sql = "DELETE FROM recipes WHERE id=?";
        this.jdbcTemplate.update(sql, recipeID);
    }

    public void updateRecipe(Long recipeID, String recipeName, Long ownerID, Map<Long, Long> IngredientMap, String pictureAddress) {
        String sql = "UPDATE recipes SET pictureAddress = ?, name = ? , ownerID = ? WHERE id = ?";

        this.jdbcTemplate.update(sql, pictureAddress, recipeName, ownerID, recipeID);
        sql = "DELETE FROM recipeIngredient where recipeID = ?";
        this.jdbcTemplate.update(sql, recipeID);
        if (IngredientMap.size() > 0) {
            sql = "INSERT INTO recipeIngredient(recipeID,ingredientID,amount) VALUES(?,?,?)";
            for (Map.Entry entry : IngredientMap.entrySet()) {
                this.jdbcTemplate.update(sql, recipeID, entry.getKey(), entry.getValue());
            }
        }
    }
}

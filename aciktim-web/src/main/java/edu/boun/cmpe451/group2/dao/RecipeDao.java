package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.model.Ingredient;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Recipe Dao class for User's Database Related operation.
 */
@Repository
@Scope("request")
public class RecipeDao extends BaseDao{
    /**
     * Returns all of recipes.
     * @return All recipes in the db
     */
    public List<Map<String, Object>> getRecipes() {
        String sql = "SELECT * FROM recipes";

        return this.jdbcTemplate.queryForList(sql);
    }

    /**
     * Returns a recipe if id has a match.
     * @param id id of the recipe
     * @return recipe (if found)
     */
    public Map<String, Object> getRecipe(Long id) {
        String sql = "SELECT * FROM recipes WHERE id = ? ";

        return this.jdbcTemplate.queryForMap(sql, id);
    }

    /**
     * Adds a recipe with recipeName,ownerID and an ingredient list
     * @param recipeName name of the recipe
     * @param ownerID user id of the owner of the recipe
     * @param IngredientMap ingredient list
     */
    public void addRecipe(String recipeName,Long ownerID,Map<Ingredient,Long> IngredientMap,String pictureAdress,String description){
        String sql = "INSERT INTO recipes(name,ownerID,pictureAddress,description) VALUES(?,?,?,?)";
        this.jdbcTemplate.update(sql,recipeName,ownerID,pictureAdress,description);
        sql = "SELECT id FROM recipes ORDER BY id DESC LIMIT 1";
        Long recipeID=Long.parseLong(this.jdbcTemplate.queryForMap(sql).get("id").toString());
        if(IngredientMap.size()>0){
            sql = "INSERT INTO recipeIngredient(recipeID,ingredientID,amount) VALUES(?,?,?)";
            for(Map.Entry entry : IngredientMap.entrySet()){
                this.jdbcTemplate.update(sql,recipeID,((Ingredient)(entry.getKey())).id,entry.getValue());
            }
        }
    }

    /**
     * Deletes a recipe by recipe ID
     * @param recipeID id of the recipe to be deleted
     */
    public void deleteRecipe(Long recipeID){

        String sql = "DELETE FROM recipeIngredient WHERE recipeID=?";
        this.jdbcTemplate.update(sql,recipeID);

        sql = "DELETE FROM  recipeTag WHERE recipeID=?";
        this.jdbcTemplate.update(sql,recipeID);

        sql = "DELETE  FROM  recipeComment WHERE recipeID=?";
        this.jdbcTemplate.update(sql,recipeID);

        sql = "DELETE FROM recipes WHERE id=?";
        this.jdbcTemplate.update(sql,recipeID);
    }
    public void updateRecipe (Long recipeID, String recipeName,Long ownerID,Map<Long,Long> IngredientMap, String pictureAddress){
        String sql = "UPDATE recipes SET pictureAddress = ?, name = ? , ownerID = ? WHERE id = ?";

        this.jdbcTemplate.update(sql,pictureAddress, recipeName, ownerID, recipeID);
        sql = "DELETE FROM recipeIngredient where recipeID = ?";
        this.jdbcTemplate.update(sql, recipeID);
        if(IngredientMap.size()>0){
            sql = "INSERT INTO recipeIngredient(recipeID,ingredientID,amount) VALUES(?,?,?)";
            for(Map.Entry entry : IngredientMap.entrySet()){
                this.jdbcTemplate.update(sql,recipeID,entry.getKey(),entry.getValue());
            }
        }
    }
}

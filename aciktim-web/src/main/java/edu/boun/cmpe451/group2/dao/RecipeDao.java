package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.model.Ingredient;
import edu.boun.cmpe451.group2.model.RecipeModel;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Map<String, Object> getRecipe(Long id) {
        String sql = "SELECT * FROM recipes WHERE recipes.id = ? ";

        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, id);

        String sql2 = "SELECT * FROM recipeIngredient JOIN ingredients ON ingredients.id = recipeIngredient.ingredientID WHERE recipeIngredient.recipeID = ? ";

        List<Map<String, Object>> map2 = this.jdbcTemplate.queryForList(sql2, id);

        map.put("ingredients", map2);

        return map;
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

package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.Ingredient;
import edu.boun.cmpe451.group2.model.Recipe;
import edu.boun.cmpe451.group2.model.Tag;
import org.springframework.context.annotation.Scope;
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
     *
     * @param users_id id of user whose recipes to be get
     * @return recipe ids and recipe names of a user
     */
    public ArrayList<Recipe> getRecipes(Long users_id) {
        String sql = "SELECT id,name FROM recipes WHERE ownerID = ?";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql, users_id);
        for (Map<String, Object> resultMap : resultList) {
            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();
            recipeList.add(recipe);
        }
        return recipeList;

    }

    /**
     * searches on recipes
     *
     * @param name
     * @param ingredients
     * @return
     */
    public ArrayList<Recipe> searchRecipes(String name, ArrayList<Integer> ingredients) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql, "%" + name + "%");
        for (Map<String, Object> resultMap : resultList) {

            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();


            String sql2 = "SELECT * FROM recipeIngredient WHERE recipeID = ?";
            List<Map<String, Object>> resultList2 = this.jdbcTemplate.queryForList(sql, recipe.id);
            boolean all_have = true;

            for (Integer ingredient : ingredients) {
                boolean is_exists = false;

                for (Map<String, Object> resultMap2 : resultList2) {
                    if (Integer.parseInt(resultMap2.get("ingredientID").toString()) == ingredient) {
                        is_exists = true;
                    }
                }

                all_have = is_exists;
            }

            if (all_have)
                recipeList.add(recipe);
        }
        return recipeList;
    }

    /*

        String sql = "SELECT * FROM recipes WHERE ownerID = ?";

        return this.jdbcTemplate.queryForList(sql, users_id);
    */

    /**
     * Returns a recipe if id has a match.
     *
     * @param id id of the recipe
     * @return recipe (if found)
     */
    public Recipe getRecipe(Long id) throws ExException {
        String sql = "SELECT * FROM recipes WHERE recipes.id = ? ";

        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, id);
        if (map.size() == 0) {
            throw new ExException(ExError.E_RECIPE_NOT_FOUND);
        }
        Recipe recipe = new Recipe();
        recipe.id = Long.parseLong(map.get("id").toString());
        recipe.name = map.get("name").toString();
        recipe.pictureAddress = map.get("pictureAddress").toString();
        recipe.ownerID = Long.parseLong(map.get("ownerID").toString());
        recipe.description = map.get("description").toString();
        recipe.likes = Long.parseLong(map.get("likes").toString());

        String sql2 = "SELECT A.*,B.name as unitName FROM (SELECT * FROM recipeIngredient JOIN ingredients ON ingredients.id = recipeIngredient.ingredientID WHERE recipeIngredient.recipeID = ? ) as A JOIN ingredientUnits as B WHERE A.unitID =B.id";

        List<Map<String, Object>> map2 = this.jdbcTemplate.queryForList(sql2, id);
        HashMap<Ingredient, Long> ingredientMap = new HashMap<Ingredient, Long>();
        for (Map<String, Object> ingredientEntry : map2) {
            Ingredient ingredient = new Ingredient();
            ingredient.id = Long.parseLong(ingredientEntry.get("id").toString());
            ingredient.name = ingredientEntry.get("name").toString();
            ingredient.protein = Double.parseDouble(ingredientEntry.get("protein").toString());
            ingredient.fat = Double.parseDouble(ingredientEntry.get("fat").toString());
            ingredient.carbohydrate = Double.parseDouble(ingredientEntry.get("carbohydrate").toString());
            ingredient.calories = Long.parseLong(ingredientEntry.get("calories").toString());
            ingredient.unitName = ingredientEntry.get("unitName").toString();
            Long amount = Long.parseLong(ingredientEntry.get("amount").toString());
            ingredientMap.put(ingredient, amount);
        }
        recipe.IngredientAmountMap = ingredientMap;

        return recipe;
    }

    /**
     * Adds a recipe with recipeName,ownerID and an ingredient list
     */
    public void addRecipe(Recipe recipe){
        String sql = "INSERT INTO recipes(name,ownerID,pictureAddress,description," +
                "totalFat,totalCarb,totalProtein,totalCal) VALUES(?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,recipe.name,recipe.ownerID,recipe.pictureAddress,recipe.description,
                recipe.totalFat,recipe.totalCarb,recipe.totalProtein,recipe.totalCal);
        sql = "SELECT id FROM recipes ORDER BY id DESC LIMIT 1";
        Long recipeID=Long.parseLong(this.jdbcTemplate.queryForMap(sql).get("id").toString());
        if(recipe.IngredientAmountMap.size()>0){
            sql = "INSERT INTO recipeIngredient(recipeID,ingredientID,amount) VALUES(?,?,?)";
            for (Map.Entry entry : recipe.IngredientAmountMap.entrySet()) {
                Ingredient ingr = (Ingredient)entry.getKey();
                String sqlGet = "SELECT * FROM Ingredients WHERE id=?";
                Map<String,Object> map =  this.jdbcTemplate.queryForMap(sqlGet,ingr.id);
                if(map == null || map.size()==0) {
                    String sql2 = "INSERT INTO Ingredients(id,name,protein,fat,carb,cal,unitName) VALUES(?,?,?,?,?,?,?)";
                }
                this.jdbcTemplate.update(sql,ingr.id,ingr.name,ingr.protein,ingr.fat,ingr.carbohydrate,ingr.calories,ingr.unitName);
                this.jdbcTemplate.update(sql, recipeID, ((Ingredient) (entry.getKey())).id, entry.getValue());
            }
        }
        if (recipe.tagList.size() > 0) {
            sql = "INSERT INTO recipeTag(recipeID, tag) VALUES(?,?)";
            for (Tag tag : recipe.tagList) {
                this.jdbcTemplate.update(sql, recipeID, tag);
            }
        }
    }

    /**
     * Deletes a recipe by recipe ID
     *
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

    public void updateRecipe(Recipe recipe) {
        String sql = "UPDATE recipes SET pictureAddress = ?, name = ? , ownerID = ?, description = ?," +
                "totalFat = ?, totalCarb = ?, totalProtein = ?, totalCal = ? WHERE id = ?";

        this.jdbcTemplate.update(sql, recipe.pictureAddress, recipe.name, recipe.ownerID, recipe.description,
                recipe.totalFat, recipe.totalCarb, recipe.totalProtein, recipe.totalCal,recipe.id);
        sql = "DELETE FROM recipeIngredient where recipeID = ?";
        this.jdbcTemplate.update(sql, recipe.id);

        if (recipe.IngredientAmountMap != null && recipe.IngredientAmountMap.size() > 0) {
            sql = "INSERT INTO recipeIngredient(recipeID,ingredientID,amount) VALUES(?,?,?)";
            for (Map.Entry entry : recipe.IngredientAmountMap.entrySet()) {
                Ingredient ingr = (Ingredient)entry.getKey();
                String sqlGet = "SELECT * FROM Ingredients WHERE id=?";
                Map<String,Object> map =  this.jdbcTemplate.queryForMap(sqlGet,ingr.id);
                if(map == null || map.size()==0) {
                    String sql2 = "INSERT INTO Ingredients(id,name,protein,fat,carb,cal,unitName) VALUES(?,?,?,?,?,?,?)";
                }
                this.jdbcTemplate.update(sql,ingr.id,ingr.name,ingr.protein,ingr.fat,ingr.carbohydrate,ingr.calories,ingr.unitName);
                this.jdbcTemplate.update(sql, recipe.id, ((Ingredient) (entry.getKey())).id, entry.getValue());
            }
        }
    }
}

package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.Ingredient;
import edu.boun.cmpe451.group2.model.Recipe;
import edu.boun.cmpe451.group2.model.Tag;
import edu.boun.cmpe451.group2.model.User;
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
     * first it searches the recipes by name,
     * then it searches the ingredients that matches the given strings
     * ingredient list has AND relationship
     * @param name name of the recipe
     * @param ingredients names of ingredients
     * @return
     */
    public ArrayList<Recipe> searchRecipes(String name, ArrayList<String> ingredients) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql, "%" + name + "%");
        for (Map<String, Object> resultMap : resultList) {

            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();
            recipe.totalCal = Double.parseDouble(resultMap.get("totalCal").toString());
            recipe.totalFat = Double.parseDouble(resultMap.get("totalFat").toString());
            recipe.totalCarb = Double.parseDouble(resultMap.get("totalCarb").toString());
            recipe.totalProtein = Double.parseDouble(resultMap.get("totalProtein").toString());

            String sql2 = "SELECT * FROM recipeIngredient WHERE recipeID = ?";
            List<Map<String, Object>> resultList2 = this.jdbcTemplate.queryForList(sql, recipe.id);
            boolean all_have = true;

            for (String ingredientName : ingredients) {
                boolean is_exists = false;
                String sqlSearchIngr="SELECT * FROM Ingredients WHERE name LIKE ? ";
                List<Map<String,Object>> ingredientsFound = this.jdbcTemplate.queryForList(sqlSearchIngr,"%"+ingredientName+"%");
                ArrayList<Integer> ids = new ArrayList<Integer>();
                for(Map<String,Object> rows: ingredientsFound){
                    ids.add(Integer.parseInt(rows.get("id").toString()));
                }
                for (Map<String, Object> resultMap2 : resultList2) {
                    int id = Integer.parseInt(resultMap2.get("ingredientID").toString());
                    if (ids.contains(id)) {
                        is_exists = true;
                    }
                }

                all_have = all_have && is_exists;
            }

            if (all_have)
                recipeList.add(recipe);
        }
        return recipeList;
    }

    /**
     * default search function
     * @param name name of the recipe
     * @return recipe list
     */
    public ArrayList<Recipe> searchRecipes(String name) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql, "%" + name + "%");
        for (Map<String, Object> resultMap : resultList) {

            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();
            recipe.totalCal = Double.parseDouble(resultMap.get("totalCal").toString());
            recipe.totalFat = Double.parseDouble(resultMap.get("totalFat").toString());
            recipe.totalCarb = Double.parseDouble(resultMap.get("totalCarb").toString());
            recipe.totalProtein = Double.parseDouble(resultMap.get("totalProtein").toString());

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
     * adds a recipe to the db
     * @param recipe recipe to be added
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

    /**
     * updates a recipe
     * @param recipe recipe to be added
     */
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
    public List<Map<String, Object>> getRecommendations(User user) {
        Long fat = 0L;
        Long carb = 0L;
        Long protein=0L;
        ArrayList<Long> recipe = new ArrayList<Long>();
        String sql = "SELECT recipeID from dailyConsumption where userID = ?";
        List<Map<String, Object>> recipes = this.jdbcTemplate.queryForList(sql, user.id);
        for(Map<String,Object> map: recipes) {
            recipe.add(Long.parseLong(map.get("recipeID").toString()));
        }
        for(Long rec: recipe) {
            sql = "SELECT totalFat, totalCarb, totalProtein, totalCal where id = ?";
            List<Map<String,Object>> nutValues = this.jdbcTemplate.queryForList(sql,rec);
            for(Map<String,Object> map: nutValues) {
                fat+=Long.parseLong(map.get("totalFat").toString());
                carb+=Long.parseLong(map.get("totalCarb").toString());
                protein+=Long.parseLong(map.get("totalProtein").toString());
            }
        }
        double recFat = (double)70/430;
        double recCarb = (double)310/430;
        double recProtein = (double)50/430;

        double fatPerc = ((double)fat/(fat + carb + protein))/recFat;
        double carbPerc = ((double)carb/(fat + carb + protein))/recCarb;
        double proteinPerc = ((double)protein/(fat + carb + protein))/recProtein;

        double leastMin=Math.min(fatPerc, Math.min(carbPerc, proteinPerc));
        String min = "";
        if(leastMin == fatPerc) {min = "totalFat";}
        else if(leastMin == carbPerc) {min = "totalCarb";}
        else {min = "totalProtein";}
        sql = "SELECT TOP 5 recipeID order by desc ?/(totalFat+totalCarb+totalProtein)";
        return this.jdbcTemplate.queryForList(sql,min);
    }
}

package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.client.Ingredient;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.Tag;
import edu.boun.cmpe451.group2.client.User;
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
        String sql = "SELECT id,name,pictureAddress,description,totalFat,totalCarb,totalProtein,totalCal FROM recipes WHERE ownerID = ?";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql, users_id);
        for (Map<String, Object> resultMap : resultList) {
            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();
            recipe.pictureAddress = resultMap.get("pictureAddress").toString();
            recipe.description = resultMap.get("description").toString();

            Object totalFat = resultMap.get("totalFat");
            if(totalFat != null)
                recipe.totalFat = Double.parseDouble(totalFat.toString());

            Object totalCarb = resultMap.get("totalCarb");
            if(totalCarb != null)
                recipe.totalCarb = Double.parseDouble(totalCarb.toString());

            Object totalProtein = resultMap.get("totalProtein");
            if(totalProtein != null)
                recipe.totalProtein = Double.parseDouble(totalProtein.toString());

            Object totalCal = resultMap.get("totalCal");
            if(totalCal != null)
                recipe.totalCal = Double.parseDouble(totalCal.toString());

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
    public ArrayList<Recipe> searchRecipes(String name, List<String> ingredients) {

//      aradigimiz ingredientlardan sistemde bulunanlarin id'leri
        ArrayList<Integer> matchedIngredientIDs = new ArrayList<Integer>();
        for (String ingredientName : ingredients) {
            String sqlSearchIngr = "SELECT * FROM Ingredients WHERE name LIKE ? ";
            List<Map<String,Object>> matchedIngredients = this.jdbcTemplate.queryForList(sqlSearchIngr,"%"+ingredientName+"%");
            for(Map<String,Object> resultMap : matchedIngredients){
                matchedIngredientIDs.add(Integer.parseInt(resultMap.get("id").toString()));
            }
        }


        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql, "%" + name + "%");

//      bu yemek

        for (Map<String, Object> resultMap : resultList) {
            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();

            Object pictureAddress = resultMap.get("pictureAddress");
            if(pictureAddress != null)
                recipe.pictureAddress = pictureAddress.toString();
            Object description = resultMap.get("description");
            if(description != null)
                recipe.description = description.toString();
            Object totalFat = resultMap.get("totalFat");
            if(totalFat != null)
                recipe.totalFat = Double.parseDouble(totalFat.toString());
            Object totalCarb = resultMap.get("totalCarb");
            if(totalCarb != null)
                recipe.totalCarb = Double.parseDouble(totalCarb.toString());
            Object totalProtein = resultMap.get("totalProtein");
            if(totalProtein != null)
                recipe.totalProtein = Double.parseDouble(totalProtein.toString());
            Object totalCal = resultMap.get("totalCal");
            if(totalCal != null)
                recipe.totalCal = Double.parseDouble(totalCal.toString());


//          bu yemegin icerdigi ingredientlarin id'leri

            String sql2 = "SELECT * FROM recipeIngredient WHERE recipeID = ?";
            // Spent 2 hours on this. Problem was for the following statement it was written "sql" instead of "sql2" as a parameter.
            List<Map<String, Object>> resultList2 = this.jdbcTemplate.queryForList(sql2, recipe.id);
            ArrayList<Integer> recipesIngredientIDs = new ArrayList<Integer>();
            for(Map<String, Object> rows : resultList2) {
                recipesIngredientIDs.add(Integer.parseInt(rows.get("ingredientID").toString()));
            }

//          eger aranan ingredientlarin tamami bu yemeginkilerde varsa bu yemegi ekle

            boolean has_all = true;
            for(int matchedID : matchedIngredientIDs) {
                if(!recipesIngredientIDs.contains(matchedID))
                    has_all = false;
            }
            if(has_all)
                recipeList.add(recipe);

        }
        return recipeList;
    }

    public List<Recipe> searchRecipesRandom(int amount) {

        String sql = "SELECT id,name,pictureAddress,description,totalFat,totalCarb,totalProtein,totalCal FROM recipes ORDER BY RAND() LIMIT ?";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,amount);
        List<Recipe> recipeList = new ArrayList<>();

        for (Map<String, Object> resultMap : resultList) {
            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(resultMap.get("id").toString());
            recipe.name = resultMap.get("name").toString();
            recipe.pictureAddress = resultMap.get("pictureAddress").toString();
            recipe.description = resultMap.get("description").toString();

            Object totalFat = resultMap.get("totalFat");
            if(totalFat != null)
                recipe.totalFat = Double.parseDouble(totalFat.toString());

            Object totalCarb = resultMap.get("totalCarb");
            if(totalCarb != null)
                recipe.totalCarb = Double.parseDouble(totalCarb.toString());

            Object totalProtein = resultMap.get("totalProtein");
            if(totalProtein != null)
                recipe.totalProtein = Double.parseDouble(totalProtein.toString());

            Object totalCal = resultMap.get("totalCal");
            if(totalCal != null)
                recipe.totalCal = Double.parseDouble(totalCal.toString());

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

            Object pictureAddress = resultMap.get("pictureAddress");
            if(pictureAddress != null)
                recipe.pictureAddress = pictureAddress.toString();
            Object description = resultMap.get("description");
            if(description != null)
                recipe.description = description.toString();
            Object totalFat = resultMap.get("totalFat");
            if(totalFat != null)
                recipe.totalFat = Double.parseDouble(totalFat.toString());
            Object totalCarb = resultMap.get("totalCarb");
            if(totalCarb != null)
                recipe.totalCarb = Double.parseDouble(totalCarb.toString());
            Object totalProtein = resultMap.get("totalProtein");
            if(totalProtein != null)
                recipe.totalProtein = Double.parseDouble(totalProtein.toString());
            Object totalCal = resultMap.get("totalCal");
            if(totalCal != null)
                recipe.totalCal = Double.parseDouble(totalCal.toString());

            recipeList.add(recipe);
        }
        return recipeList;
    }

    /**
     *
     * @param id id of the recipe
     * @return recipe (if found)
     * @throws ExException when there is no recipe of that id
     */
    public Recipe getRecipe(Long id) throws ExException {
        String sql = "SELECT * FROM recipes WHERE recipes.id = ? ";
        String sqlCount = "SELECT COUNT(*) FROM recipes WHERE recipes.id = ?";
        Map<String, Object> mapCount = this.jdbcTemplate.queryForMap(sqlCount, id);
        if(Integer.parseInt(mapCount.get("COUNT(*)").toString()) == 0){
            throw new ExException(ExError.E_RECIPE_NOT_FOUND);
        }
        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, id);
        Recipe recipe = new Recipe();
        recipe.id = Long.parseLong(map.get("id").toString());
        recipe.name = map.get("name").toString();
        recipe.pictureAddress = map.get("pictureAddress").toString();
        recipe.ownerID = Long.parseLong(map.get("ownerID").toString());
        recipe.description = map.get("description").toString();
        recipe.likes = Long.parseLong(map.get("likes").toString());
        recipe.totalFat = Double.parseDouble(map.get("totalFat").toString());
        recipe.totalCarb = Double.parseDouble(map.get("totalCarb").toString());
        recipe.totalProtein = Double.parseDouble(map.get("totalProtein").toString());
        recipe.totalCal = Double.parseDouble(map.get("totalCal").toString());

        String sql2 = "SELECT * FROM recipeIngredient JOIN Ingredients ON Ingredients.id = recipeIngredient.ingredientID WHERE recipeIngredient.recipeID = ? ";

        List<Map<String, Object>> map2 = this.jdbcTemplate.queryForList(sql2, id);
        HashMap<Ingredient, Long> ingredientMap = new HashMap<Ingredient, Long>();
        for (Map<String, Object> ingredientEntry : map2) {
            Ingredient ingredient = new Ingredient();
            ingredient.id = Long.parseLong(ingredientEntry.get("id").toString());
            ingredient.name = ingredientEntry.get("name").toString();
            ingredient.protein = Double.parseDouble(ingredientEntry.get("protein").toString());
            ingredient.fat = Double.parseDouble(ingredientEntry.get("fat").toString());
            ingredient.carbohydrate = Double.parseDouble(ingredientEntry.get("carb").toString());
            ingredient.calories = Double.parseDouble(ingredientEntry.get("cal").toString());
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
                String sqlGet = "SELECT COUNT(*) FROM Ingredients WHERE id=?";
                Map<String,Object> map =  this.jdbcTemplate.queryForMap(sqlGet,ingr.id);
                int count = Integer.parseInt( map.get("COUNT(*)").toString());
                if(count == 0) {
                    String sql2 = "INSERT INTO Ingredients(id,name,protein,fat,carb,cal,unitName) VALUES(?,?,?,?,?,?,?)";
                    this.jdbcTemplate.update(sql2,ingr.id,ingr.name,ingr.protein,ingr.fat,ingr.carbohydrate,ingr.calories,ingr.unitName);
                }
                long amount = (long)entry.getValue();
                this.jdbcTemplate.update(sql,recipeID,ingr.id,amount);
            }
        }
        if (recipe.tagList.size() > 0) {
            sql = "INSERT INTO recipeTag(recipeID, tag) VALUES(?,?)";

//            for (Tag tag : recipe.tagList) {
//                this.jdbcTemplate.update(sql, recipeID, tag.name);
//            }
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
            sql = "SELECT totalFat, totalCarb, totalProtein, totalCal from recipes where id = ?";
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
        sql = "SELECT recipeID FROM recipes order by ?/(totalFat+totalCarb+totalProtein) DESC LIMIT 5";
        return this.jdbcTemplate.queryForList(sql,min);
    }
}

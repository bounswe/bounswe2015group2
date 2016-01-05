package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.client.Ingredient;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.Tag;
import edu.boun.cmpe451.group2.client.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Recipe Dao class for User's Database Related operation.
 */
@Repository
@Scope("request")
public class RecipeDao extends BaseDao {

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao = null;

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
    public ArrayList<Recipe> searchRecipes(String name, List<String> ingredients,List<String> tags) {

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
        if(tags != null){
            filterListByTags(recipeList,tags);
        }
        return recipeList;
    }

    public void filterListByTags(ArrayList<Recipe> recipeList,List<String> tags) {
        ArrayList<Recipe> temp = new ArrayList<Recipe>();
        String sql = "SELECT * FROM recipeTag WHERE recipeID=?";
        for(Recipe r:recipeList){
            List<Map<String,Object>> rows = this.jdbcTemplate.queryForList(sql,r.id);
            boolean includes = false;
            for(Map<String,Object> row : rows){
                if(tags.contains(row.get("tag").toString())) includes=true;
            }
            if(includes) temp.add(r);
        }
        recipeList = temp;
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
    public ArrayList<Recipe> searchRecipes(String name) throws ExException{
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

        if(recipeList.size()<50){
            AddSemanticallyRelatedRecipes(recipeList);
        }
        if(recipeList.size()<50){
            AddSemanticallyRelatedRecipesClass(recipeList);
        }
        return recipeList;
    }

    public void AddSemanticallyRelatedRecipesClass(ArrayList<Recipe> recipeList) throws ExException {
        HashMap<Long,Recipe> temp = new HashMap<Long,Recipe>();
        for(Recipe r:recipeList){
            temp.put(r.id,r);
        }

        for(Recipe r : recipeList){
            ArrayList<Tag> tags = getTags(r.id);
            for(Tag t : tags){
                ArrayList<Recipe> tempList = searchByTagClass(t);
                for(Recipe r2:tempList){
                    temp.put(r2.id,r2);
                    if(temp.size()>=50) break;
                }
                if(temp.size()>=50) break;
            }
            if(temp.size()>=50) break;
        }

        recipeList.clear();
        for(Recipe r : temp.values()){
            recipeList.add(r);
        }
    }

    public ArrayList<Recipe> searchByTagClass(Tag t) throws ExException{
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sql = "SELECT recipeID FROM recipeTag WHERE parentTag=? ";
        List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql,t.parentTag);
        for(Map<String,Object> row :  list){
            Long id = Long.parseLong(row.get("recipeID").toString());
            Recipe r = getRecipe(id);
            r.id = id;
            recipes.add(r);
        }
        return recipes;
    }

    public ArrayList<Tag> getTags(Long recipeID){
        ArrayList<Tag> tags = new ArrayList<Tag>();
        String sql = "SELECT * FROM recipeTag WHERE recipeID=?";
        List<Map<String,Object>> res = this.jdbcTemplate.queryForList(sql,recipeID);
        for(Map<String,Object> row : res){
            Tag t = new Tag();
            t.id = Long.parseLong(row.get("recipeID").toString());
            t.name = row.get("tag").toString();
            t.parentTag = row.get("parentTag").toString();
            tags.add(t);
        }
        return tags;
    }

    public ArrayList<Tag> getTagsByName(String tagName){
        ArrayList<Tag> tags = new ArrayList<Tag>();
        String sql = "SELECT * FROM recipeTag WHERE tag LIKE ?";
        List<Map<String,Object>> res = this.jdbcTemplate.queryForList(sql,"%" + tagName + "%");
        for(Map<String,Object> row : res){
            Tag t = new Tag();
            t.id = Long.parseLong(row.get("recipeID").toString());
            t.name = row.get("tag").toString();
            t.parentTag = row.get("parentTag").toString();
            tags.add(t);
        }
        return tags;
    }

    public void AddSemanticallyRelatedRecipes(ArrayList<Recipe> recipeList) throws ExException{
        HashMap<Long,Recipe> temp = new HashMap<Long,Recipe>();
        for(Recipe r:recipeList){
            temp.put(r.id,r);
        }
        for(Recipe r : recipeList){
            ArrayList<Tag> tags = getTags(r.id);
            for(Tag t : tags){
                ArrayList<Recipe> tempList = searchByTag(t);
                for(Recipe r2:tempList){
                    temp.put(r2.id,r2);
                    if(temp.size()>=50) break;
                }
                if(temp.size()>=50) break;
            }
            if(temp.size()>=50) break;
        }
        recipeList.clear();
        for(Recipe r : temp.values()){
            recipeList.add(r);
        }
    }

    public ArrayList<Recipe> searchByTag(Tag t) throws ExException {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sql = "SELECT recipeID FROM recipeTag WHERE tag=? ";
        List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql,t.name);
        for(Map<String,Object> row :  list){
            Long id = Long.parseLong(row.get("recipeID").toString());
            Recipe r = getRecipe(id);
            r.id = id;
            recipes.add(r);
        }
        return recipes;
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

        String sql3 = "SELECT * FROM recipeTag  WHERE recipeID = ?";

        List<Map<String, Object>> map3 = this.jdbcTemplate.queryForList(sql3, id);
        for (Map<String, Object> tagEntry : map3) {
            Tag tag = new Tag();
            tag.name = tagEntry.get("tag").toString();
            tag.parentTag = tagEntry.get("parentTag").toString();
            recipe.tagList.add(tag);
        }

        return recipe;
    }

    public List<Recipe> getRecipesAll() throws ExException {
        String sql = "SELECT * FROM recipes;";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        List<Map<String, Object>> resultList = this.jdbcTemplate.queryForList(sql);
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
        System.out.println(recipe.tagList.size());
        if (recipe.tagList.size() > 0) {
            sql = "INSERT INTO recipeTag(recipeID, tag, parentTag) VALUES(?,?,?)";

            for (Tag tag : recipe.tagList) {
                System.out.println(tag.name);
                this.jdbcTemplate.update(sql, recipeID, tag.name, tag.parentTag);
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
        Double fat = 0d;
        Double carb = 0d;
        Double protein=0d;
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
                fat+=Double.parseDouble(map.get("totalFat").toString());
                carb+=Double.parseDouble(map.get("totalCarb").toString());
                protein+=Double.parseDouble(map.get("totalProtein").toString());
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
        sql = "SELECT id FROM recipes order by "+min+"/(totalFat+totalCarb+totalProtein) DESC LIMIT 5";
        return this.jdbcTemplate.queryForList(sql);
    }

    /**
     * this method first finds the liked tags of a user, and extracts disliked tags and allergies tags
     * than brings the recipes of refined tags
     * than selects maximum 5 of them and returns them as recommended
     * @param userID id of the user
     * @return arrayList of recipes at maximum 5
     * @throws ExException when an integer cannot be converted to long
     */
    public ArrayList<Recipe> getRecommendationsPreferences(String userID) throws ExException {
        ArrayList<Tag> tagsLiked = userDao.getLikes(userID);
        ArrayList<Tag> tagsDisliked = userDao.getDislikes(userID);
        ArrayList<Tag> allergies = userDao.getAllergies(userID);
        ArrayList<Tag> refined = new ArrayList<Tag>();
        ArrayList<Recipe> recommendations = new ArrayList<Recipe>();
        for(Tag t: tagsLiked){
            boolean contains = false;
            for(Tag t2:tagsDisliked){
                if(t2.name.equals(t.name)){
                    if(t.parentTag.equals("") || t2.parentTag.equals("") || t.parentTag.equals(t2.parentTag))
                    {
                        contains = true;
                        break;
                    }
                }
            }
            if(!contains){
                for(Tag t2:allergies){
                    if(t2.name.equals(t.name)){
                        if(t.parentTag.equals("") || t2.parentTag.equals("") || t.parentTag.equals(t2.parentTag))
                        {
                            contains = true;
                            break;
                        }
                    }
                }
            }
            if(!contains) refined.add(t);
        }
        //upper part was unneccessary :(
        //refined tags are calculated
        //find recipes here
        ArrayList<Integer> recipeIDs = new ArrayList<Integer>();
        for(Tag t:refined) {
            mergeRecipeIDs(recipeIDs, getRecipeIDsByTag(t));
        }
        ArrayList<Integer> dislikedRecipeIDs= new ArrayList<>();
        for(Tag t:tagsDisliked){
            mergeRecipeIDs(dislikedRecipeIDs,getRecipeIDsByTag(t));
        }
        for(Tag t:allergies){
            mergeRecipeIDs(dislikedRecipeIDs,getRecipeIDsByTag(t));
        }
        recipeIDs = distractRecipes(recipeIDs,dislikedRecipeIDs);
        for(Integer i: recipeIDs){
            recommendations.add(getRecipe(i.longValue()));
        }
        // TODO add semantically related recommendations if size is less than 5
        int size = recommendations.size();
        ArrayList<Recipe> refinedRecommendations = new ArrayList<Recipe>();
        if(size > 5){
            Random random = new Random();
            for(int i=0;i<5;i++){
                int r = random.nextInt(size);
                refinedRecommendations.add(recommendations.get(r));
                recommendations.remove(r);
                size--;
            }
        }else{
            return recommendations;
        }

        return refinedRecommendations;
    }

    private ArrayList<Integer> distractRecipes(ArrayList<Integer> recipeIDs, ArrayList<Integer> dislikedRecipeIDs) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i: recipeIDs){
            if(!dislikedRecipeIDs.contains(i)) temp.add(i);
        }
        return temp;
    }

    public void mergeRecipeIDs(ArrayList<Integer> list1,ArrayList<Integer> list2){
        for(int i : list2){
            if(!list1.contains(i)){
                list1.add(i);
            }
        }
    }
    public ArrayList<Integer> getRecipeIDsByTag(Tag t){
        String sql = "SELECT recipeID FROM recipeTag WHERE tag=?";
        List<Map<String,Object>> dbResult = this.jdbcTemplate.queryForList(sql,t.name);
        ArrayList<Integer> recipeIDs = new ArrayList<Integer>();
        for(Map<String,Object> row: dbResult){
            recipeIDs.add(Integer.parseInt(row.get("recipeID").toString()));
        }
        return recipeIDs;
    }
}

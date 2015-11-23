package edu.boun.cmpe451.group2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.boun.cmpe451.group2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;

/**
 * API Controller Class
 * This class includes following functions
 */
@Controller
@RequestMapping("api")
@Scope("request")
public class APIController implements ControllerInterface{

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;

    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;

    /**
     * Checks login
     *
     * @param email    registered email
     * @param password password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(
            @RequestParam String email,
            @RequestParam String password) {

        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            String api_key = userModel.login(email, password);
            result.put("type", "SUCCESS");
            result.put("content", ExError.S_OK);
            result.put("api_key", api_key);

            return gson.toJson(result);

        } catch (Exception e) {
            e.printStackTrace();

            result.put("type", "ERROR");

            if (e instanceof ExException)
                result.put("content", ((ExException) e).getErrCode());
            else
                result.put("content", ExError.E_UNKNOWN);

            return gson.toJson(result);
        }
    }

    @RequestMapping("/getuser")
    public @ResponseBody User getUser(@RequestParam String api_key){
        User user = userModel.getUser(api_key);
        return user;
    }

    /**
     * Register user to database
     * @param user     User object whose email and password fields must be initialized
     * @return
     */
    @RequestMapping("/signup")
    @ResponseBody
    public String signup(@RequestBody User user) {

        String email = user.email;
        String password = user.passwd;
        String full_name = "";
        if(user.full_name != null)
            full_name=user.full_name;
        String username = "";
        if(user.full_name != null)
            username=user.username;
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            String api_key = userModel.signup(email, password, full_name, username);

            result.put("type", "SUCCESS");
            result.put("content", ExError.S_OK);
            result.put("api_key", api_key);

            return gson.toJson(result);

        } catch (Exception e) {
            e.printStackTrace();

            result.put("type", "ERROR");

            if (e instanceof ExException)
                result.put("content", ((ExException) e).getErrCode());
            else
                result.put("content", ExError.E_UNKNOWN);

            return gson.toJson(result);
        }
    }

    /**
     * Adds a recipe to the db
     * @param recipe recipe to be added
     * @return returns json string
     */
    @RequestMapping("/addrecipe")
    public String addrecipe(@RequestBody Recipe recipe) {
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            recipeModel.addRecipe(recipe.name,recipe.ownerID,recipe.IngredientAmountMap,recipe.pictureAddress,recipe.description);
            result.put("type","SUCCESS");
            result.put("content","Recipe Added");
        }
        catch (Exception e){
            e.printStackTrace();

            result.put("type", "ERROR");

            if (e instanceof ExException)
                result.put("content", ((ExException) e).getErrCode());
            else
                result.put("content", ExError.E_UNKNOWN);

            return gson.toJson(result);
        }

        return gson.toJson(result);

    }

    /**
     * deletes a recipe
     * @param recipeID id of the recipe to be deleted
     * @return
     */
    public String deleteRecipe(
            @RequestParam Long recipeID
    ) {
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            recipeModel.deleteRecipe(recipeID);
            result.put("type", "SUCCESS");
            result.put("content", "Recipe Deleted");
        } catch (Exception e) {
            e.printStackTrace();

            result.put("type", "ERROR");

            if (e instanceof ExException)
                result.put("content", ((ExException) e).getErrCode());
            else
                result.put("content", ExError.E_UNKNOWN);

            return gson.toJson(result);
        }

        return gson.toJson(result);
    }

    @RequestMapping("recipe/list")
    public @ResponseBody

    //TODO: This function needs to return ArrayList<RecipeModel>, but the recipes function in the HomeController requires a List<Map<String, Object>, Find a way to satisfy both APIs.
    List<Recipe> getRecipes(@RequestParam String api_key, @RequestParam Long users_id) {
        // todo api_key control
        return recipeModel.getRecipes(users_id);
    }

    @RequestMapping("recipe/get")
    public @ResponseBody Recipe getRecipe
            (@RequestParam String api_key, @RequestParam Long recipe_id) throws Exception {
        // todo api_key control
        return recipeModel.getRecipe(recipe_id);
    }
}
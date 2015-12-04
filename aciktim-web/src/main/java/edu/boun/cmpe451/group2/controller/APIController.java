package edu.boun.cmpe451.group2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.boun.cmpe451.group2.client.*;
import edu.boun.cmpe451.group2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;

/**
 * API Controller Class
 * This class includes following functions
 */
@Controller
@RequestMapping("api")
@Scope("request")
public class APIController implements ControllerInterface {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;

    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;

    @Qualifier("menuModel")
    @Autowired
    private MenuModel menuModel = null;
    /**
     * Checks login
     *
     * @param email    registered email
     * @param password password
     * @return
     */
    @RequestMapping(LOGIN_PATH)
    public @ResponseBody
    ApiResponse login(
            @RequestParam String email,
            @RequestParam String password) {

        ApiResponse apiResponse = new ApiResponse();
        try {
            String api_key = userModel.login(email, password);
            apiResponse.status = ApiResponse.STATUS.OK;
            apiResponse.message = ExError.S_OK;
            apiResponse.api_key = api_key;

            return apiResponse;

        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.status= ApiResponse.STATUS.ERROR;

            if (e instanceof ExException)
                apiResponse.message =  ((ExException) e).getErrCode();
            else
                apiResponse.message =  ExError.E_UNKNOWN;

            return apiResponse;
        }
    }

    @RequestMapping("/getuser")
    public @ResponseBody
    User getUser(@RequestParam String api_key){
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
    public ApiResponse signup(@RequestBody User user) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            String api_key = userModel.signup(user);
            apiResponse.status = ApiResponse.STATUS.OK;
            apiResponse.api_key = api_key;

            return apiResponse;

        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.status= ApiResponse.STATUS.ERROR;

            if (e instanceof ExException)
                apiResponse.message =  ((ExException) e).getErrCode();
            else
                apiResponse.message =   ExError.E_UNKNOWN;

            return apiResponse;
        }
    }

    /**
     * Adds a recipe to the db
     * @param recipe recipe to be added
     * @return returns json string
     */
    @RequestMapping("/addrecipe")
    public @ResponseBody
    ApiResponse addrecipe(@RequestBody Recipe recipe) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            recipeModel.addRecipe(recipe);
            apiResponse.status = ApiResponse.STATUS.OK;
            apiResponse.message ="Recipe Added";
            return apiResponse;
        }
        catch (Exception e){
            e.printStackTrace();

            apiResponse.status = ApiResponse.STATUS.ERROR;

            if (e instanceof ExException)
                apiResponse.message = ((ExException) e).getErrCode();
            else
                apiResponse.message= ExError.E_UNKNOWN;

            return apiResponse;
        }

    }

    /**
     * deletes a recipe
     * @param recipeID id of the recipe to be deleted
     * @return
     */
    public @ResponseBody
    ApiResponse deleteRecipe(
            @RequestParam Long recipeID
    ) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            recipeModel.deleteRecipe(recipeID);
            apiResponse.status = ApiResponse.STATUS.OK;
            apiResponse.message ="Recipe Deleted";
            return apiResponse;
        }catch (Exception e){
            e.printStackTrace();

            apiResponse.status = ApiResponse.STATUS.ERROR;

            if (e instanceof ExException)
                apiResponse.message = ((ExException) e).getErrCode();
            else
                apiResponse.message= ExError.E_UNKNOWN;

            return apiResponse;
        }
    }

    @RequestMapping("recipe/list")
    public @ResponseBody List<Recipe> getRecipes(@RequestParam Long users_id) {
        return recipeModel.getRecipes(users_id);
    }

    @RequestMapping("recipe/get")
    public @ResponseBody Recipe getRecipe(@RequestParam Long recipe_id) throws Exception {
        return recipeModel.getRecipe(recipe_id);
    }

    @RequestMapping(RECIPE_SVC_PATH + "/update")
    public @ResponseBody
    ApiResponse updateRecipe(@RequestBody Recipe recipe) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            recipeModel.updateRecipe(recipe);
            apiResponse.status = ApiResponse.STATUS.OK;
            apiResponse.message = "Recipe Added";
            return apiResponse;
        }
        catch (Exception e){
            e.printStackTrace();

            apiResponse.status = ApiResponse.STATUS.ERROR;

            if (e instanceof ExException)
                apiResponse.message = ((ExException) e).getErrCode();
            else
                apiResponse.message= ExError.E_UNKNOWN;

            return apiResponse;
        }
    }

    @RequestMapping(USER_SVC_PATH + "/recommendations")
    public @ResponseBody List<Recipe> getRecommendations(@RequestBody User user){

        try {
            return recipeModel.getRecommendations(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * default search function
     * @param name name of the recipe
     * @return an arraylist of recipe object
     */
    @RequestMapping("/search")
    public @ResponseBody ArrayList<Recipe> search(@RequestParam String name){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try {
            recipes = recipeModel.searchRecipes(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }

    /**
     * advanced search
     * filters the results by the given ingredient names
     * this filter has AND relationship between the ingredient names
     * @param name name of the recipe
     * @param ingrNames ingredient names to be included in the recipe
     * @return an array list of recipes that satisfies the criteria
     */
    @RequestMapping("/advancedSearch")
    public @ResponseBody ArrayList<Recipe> search(@RequestParam String name,@RequestParam ArrayList<String> ingrNames){
        ArrayList<Recipe> result = new ArrayList<Recipe>();
        try {
            result = recipeModel.searchRecipes(name,ingrNames);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * adds a menu
     * @param menu menu to be added
     * @return type and content of the result in a hashmap
     */
    @RequestMapping(USER_SVC_PATH+"/addMenu")
    public @ResponseBody
    ApiResponse addMenu(@RequestParam Menu menu){
        ApiResponse apiResponse = new ApiResponse();
        try {
            menuModel.AddMenu(menu);
            apiResponse.status = ApiResponse.STATUS.OK;
            apiResponse.message = "Menu Added!";
            return apiResponse;
        }
        catch (Exception e){
            e.printStackTrace();

            apiResponse.status = ApiResponse.STATUS.ERROR;

            if (e instanceof ExException)
                apiResponse.message = ((ExException) e).getErrCode();
            else
                apiResponse.message= ExError.E_UNKNOWN;

            return apiResponse;
        }
    }

    /**
     * gets menus of the user
     * @param api_key api_key of the user
     * @return a hashmap of long and menu (keys are ids, values are menues)
     */
    @RequestMapping(USER_SVC_PATH + "/getMenus")
    public @ResponseBody HashMap<Long,Menu> getMenusByApiKey(@RequestParam String api_key){
        HashMap<Long,Menu> menus = new HashMap<Long,Menu>();
        try{
            menus = menuModel.GetMenusByApiKey(api_key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return menus;
    }
}
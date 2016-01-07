package edu.boun.cmpe451.group2.controller;

import java.text.SimpleDateFormat;
import java.util.*;

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

        String email = user.email;
        String password = user.passwd;
        String full_name = "";
        if(user.full_name != null)
            full_name=user.full_name;
        String username = "";
        if(user.username != null)
            username=user.username;
        boolean isInst = user.isInst;
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
    public @ResponseBody List<Recipe> getRecipes(@RequestParam Long user_id) {
        return recipeModel.getRecipes(user_id);
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
    public @ResponseBody ArrayList<Recipe> getRecommendations(@RequestParam Long user_id){

        try {
            return recipeModel.getRecommendations(user_id+"");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<Recipe>();
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
     * this method helps a REGISTERED user to perform advanced search, and reorders the result by the preferences of the given user
     * if the bounds are not given by the user please supply dummy values like 0 for lower bound and 9999 for upperbound
     * @param keyword keyword of the search, can be empty
     * @param ingredients ingredients that ALL HAS TO BE in the result list, these ingredients are keywords
     * @param isInst is made by a user or restaurant
     * @param totalFatUpper upper bound for fat
     * @param totalCarbUpper upper bound for carb
     * @param totalProteinUpper upper bound for protein
     * @param totalCalUpper upper bound for calories
     * @param totalFatLower lower bound for fat
     * @param totalCarbLower lower bound for carb
     * @param totalProteinLower lower bound for protein
     * @param totalCalLower lower bound for calories
     * @param tags tags that ALL HAS TO BE in the result list
     * @param userID user id of the performer, this is for preferences
     * @return an arraylist of recipes that matches the given query
     */
    @RequestMapping("/advancedSearch")
    public @ResponseBody ArrayList<Recipe> advancedSearch(@RequestParam String keyword,@RequestParam List<String> ingredients,@RequestParam Boolean isInst,@RequestParam Double totalFatUpper,@RequestParam Double totalCarbUpper, @RequestParam Double totalProteinUpper, @RequestParam Double totalCalUpper, @RequestParam Double totalFatLower, @RequestParam Double totalCarbLower, @RequestParam Double totalProteinLower, @RequestParam Double totalCalLower, @RequestParam List<String> tags,@RequestParam Long userID ) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try {
            recipes = recipeModel.advancedSearchRecipes(keyword, ingredients, isInst, totalFatUpper, totalCarbUpper, totalProteinUpper, totalCalUpper, totalFatLower, totalCarbLower, totalProteinLower, totalCalLower, tags, userID);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return recipes;
    }

        @RequestMapping("/searchRestaurant")
    public @ResponseBody ArrayList<User> searchRestaurants(@RequestParam String name){
        ArrayList<User> restaurants = new ArrayList<User>();
        try {
            restaurants = userModel.searchRestaurants(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    //this advanced search is old
    /*
     * advanced search
     * filters the results by the given ingredient names
     * this filter has AND relationship between the ingredient names
     * @param name name of the recipe
     * @param ingrNames ingredient names to be included in the recipe
     * @return an array list of recipes that satisfies the criteria
    @RequestMapping("/advancedSearch")
    public @ResponseBody List<Recipe> search(@RequestParam String name,@RequestParam List<String> ingrNames){
        ArrayList<Recipe> result = new ArrayList<Recipe>();
        try {
            // TODO : Uncomment the following line and fix the incompaitble type issue on ingrNames
            //result = recipeModel.searchRecipes(name,ingrNames);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
     */

    /**
     * this method helps a user to consume a recipe
     * @param userID id of the user
     * @param recipeID id of the recipe to be consume
     * @param date date of the consumption
     * @return an ApiResponse containing the status and the message
     */
    @RequestMapping("/consume")
    public @ResponseBody ApiResponse consume(@RequestParam Long userID,@RequestParam Long recipeID,@RequestParam String date){
        ApiResponse response = new ApiResponse();
        try{
            Calendar queriedCal = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            try
            {
                String[] arr = date.split("/");
                arr[1] = Integer.toString(Integer.parseInt(arr[1])+1);
                date = arr[0]+"/"+arr[1]+"/"+arr[2];
                Date queried_date = formatter.parse(date);
                queriedCal = Calendar.getInstance();
                queriedCal.setTime(queried_date);
            }catch (Exception e){
                e.printStackTrace();
            }
            boolean status = userModel.consume(userID,recipeID,queriedCal);
            response.status = ApiResponse.STATUS.OK;
            response.message ="Recipe Consumed!";
        }catch(Exception e){
            e.printStackTrace();
            response.status = ApiResponse.STATUS.ERROR;
            response.message = "Recipe couldn't be consumed";
        }
        return response;

    }
    /**
     * This method returns the daily consumption of a user given that day
     * if there is an exception it returns an empty list
     * @param userID id of the user
     * @param date date of the day in the format of yyyy/MM/dd
     * @return returns arraylist of recipes
     */
    @RequestMapping(USER_SVC_PATH+"/getDailyConsumption")
    public @ResponseBody ArrayList<Recipe> getDailyConsumption(@RequestParam Long userID,@RequestParam String date){
        try{
            Calendar queriedCal = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            try
            {
                String[] arr = date.split("/");
                arr[1] = Integer.toString(Integer.parseInt(arr[1])+1);
                date = arr[0]+"/"+arr[1]+"/"+arr[2];
                Date queried_date = formatter.parse(date);
                queriedCal = Calendar.getInstance();
                queriedCal.setTime(queried_date);
            }catch (Exception e){
                    e.printStackTrace();
            }

            return userModel.getDailyConsumption(userID,queriedCal);
        } catch (ExException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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
     * @param ownerId ownerId of the user
     * @return a hashmap of long and menu (keys are ids, values are menues)
     */
    @RequestMapping(USER_SVC_PATH + "/getMenus")
    public @ResponseBody ArrayList<Menu> getMenus(@RequestParam Long ownerId){
        ArrayList<Menu> menus = new ArrayList<Menu>();
        try{
            menus = menuModel.GetMenus(ownerId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return menus;
    }

    @RequestMapping(USER_SVC_PATH +"/editProfile")
    public @ResponseBody ApiResponse editProfile(@RequestParam String api_key,@RequestParam String username,@RequestParam String email,@RequestParam ArrayList<Tag> likes, @RequestParam ArrayList<Tag> dislikes, @RequestParam ArrayList<Tag> allergies ){
        ApiResponse response = new ApiResponse();
        try{
            User user = userModel.getUser(api_key);
            userModel.editProfile(Long.parseLong(user.id),username,email,likes,dislikes,allergies);
        }catch(Exception e){
            e.printStackTrace();
            response.status = ApiResponse.STATUS.ERROR;
            if (e instanceof ExException)
                response.message = ((ExException) e).getErrCode();
            else
                response.message= ExError.E_UNKNOWN;

            return response;
        }
        return response;
    }

}
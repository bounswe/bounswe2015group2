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
import com.google.gson.Gson;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import retrofit.http.Query;

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
    public @ResponseBody Response login(
            @RequestParam String email,
            @RequestParam String password) {

        Response response = new Response();
        try {
            String api_key = userModel.login(email, password);
            response.status = Response.STATUS.OK;
            response.message = ExError.S_OK;
            response.api_key = api_key;

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.status= Response.STATUS.ERROR;

            if (e instanceof ExException)
                response.message =  ((ExException) e).getErrCode();
            else
                response.message =  ExError.E_UNKNOWN;

            return response;
        }
    }

    @RequestMapping("/getuser")
    public @ResponseBody
    User getUser(@RequestParam String api_key){
        User user = userModel.getUser(api_key);
        return user;
    }

    /**
     * this method returns a user object by userID
     * @param userID id of the user
     * @return a user object or if it gets an exception it returns an empty user
     */
    @RequestMapping("/user")
    public User getUser(@Query(API_KEY_PARAMETER) Long userID) {
        try{
            return userModel.getUserByID(userID);
        }catch(Exception e){
            e.printStackTrace();
            return new User();
        }
    }

    /**
     * Register user to database
     * @param user     User object whose email and password fields must be initialized
     * @return
     */
    @RequestMapping("/signup")
    @ResponseBody
    public Response signup(@RequestBody User user) {

        String email = user.email;
        String password = user.passwd;
        String full_name = "";
        if(user.full_name != null)
            full_name=user.full_name;
        String username = "";
        if(user.username != null)
            username=user.username;
        boolean isInst = user.isInst;
        Response response = new Response();
        try {
            String api_key = userModel.signup(user);
            response.status = Response.STATUS.OK;
            response.api_key = api_key;

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.status=Response.STATUS.ERROR;

            if (e instanceof ExException)
                response.message =  ((ExException) e).getErrCode();
            else
                response.message =   ExError.E_UNKNOWN;

            return response;
        }
    }

    /**
     * this method gives a recommendation based on daily consumption
     * if there is an exception it returns an empty list of recipes
     * @param users_id id of the user
     * @return list of recipes that contains recommended recipes
     */
    @RequestMapping(USER_SVC_PATH + "/recommendations")
    public List<Recipe> getRecommendations(@RequestParam Long users_id) {

        try {
            User user = new User();
            user.id = users_id.toString();
            return recipeModel.getRecommendations(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<Recipe>();
    }

    /**
     * Adds a recipe to the db
     * @param recipe recipe to be added
     * @return returns json string
     */
    @RequestMapping("/addrecipe")
    public @ResponseBody Response addrecipe(@RequestBody Recipe recipe) {
        Response response = new Response();
        try {
            recipeModel.addRecipe(recipe);
            response.status = Response.STATUS.OK;
            response.message ="Recipe Added";
            return response;
        }
        catch (Exception e){
            e.printStackTrace();

            response.status = Response.STATUS.ERROR;

            if (e instanceof ExException)
                response.message = ((ExException) e).getErrCode();
            else
                response.message= ExError.E_UNKNOWN;

            return response;
        }

    }

    /**
     * deletes a recipe
     * @param recipeID id of the recipe to be deleted
     * @return
     */
    public @ResponseBody Response deleteRecipe(
            @RequestParam Long recipeID
    ) {
        Response response = new Response();
        try {
            recipeModel.deleteRecipe(recipeID);
            response.status = Response.STATUS.OK;
            response.message ="Recipe Deleted";
            return response;
        }catch (Exception e){
            e.printStackTrace();

            response.status = Response.STATUS.ERROR;

            if (e instanceof ExException)
                response.message = ((ExException) e).getErrCode();
            else
                response.message= ExError.E_UNKNOWN;

            return response;
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
    public @ResponseBody Response updateRecipe(@RequestBody Recipe recipe) {
        Response response = new Response();
        try {
            recipeModel.updateRecipe(recipe);
            response.status = Response.STATUS.OK;
            response.message = "Recipe Added";
            return response;
        }
        catch (Exception e){
            e.printStackTrace();

            response.status = Response.STATUS.ERROR;

            if (e instanceof ExException)
                response.message = ((ExException) e).getErrCode();
            else
                response.message= ExError.E_UNKNOWN;

            return response;
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
     * This method returns the daily consumption of a user given that day
     * if there is an exception it returns an empty list
     * @param userID id of the user
     * @param date date of the day in the format of yyyy/MM/dd
     * @return returns arraylist of recipes
     */
    @RequestMapping(USER_SVC_PATH+"/getDailyConsumption")
    public @ResponseBody ArrayList<Recipe> getDailyConsumption(Long userID,String date){
        try{
            Calendar queriedCal = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            try
            {
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
    public @ResponseBody Response addMenu(@RequestParam Menu menu){
        Response response = new Response();
        try {
            menuModel.AddMenu(menu);
            response.status = Response.STATUS.OK;
            response.message = "Menu Added!";
            return response;
        }
        catch (Exception e){
            e.printStackTrace();

            response.status = Response.STATUS.ERROR;

            if (e instanceof ExException)
                response.message = ((ExException) e).getErrCode();
            else
                response.message= ExError.E_UNKNOWN;

            return response;
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
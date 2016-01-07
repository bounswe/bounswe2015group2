package edu.boun.cmpe451.group2.android.api;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.*;

/**
 * This class gives the api methods are provided. It is an implementation of the retrofit framework
 *
 * @author Mustafa Taha Kocyigit
 */
public interface ControllerInterface {

    public static final String API_KEY_PARAMETER = "api_key";

    public static final String RECIPE_ID_PARAMETER = "name";

    public static final String DATE_PARAMETER = "date";

    public static final String USER_ID_PARAMETER = "users_id";

    public static final String PASSWORD_PARAMETER = "password";

    public static final String EMAIL_PARAMETER = "email";

    public static final String LOGIN_PATH = "login";

    public static final String SIGN_UP_PATH = "signup";

    public static final String USER_SVC_PATH = "getuser";

    public static final String USER_PATH = "user";

    public static final String RECIPE_SVC_PATH = "recipe";

    public static final String RECIPE_TITLE_SEARCH_PATH = "search";

    public static final String RECIPE_LIST_BY_USER_PATH = RECIPE_SVC_PATH + "/list";

    /**
     * Sends a post request and expexts a response from the backend in the form of String
     *
     * @param email email of the user to be logged in
     * @param password password of the user
     * @return acknowledgement or error from the backend
     */
    @POST(LOGIN_PATH)
    Call<ApiResponse> login(@Query(EMAIL_PARAMETER) String email, @Query(PASSWORD_PARAMETER) String password);


    /**
     * Sends a get request in order to get information about the user using the api key of the user.
     *
     * @param api_key api key of the user
     * @return user object for the user
     */
    @GET(USER_SVC_PATH)
    Call<User> getUser(@Query(API_KEY_PARAMETER) String api_key);

    /**
     * Sends a post request to the backend and registers the user
     * @param user User object whose username and password fields need to be initialized
     * @return The response should conain the api key if the operation is successful
     */
    @POST(USER_SVC_PATH)
    Call<ApiResponse> signup(@Body User user);

    /**
     * Adds a recipe to the db
     * @param recipe recipe to be added
     * @return returns json string
     */
    @POST(RECIPE_SVC_PATH)
    Call<ApiResponse> addrecipe(@Body Recipe recipe);

    /**
     * deletes a recipe
     * @param recipeID id of the recipe to be deleted
     * @return
     */
    @DELETE(RECIPE_SVC_PATH)
    String deleteRecipe(@Query(RECIPE_ID_PARAMETER) Long recipeID);

    /**
     * Sends a get request to get all recipes that a user has created
     * @param users_id that the database has assigned to the user
     * @return list of recipes that a user has created
     */
    @GET(RECIPE_LIST_BY_USER_PATH)
    Call<List<Recipe>> getRecipes(@Query(USER_ID_PARAMETER) Long users_id);

    /**
     * Sends a get request to get the details of a recipe using the recipe id
     * @param recipe_id that the db has assigned to a recipe
     * @return recipe object
     * @throws Exception
     */
    @GET(RECIPE_SVC_PATH)
    Call<Recipe> getRecipe(@Query(RECIPE_ID_PARAMETER) Long recipe_id) throws Exception;

    /**
     * Sends a put request to update details of a recipe
     * @param recipe new recipe object
     * @return acknowledgement
     */
    @PUT(RECIPE_SVC_PATH + "/update")
    Call<ApiResponse> updateRecipe(@Body Recipe recipe);

    /**
     * Sends a post request in order to get recommendation for a user
     * @param user User to get recommendations
     * @return List of recipes that are recommended
     */
    @GET(USER_SVC_PATH + "/recommendations")
    Call<List<Recipe>> getRecommendations(@Body User user);

    /**
     * default search function
     * @param name name of the recipe
     * @return an arraylist of recipe object
     */
    @GET(RECIPE_TITLE_SEARCH_PATH)
    Call<List<Recipe>> search(@Query(RECIPE_ID_PARAMETER) String name);

    /**
     * advanced search
     * filters the results by the given ingredient names
     * this filter has AND relationship between the ingredient names
     * @param name name of the recipe
     * @param ingrNames ingredient names to be included in the recipe
     * @return an array list of recipes that satisfies the criteria
     */
    @GET("/advancedSearch")
    ArrayList<Recipe> search(@Query(RECIPE_ID_PARAMETER) String name,@Query(RECIPE_ID_PARAMETER) List<String> ingrNames);

    /**
     * adds a menu
     * @param menu menu to be added
     * @return type and content of the result in a hashmap
     */
    @POST(USER_SVC_PATH+"/addMenu")
    ApiResponse addMenu(@Query(RECIPE_ID_PARAMETER) Menu menu);

    /**
     * gets menus of the user
     * @param api_key api_key of the user
     * @return a hashmap of long and menu (keys are ids, values are menues)
     */
     @GET(USER_SVC_PATH+"/getMenus")
     List<Menu> getMenus(@Query(RECIPE_ID_PARAMETER) Long ownerId);

    /**
     * This method returns the daily consumption of a user given that day
     * if there is an exception it returns an empty list
     * @param userID id of the user
     * @param date date of the day in the format of yyyy/MM/dd
     * @return returns arraylist of recipes
     */
    @GET(USER_PATH + "/getDailyConsumption")
    Call<List<Recipe>> getDailyConsumption(@Query(USER_ID_PARAMETER) Long userID, @Query(DATE_PARAMETER) String date);
    
    @POST("/consume")
    ApiResponse consume(@Query(USER_ID_PARAMETER) Long userID,@Query(RECIPE_ID_PARAMETER) Long recipeID,@Query(DATE_PARAMETER) String date);

    @GET("/searchRestaurant")
    List<User> searchRestaurants(@Query("name") String name);

    @PUT(USER_SVC_PATH +"/editProfile")
    ApiResponse editProfile(@Query(API_KEY_PARAMETER) String api_key,@Query("username") String username,
                            @Query(EMAIL_PARAMETER) String email,@Query("likes") List<Tag> likes,
                            @Query("dislikes") List<Tag> dislikes, @Query("allergies") List<Tag> allergies );

}

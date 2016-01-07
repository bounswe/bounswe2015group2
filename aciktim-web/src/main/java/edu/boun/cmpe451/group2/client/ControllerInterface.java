package edu.boun.cmpe451.group2.client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.http.*;

/**
 * This class includes all documentation about the API
 * extensive documentation should be written about the functionality of every method
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

    @POST(LOGIN_PATH)
    ApiResponse login(@Query(EMAIL_PARAMETER) String email, @Query(PASSWORD_PARAMETER) String password);

    @GET(USER_SVC_PATH)
    User getUser(@Query(API_KEY_PARAMETER) String api_key);

    @POST(USER_SVC_PATH)
    ApiResponse signup(@Body User user);

    @POST(RECIPE_SVC_PATH)
    ApiResponse addrecipe(@Body Recipe recipe);

    @DELETE(RECIPE_SVC_PATH)
    ApiResponse deleteRecipe(@Query(RECIPE_ID_PARAMETER) Long recipeID);

    @GET(RECIPE_LIST_BY_USER_PATH)
    List<Recipe> getRecipes(@Query(USER_ID_PARAMETER) Long users_id);

    @GET(RECIPE_SVC_PATH)
    Recipe getRecipe(@Query(RECIPE_ID_PARAMETER) Long recipe_id) throws Exception;

    @PUT(RECIPE_SVC_PATH + "/update")
    ApiResponse updateRecipe(@Body Recipe recipe);

    @GET(USER_SVC_PATH + "/recommendations")
    List<Recipe> getRecommendations(@Body User user);

    @GET("/search")
    List<Recipe> search(@Query(RECIPE_ID_PARAMETER) String name);

    @GET("/advancedSearch")
    List<Recipe> search(@Query(RECIPE_ID_PARAMETER) String name,@Query(RECIPE_ID_PARAMETER) List<String> ingrNames);

    @POST(USER_SVC_PATH + "/addMenu")
    ApiResponse addMenu(@Query(RECIPE_ID_PARAMETER) Menu menu);

    @GET(USER_SVC_PATH+"/getMenus")
    List<Menu> getMenus(@Query(RECIPE_ID_PARAMETER) Long ownerId);

    @GET(USER_SVC_PATH+"/getDailyConsumption")
    List<Recipe> getDailyConsumption( @Query(USER_ID_PARAMETER) Long userID, @Query(DATE_PARAMETER)String date);

    @POST("/consume")
    ApiResponse consume(@Query(USER_ID_PARAMETER) Long userID,@Query(RECIPE_ID_PARAMETER) Long recipeID,@Query(DATE_PARAMETER) String date);

    @GET("/searchRestaurant")
    List<User> searchRestaurants(@Query("name") String name);

    @PUT(USER_SVC_PATH +"/editProfile")
    ApiResponse editProfile(@Query(API_KEY_PARAMETER) String api_key,@Query("username") String username,
                            @Query(EMAIL_PARAMETER) String email,@Query("likes") ArrayList<Tag> likes,
                            @Query("dislikes") ArrayList<Tag> dislikes, @Query("allergies") ArrayList<Tag> allergies );

}

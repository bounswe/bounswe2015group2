package edu.boun.cmpe451.group2.controller;


import edu.boun.cmpe451.group2.model.Recipe;
import edu.boun.cmpe451.group2.model.User;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
/**
 * This class includes all documentation about the API
 * extensive documentation should be written about the functionality of every method
 *
 * @author Mustafa Taha Kocyigit
 */
public interface ControllerInterface {

    public static final String API_KEY_PARAMETER = "api_key";

    public static final String RECIPE_ID_PARAMETER = "recipeID";

    public static final String USER_ID_PARAMETER = "users_id";

    public static final String PASSWORD_PARAMETER = "password";

    public static final String EMAIL_PARAMETER = "email";

    public static final String LOGIN_PATH = "/login";

    public static final String USER_SVC_PATH = "/user";

    public static final String RECIPE_SVC_PATH = "/recipe";

    public static final String RECIPE_TITLE_SEARCH_PATH = RECIPE_SVC_PATH + "/search/findByName";

    public static final String RECIPE_LIST_BY_USER_PATH = RECIPE_SVC_PATH + "/list";
    @FormUrlEncoded
    @POST(LOGIN_PATH)
    String login(@Field(EMAIL_PARAMETER) String email, @Field(PASSWORD_PARAMETER) String password);

    @GET(USER_SVC_PATH)
    User getUser(@Query(API_KEY_PARAMETER) String api_key);

    @POST(USER_SVC_PATH)
    String signup(@Body User user);

    @POST(RECIPE_SVC_PATH)
    String addrecipe(@Body Recipe recipe);

    String deleteRecipe(@Query(RECIPE_ID_PARAMETER) Long recipeID);

    @GET(RECIPE_LIST_BY_USER_PATH)
    List<Recipe> getRecipes(@Query(USER_ID_PARAMETER) Long users_id);

    @GET(RECIPE_SVC_PATH)
    Recipe getRecipe(@Query(RECIPE_ID_PARAMETER) Long recipe_id) throws Exception;

}

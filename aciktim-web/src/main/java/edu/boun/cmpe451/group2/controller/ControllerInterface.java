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

    public static final String PASSWORD_PARAMETER = "password";

    public static final String EMAIL_PARAMETER = "email";

    public static final String LOGIN_PATH = "/login";

    @FormUrlEncoded
    @POST(LOGIN_PATH)
    String login(@Field(EMAIL_PARAMETER) String email, @Field(PASSWORD_PARAMETER) String password);

    User getUser(String api_key);

    String signup(User user);

    String addrecipe(Recipe recipe);

    String deleteRecipe(Long recipeID);

    List<Recipe> getRecipes(String api_key,Long users_id);

    Recipe getRecipe( String api_key,Long recipe_id) throws Exception;

}

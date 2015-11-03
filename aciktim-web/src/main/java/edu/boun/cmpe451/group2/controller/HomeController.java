package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.model.UserModel;
import edu.boun.cmpe451.group2.model.RecipeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
@Scope("request")
public class HomeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;
    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;

    /**
     * Index Controller function.
     *
     * @param model - object that stores data to be rendered by view
     * @return view
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model, @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            model.put("full_name", "");
        }
        else {
            UserModel user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
        }
        return "home_index";
    }

    @RequestMapping(value = {"/signup"})
    public String signup(ModelMap model) {

        model.put("type", "NORMAL");

        return "sign-up";
    }

    @RequestMapping(value = {"/recipes"})
    public String recipes(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id){

        if (session_id != "") {
            List<Map<String,Object>> recipes = recipeModel.getRecipes(Long.parseLong(userModel.getUser(session_id).id));

            model.put("recipes", recipes);

            model.put("full_name", userModel.getUser(session_id).full_name);
        }else{
            return index(model,session_id);
        }
        return "recipe-grid";
    }

    @RequestMapping(value = {"/login"})
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletResponse response,
            ModelMap model) {

        try {
            String session_id = userModel.login(email, password);
            Cookie cookie = new Cookie("session_id", session_id);
            response.addCookie(cookie);

            UserModel user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home_index";
    }

    @RequestMapping(value = {"/logout"})
    public String logout(
            HttpServletResponse response,
            ModelMap model) {

        Cookie cookie = new Cookie("session_id", "");
        response.addCookie(cookie);

        model.put("full_name", "");

        return "home_index";
    }

    @RequestMapping(value = {"/users"})
    public String viewUser(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            model.put("full_name", "");
        }
        else {
            UserModel user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("email", user.email);
        }

        return "profile-view";
    }

    @RequestMapping(value = {"/edituser"})
    public String editUser(ModelMap model) {



        return "profile-edit";
    }

    @RequestMapping(value = {"/recipeform"})
    public String recipeform(
            ModelMap model ,
            @CookieValue(value="session_id" , defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            return index(model, session_id);
        }
        else {
            UserModel user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("email", user.email);
        }


        return "recipeformpage";
    }

    @RequestMapping(value = {"/addrecipe"} , method = RequestMethod.POST)
    public String addrecipe(
            @RequestParam String recipe_name,
            @RequestParam String description,
            @RequestParam String image_url,
            @CookieValue(value="session_id", defaultValue = "") String session_id,
            ModelMap model) {

        Long id = Long.parseLong(userModel.getUser(session_id).id);

        try {
            recipeModel.addRecipe(recipe_name,id,null,image_url,description);
            model.put("type", "SUCCESS");
        }catch (Exception e){
            model.put("type", "ERROR");

        }
        return recipes(model,session_id);
    }


    @RequestMapping(value = {"/adduser"})
    public String adduser(
            @RequestParam String email,
            @RequestParam String confirm_email,
            @RequestParam String password,
            @RequestParam String confirm_password,
            @RequestParam String first_name,
            @RequestParam String last_name,
            ModelMap model) {
        if (!email.equals(confirm_email) || !password.equals(confirm_password)) {
            model.put("type", "ERROR");
        }

        try {
            userModel.signup(email, password, first_name.concat(" ").concat(last_name), email);

            model.put("type", "SUCCESS");
        } catch (Exception e) {
            model.put("type", "ERROR");
        }

        return "sign-up";
    }

    @RequestMapping(value = "recipe/edit")
    public String editRecipe(
            @RequestParam(required = false) Long id) {

        userModel.getUserDao().deleteUser(id);

        return "redirect:/recipes";
    }

    @RequestMapping(value = "recipe/delete")
    public String deleteRecipe(
            @RequestParam(required = false) Long recipe_id) {
        recipeModel.getRecipeDao().deleteRecipe(recipe_id);
        return "redirect:/recipes";
    }

}
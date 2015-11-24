package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Scope("request")
public class RecipeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;
    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;

//    @RequestMapping(value = {"/myrecipes"})
//    public String viewrecipes(
//            ModelMap model,
//            @CookieValue(value="session_id", defaultValue = "") String session_id){
//
//        if (!session_id.equals("")) {
//            List<Map<String,Object>> recipes = recipeModel.getRecipes(Long.parseLong(userModel.getUser(session_id).id));
//
//            model.put("recipes", recipes);
//
//            model.put("full_name", userModel.getUser(session_id).full_name);
//        }else{
//            return "redirect:index";
//        }
//
//        model.put("content_bar_selection" , "recipes");
//        return "recipe-views/recipe_grid";
//    }

    @RequestMapping(value = {"/recipe/view"})
    public String recipeview(
            ModelMap model ,
            @RequestParam String action_type,
            @RequestParam (required = false , defaultValue = "-1") String recipe_id,
            @CookieValue(value="session_id" , defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            return "redirect:index";
        }
        else {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("email", user.email);

            model.put("action_type" , action_type);

            model.put("existing_recipe_name" , "");
            model.put("existing_recipe_description" , "");
            model.put("existing_recipe_image_url" , "");

            if (action_type.equals("edit")){
                Long rp_id = Long.parseLong(recipe_id);
                try {
                    Recipe rm = recipeModel.getRecipe(rp_id);
                    model.put("existing_recipe_id" , rp_id);
                    model.put("existing_recipe_name" , rm.name);
                    model.put("existing_recipe_description" , rm.description);
                    model.put("existing_recipe_image_url" , rm.pictureAddress);
                }catch (Exception e){
                }
            }
        }
        model.put("content_bar_selection" , "recipes");
        return "recipe-views/recipe_grid";
    }


    @RequestMapping(value = {"/recipe/form"})
    public String recipeform(
            ModelMap model ,
            @RequestParam String action_type,
            @RequestParam (required = false , defaultValue = "-1") String recipe_id,
            @CookieValue(value="session_id" , defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            return "redirect:index";
        }
        else {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("email", user.email);

            model.put("action_type" , action_type);

            model.put("existing_recipe_name" , "");
            model.put("existing_recipe_description" , "");
            model.put("existing_recipe_image_url" , "");

            if (action_type.equals("edit")){
                Long rp_id = Long.parseLong(recipe_id);
                try {
                    Recipe rm = recipeModel.getRecipe(rp_id);
                    model.put("existing_recipe_id" , rp_id);
                    model.put("existing_recipe_name" , rm.name);
                    model.put("existing_recipe_description" , rm.description);
                    model.put("existing_recipe_image_url" , rm.pictureAddress);
                }catch (Exception e){
                }
            }
        }
        model.put("content_bar_selection" , "create_recipe");
        return "recipe-views/recipe_form_page";
    }

    @RequestMapping(value = {"/recipe/add" } , method = RequestMethod.POST)
    public String recipeadd(
            @RequestParam String recipe_name,
            @RequestParam String description,
            @RequestParam String image_url,
            @RequestParam ArrayList<String> ingredient_ndb_nums,
            @RequestParam ArrayList<Integer> ingredient_amounts,
            @RequestParam ArrayList<String> ingredient_units,
            @CookieValue(value="session_id", defaultValue = "") String session_id,
            ModelMap model) {

        Long id = Long.parseLong(userModel.getUser(session_id).id);

        try {
            Recipe r = new Recipe();
            r.name=recipe_name;
            r.id = id;
            r.IngredientAmountMap = null ;
            r.pictureAddress = image_url;
            r.description = description;
            recipeModel.addRecipe(r);

            model.put("type", "SUCCESS");
        }catch (Exception e){
            model.put("type", "ERROR");

        }

        return "redirect:recipes";
    }


    @RequestMapping(value = "recipe/edit")
    public String recipeedit(
            @RequestParam(required = false) Long recipe_id,
            @RequestParam(required = false) String image_url,
            @RequestParam(required = false) String recipe_name,
            @RequestParam(required = false) String description,
            @CookieValue(value="session_id", defaultValue = "") String session_id,
            ModelMap model) {



        Long userId = Long.parseLong(userModel.getUser(session_id).id);
    try{
        Recipe r = new Recipe();
        r.name=recipe_name;
        r.id = recipe_id;
        r.IngredientAmountMap = null ;
        r.pictureAddress = image_url;
        r.description = description;
        recipeModel.updateRecipe(r);
        model.put("type", "SUCCESS");
    }catch(Exception e){
        model.put("type", "ERROR");

    }

        return "redirect:/recipes";
    }

    @RequestMapping(value = "recipe/delete")
    public String recipedelete(
            @RequestParam(required = false) Long recipe_id) {
        recipeModel.getRecipeDao().deleteRecipe(recipe_id);
        return "redirect:/recipes";
    }




}
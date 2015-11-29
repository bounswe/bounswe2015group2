package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.client.Ingredient;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.Tag;
import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.exception.ExException;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@Scope("request")
public class RecipeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;
    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;


    @RequestMapping(value = {"/recipes"})
    public String viewrecipes(
            ModelMap model,

            @RequestParam (required = false, defaultValue = "false") String bad_a,

            @RequestParam (required = false, defaultValue = "-1") String ownerID,
            @RequestParam (required = false, defaultValue = "-1") String search_keyword,
            @RequestParam (required = false, defaultValue = "-1") String ingredients_string,

            @CookieValue(value="session_id", defaultValue = "") String session_id){


        model.put("bad_attempt", bad_a);

        model.put("content_bar_selection" , "recipes");
        User user = null;
        if (!session_id.equals("")){
            user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
        }



        if (user != null && ownerID.equals(user.id)){ //bring my recipes
            List<Recipe> recipes = recipeModel.getRecipes(Long.parseLong(user.id));
            model.put("recipeResults",recipes);
        }else{
            try{
                if (!search_keyword.equals("-1")){
                    if (!ingredients_string.equals("-1")){ // bring keyword + ingredients
                        List<String> ingredientList = Arrays.asList(ingredients_string.split("\\s*,\\s*"));
                        List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword, ingredientList);
                        model.put("recipeResults", recipeResults);
                    }else{ // bring keyword
                        List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword);
                        model.put("recipeResults", recipeResults);
                    }
                }else{ // bring random recipes
                    List<Recipe> recipeResults = recipeModel.searchRecipesRandom(10);
                    model.put("recipeResults", recipeResults);
                }
            }catch (ExException e){
                e.printStackTrace();
                System.out.println("Shit man");
            }
        }
        return "user-views/recipes";

    }

    ////  used for both search and advanced search
//    @RequestMapping(value = "/recipes" , method=RequestMethod.POST)
//    public String searchRecipe(
//            @RequestParam String search_keyword,
//            @RequestParam(required = false, defaultValue = "") String ingredients_string,
//            ModelMap model,
//            @CookieValue(value="session_id", defaultValue = "") String session_id) {
//
//        try {
//            if (!session_id.equals("")) {
//                User user = userModel.getUser(session_id);
//                model.put("full_name", user.full_name);
//            }
//            if(ingredients_string.equals("")) {
//                List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword);
//                model.put("recipeResults", recipeResults);
//            }else{
//                List<String> tempList = Arrays.asList(ingredients_string.split("\\s*,\\s*"));
//                ArrayList<String> ingredientList = new ArrayList<>(tempList);
//                List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword, ingredientList);
//                model.put("recipeResults", recipeResults);
//            }
//        } catch (ExException e) {
//            e.printStackTrace();
//        }
//        model.put("content_bar_selection","recipes");
//        return "user-views/recipes";
//    }

//    @RequestMapping(value = "/recipes" , method=RequestMethod.POST)
//    public String searchRecipe(
//            @RequestParam String search_keyword,
//            ModelMap model,
//            @CookieValue(value="session_id", defaultValue = "") String session_id) {
//
//        try {
//            if (!session_id.equals("")) {
//                User user = userModel.getUser(session_id);
//                model.put("full_name", user.full_name);
//            }
//            List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword);
//            model.put("recipeResults", recipeResults);
//        } catch (ExException e) {
//            e.printStackTrace();
//        }
//        model.put("content_bar_selection","recipes");
//        return "user-views/recipes";
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
                    System.out.printf("Bullshit");
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
                    System.out.printf("Bullshit");
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
            @CookieValue(value="session_id", defaultValue = "") String session_id,
            ModelMap model,
            HttpServletRequest request) {

        String[] ingredient_name = request.getParameterValues("ingredient_name");
        String[] ingredient_amount = request.getParameterValues("ingredient_amount");
        String[] ingredient_no = request.getParameterValues("ingredient_no");

        String[] ingredient_en = request.getParameterValues("ingredient_en");
        String[] ingredient_carb = request.getParameterValues("ingredient_carb");
        String[] ingredient_prot = request.getParameterValues("ingredient_prot");
        String[] ingredient_fat = request.getParameterValues("ingredient_fat");

        String[] tag = request.getParameterValues("tag");


        Long id = Long.parseLong(userModel.getUser(session_id).id);

        try {
            Recipe r = new Recipe();
            r.ownerID = id;
            System.out.println("1");
            fillRecipe(r, recipe_name, id, image_url, description, formAmountMap(ingredient_name, ingredient_amount, ingredient_no, ingredient_en, ingredient_carb, ingredient_prot, ingredient_fat), formTagList(tag));
            System.out.println("2");
            recipeModel.addRecipe(r);
            System.out.println("3");
            model.put("type", "SUCCESS");
        }catch (ExException e){
            e.printStackTrace();
            model.put("type", "ERROR");

        }catch (Exception e){
            e.printStackTrace();
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


        System.out.println(recipe_id);
        System.out.println(image_url);
        System.out.println(recipe_name);
        System.out.println(description);

        Long userId = Long.parseLong(userModel.getUser(session_id).id);
        System.out.println(userId);

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
        System.out.println("asd" + recipe_id);
        recipeModel.getRecipeDao().deleteRecipe(recipe_id);
        return "redirect:/recipes";
    }

    public void fillRecipe(Recipe r,
                           String recipe_name,
                           Long id,
                           String image_url,
                           String description,
                           HashMap<Ingredient,Long> ingredient_map,
                           List<Tag> taglist){
        r.name = recipe_name;
        r.id = id;
        r.pictureAddress = image_url;
        r.description = description;
        r.setIngredientAmountMap(ingredient_map);
        r.tagList = taglist;

    }

    public HashMap<Ingredient,Long> formAmountMap(String[] names, String[] nos, String[] amounts, String[] en,String[] carb,String[] prot,String[] fat){
        HashMap<Ingredient,Long> m = new HashMap<>();
        int counter = 0;
        for (String no : nos){
            Ingredient i = new Ingredient();
            System.out.println("Calories:");
            System.out.println(en[counter]);
            i.calories = Double.parseDouble(en[counter]);
            i.carbohydrate = Double.parseDouble(carb[counter]);
            i.protein = Double.parseDouble(prot[counter]);
            i.fat = Double.parseDouble(fat[counter]);
            i.name = names[counter];
            i.unitName = "grams";
            m.put(i,Long.parseLong(amounts[counter]));
            counter++;
        }
        return m;
    }

    public List<Tag> formTagList(String[] tagList){
        List<Tag> list = new ArrayList<Tag>();
        for (String tag : tagList){
            System.out.println("Tag:");
            System.out.println(tag);
            Tag t = new Tag();
            t.name = tag;
            list.add(t);
        }
        return list;
    }




}
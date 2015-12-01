package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.client.Ingredient;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.Tag;
import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.client.Menu;
import edu.boun.cmpe451.group2.dao.UserDao;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.RecipeModel;
import edu.boun.cmpe451.group2.model.MenuModel;
import edu.boun.cmpe451.group2.model.UserModel;
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

/**
 * Created by Baris Kaya on 11/29/2015.
 */
@Controller
@Scope("request")
public class MenuController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;
    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;
    @Qualifier("menuModel")
    @Autowired
    private MenuModel menuModel = null;


    @RequestMapping(value = {"/menu/form"})
    public String menuform(
            ModelMap model ,
            @RequestParam String action_type,
            @RequestParam (required = false , defaultValue = "-1") String menu_id,
            @CookieValue(value="session_id" , defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            return "redirect:index";
        } else {
            User user = userModel.getUser(session_id);
            List<Recipe> recipes = recipeModel.getRecipes(Long.parseLong(user.id));
            model.put("recipeResults",recipes);
            model.put("full_name", user.full_name);
            model.put("email", user.email);

            model.put("action_type", action_type);

            model.put("existing_menu_name", "");

            if (action_type.equals("edit")) {
                Long mn_id = Long.parseLong(menu_id);
                try {
                    Recipe rm = recipeModel.getRecipe(mn_id);
                    model.put("existing_menu_id", mn_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.put("content_bar_selection", "create_menu");
        return "menu-views/menu_form_page";
    }


    @RequestMapping(value = {"/menu/add" } , method = RequestMethod.POST)
    public String menuadd(
            @RequestParam String menu_name,
            @CookieValue(value="session_id", defaultValue = "") String session_id,
            ModelMap model,
            HttpServletRequest request) {

        String[] recipe_ids = request.getParameterValues("recipe_id");
        String[] recipe_names = request.getParameterValues("recipe_name");
        String[] recipe_included = request.getParameterValues("recipe_included");
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        for (int i = 0; i < recipe_ids.length; i++) {
            if (recipe_included[i].equals("true")) {
                try {
                    recipes.add(recipeModel.getRecipe(Long.parseLong(recipe_ids[i])));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        Long id = Long.parseLong(userModel.getUser(session_id).id);

        try {
            menuModel.AddMenu(new Menu(recipes, id, menu_name));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/recipes";
    }

    @RequestMapping(value = {"/menus"})
    public String viewmenus(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id){






        User user = null;
        if (!session_id.equals("")){
            user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("content_bar_selection" , "menus");

            try {
                List<Menu> list = new ArrayList<Menu>(menuModel.GetMenusByID(Long.parseLong(user.id)).values());
                model.put("menus", list);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            return "redirect:/index";
        }



        return "menu-views/menu_view_page";

    }

}
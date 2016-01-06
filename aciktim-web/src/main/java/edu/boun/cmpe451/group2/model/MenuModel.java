package edu.boun.cmpe451.group2.model;

import edu.boun.cmpe451.group2.client.Menu;
import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.dao.MenuDao;
import edu.boun.cmpe451.group2.dao.RecipeDao;
import edu.boun.cmpe451.group2.dao.UserDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Menu Model Class
 * This class process all works related to Menu.
 */
@Service
@Scope("request")
public class MenuModel {
    @Qualifier("menuDao")
    @Autowired
    private MenuDao menuDao = null;
    @Qualifier("recipeDao")
    @Autowired
    private RecipeDao recipeDao = null;
    /**
     * adds a menu to the db.
     * note that this is an INSTANCE method
     * and please be sure that you are trying to add VALID recipes to the menu.
     */
    public void AddMenu(Menu menu){
        menuDao.createMenu(menu);
    }

    /**
     * Gets the menus of a user (which are not soft deleted)
     * @param api_key api_key of the user
     * @return HashMap of menus (keys are menuIDs values are menus)
     * @throws ExException
     */
    public ArrayList<Menu> GetMenusByApiKey(String api_key) throws ExException {
        UserDao userDao = null;
        Map<String, Object> user = userDao.getUserByApiKey(api_key);
        if(user == null){
            throw new ExException(ExError.E_USER_NOT_FOUND);
        }
        List<Map<String,Object>> menusDB = menuDao.getMenus(Long.parseLong(user.get("id").toString()));
        HashMap<Long,Menu> menus = new HashMap();
        Long menuID;
        for (Map<String,Object> menuRecipe : menusDB){
            menuID = Long.parseLong(menuRecipe.get("id").toString());
            Recipe recipe = new Recipe();
            recipe.id = Long.parseLong(menuRecipe.get("recipeID").toString());
            if(menus.containsKey(menuID)){
                menus.get(menuID).recipes.add(recipe);
            }else{
                ArrayList<Recipe> recipes = new ArrayList<Recipe>();
                recipes.add(recipe);
                String name = menuRecipe.get("menu_name").toString();
                Menu menu= new Menu(recipes,api_key,name);
                menus.put(menuID,menu);
            }
        }
        ArrayList<Menu> menusRet = new ArrayList<>();
        for(Map.Entry<Long, Menu> entry: menus.entrySet()) {
            menusRet.add(entry.getValue());
        }
        return menusRet;
    }
    /**
     * Gets the menus of a user (which are not soft deleted)
     * @param id id of the user
     * @return HashMap of menus (keys are menuIDs values are menus)
     * @throws ExException
     */
    public HashMap<Long,Menu> GetMenusByID(Long id) throws ExException {
        List<Map<String,Object>> menusDB = menuDao.getMenus(id);
        HashMap<Long,Menu> menus = new HashMap();
        Long menuID;

        for (Map<String,Object> menuRecipe : menusDB){
            menuID = Long.parseLong(menuRecipe.get("id").toString());
            Recipe recipe = recipeDao.getRecipe(Long.parseLong(menuRecipe.get("recipeID").toString()));
            if(menus.containsKey(menuID)){
                menus.get(menuID).recipes.add(recipe);
            }else{
                ArrayList<Recipe> recipes = new ArrayList<Recipe>();
                recipes.add(recipe);
                String name = (String)menuRecipe.get("menu_name");
                Menu menu = new Menu(recipes,id, name);
                menus.put(menuID,menu);
            }
        }
        return menus;
    }
}

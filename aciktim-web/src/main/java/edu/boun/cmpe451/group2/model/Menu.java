package edu.boun.cmpe451.group2.model;

import edu.boun.cmpe451.group2.dao.MenuDao;
import edu.boun.cmpe451.group2.dao.UserDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.utils.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Menu model class
 * this class processes all works related to menus.
 * this is also the variable type to be used.
 */
@Service
@Scope("request")
public class Menu {
    private MenuDao menuDao = null;

    public ArrayList<RecipeModel> recipes;
    public Long ownerID;
    public String name ;

    /**
     * Constructor method of Menu model
     * @param recipes arraylist of recipes that this menu contains, only the recipeIDs have to be filled
     * @param api_key api_key of the user
     * @param name menu name(must be nonempty)
     * @throws ExException throws exception when the recipe list is empty or null, name is empty or null,user is not found
     */
    public Menu(ArrayList<RecipeModel> recipes,String api_key,String name) throws ExException {
        if(recipes == null || recipes.size()==0){
            throw new ExException(ExError.E_RECIPELIST_EMPTY_OR_NULL);
        }else if(name == null || StringUtil.isEmpty(name)){
            throw new ExException(ExError.E_MENU_NAME_EMPTY);
        }
        UserDao userDao = null;
        Map<String, Object> user = userDao.getUserByApiKey(api_key);
        if(user == null){
            throw new ExException(ExError.E_USER_NOT_FOUND);
        }
        this.recipes = recipes;
        this.ownerID = Long.parseLong(user.get("id").toString());
        this.name = name;
    }

    /**
     * adds a menu to the db.
     * note that this is an INSTANCE method
     * and please be sure that you are trying to add VALID recipes to the menu.
     */
    public void AddMenu(){
        menuDao.createMenu(this);
    }

    /**
     * Gets the menus of a user (which are not soft deleted)
     * @param api_key api_key of the user
     * @return HashMap of menus (keys are menuIDs values are menus)
     * @throws ExException
     */
    public HashMap<Long,Menu> GetMenusByApiKey(String api_key) throws ExException {
        UserDao userDao = null;
        Map<String, Object> user = userDao.getUserByApiKey(api_key);
        if(user == null){
            throw new ExException(ExError.E_USER_NOT_FOUND);
        }
        List<Map<String,Object>> menusDB = menuDao.getMenus(Long.parseLong(user.get("id").toString()));
        HashMap<Long,Menu> menus = new HashMap();
        Long menuID;
        Long ownerID = Long.parseLong(user.get("id").toString());
        for (Map<String,Object> menuRecipe : menusDB){
            menuID = Long.parseLong(menuRecipe.get("id").toString());
            RecipeModel recipe = new RecipeModel();
            recipe.id = Long.parseLong(menuRecipe.get("recipeID").toString());
            if(menus.containsKey(menuID)){
                menus.get(menuID).recipes.add(recipe);
            }else{
                ArrayList<RecipeModel> recipes = new ArrayList<RecipeModel>();
                recipes.add(recipe);
                String name = menuRecipe.get("name").toString();
                Menu menu= new Menu(recipes,api_key,name);
                menus.put(menuID,menu);
            }
        }
        return menus;
    }
}

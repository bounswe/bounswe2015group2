package edu.boun.cmpe451.group2.client;

import edu.boun.cmpe451.group2.dao.UserDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.utils.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Menu model class
 * this class processes all works related to menus.
 * this is also the variable type to be used.
 */
@Service
@Scope("request")
public class Menu {

    public ArrayList<Recipe> recipes;
    public Long ownerID;
    public String name ;

    /**
     * Constructor method of Menu model
     * @param recipes arraylist of recipes that this menu contains, only the recipeIDs have to be filled
     * @param api_key api_key of the user
     * @param name menu name(must be nonempty)
     * @throws ExException throws exception when the recipe list is empty or null, name is empty or null,user is not found
     */
    public Menu(ArrayList<Recipe> recipes,String api_key,String name) throws ExException {
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
}

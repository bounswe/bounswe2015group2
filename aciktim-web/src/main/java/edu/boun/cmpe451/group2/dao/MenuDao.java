package edu.boun.cmpe451.group2.dao;

import edu.boun.cmpe451.group2.client.Menu;
import edu.boun.cmpe451.group2.client.Recipe;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Menu Dao class for User's Database Related operation.
 */
@Repository
@Scope("request")
public class MenuDao extends BaseDao{

    /**
     * this methods tries to add a menu to db
     * Note that recipeID and menuID have FK relationships and this relationships are not checked
     * please be sure that you are trying to add VALID recipes to the menu.
     * @param menu menu to be added
     */
    public void createMenu(Menu menu){
        String sql = "INSERT INTO menus(name,ownerID) VALUES(?,?)";
        this.jdbcTemplate.update(sql,menu.name,menu.ownerID);
        sql = "SELECT id FROM menus ORDER BY id DESC LIMIT 1";
        Long menuID=Long.parseLong(this.jdbcTemplate.queryForMap(sql).get("id").toString());
        sql = "INSERT INTO menuRecipe(menuID, recipeID) VALUES(?,?)";
        for(Recipe recipe : menu.recipes){
            this.jdbcTemplate.update(sql,menuID,recipe.id);
        }
    }

    public List<Map<String, Object>> getMenus(Long ownerID){
        String sql = "SELECT menus.id,menus.name as menu_name,menus.ownerID,menus.likes,menuRecipe.recipeID FROM menus INNER JOIN menuRecipe ON menus.id = menuRecipe.menuID WHERE ownerID=? AND NOT menus.isDeleted";
        return this.jdbcTemplate.queryForList(sql,ownerID);
    }
}

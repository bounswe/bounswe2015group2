package edu.boun.cmpe451.group2.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import edu.boun.cmpe451.group2.client.Recipe;
import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import edu.boun.cmpe451.group2.dao.UserDao;
import edu.boun.cmpe451.group2.exception.ExError;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.utils.StringUtil;
import edu.boun.cmpe451.group2.utils.Security;

/**
 * User Model Class
 * This class process all works related to User.
 */
@Service
@Scope("request")
public class UserModel {

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao = null;

    @Qualifier("recipeDao")
    @Autowired
    RecipeDao recipeDao = null;


    /**
     * login
     *
     * @param email    email address
     * @param password password
     * @return
     * @throws Exception
     */
    public String login(String email, String password) throws Exception {
        if (StringUtil.isEmpty(email))
            throw new ExException(ExError.E_EMAIL_EMPTY);

        if (StringUtil.isEmpty(password))
            throw new ExException(ExError.E_PWD_EMPTY);

        Map<String, Object> user = userDao.getUserByEmail(email);

        if (user == null)
            throw new ExException(ExError.E_NOT_REGISTERED);

        if (!Security.md5(password).equals(user.get("passwd").toString())) {
            throw new ExException(ExError.E_INVALID_PWD);
        }

        return (String) user.get("api_key");

    }

    /**
     * registers the new user
     * @param user user to be registered
     * @return api_key
     * @throws Exception when email is empty, when password is empty
     */
    public String signup (User user) throws Exception {
        user.username = user.email;
        System.out.println(user.username);
        if (StringUtil.isEmpty(user.email))
            throw new ExException(ExError.E_EMAIL_EMPTY);
        System.out.println(user.username);

        if (StringUtil.isEmpty(user.passwd))
            throw new ExException(ExError.E_PWD_EMPTY);
        System.out.println(user.username);

        if (StringUtil.isEmpty(user.full_name))
            user.full_name = "";

        System.out.println(user.username);

        // checks if email is already registered
        Map<String, Object> userM = userDao.getUserByEmail(user.email);

        System.out.println(user.username);
        if (userM != null)
            throw new ExException(ExError.E_ALREADY_REGISTERED);

        System.out.println(user.username);
        userDao.addUser(user);

        System.out.println(user.username);
        return (String) userDao.getUserByEmail(user.email).get("api_key");
    }

    /**
     *
     * @param id
     * @param email
     * @param pwd
     * @param full_name
     * @param username
     * @throws Exception
     */
    public void editProfile(Long id, String email, String pwd, String full_name, String username) throws Exception {
        if (StringUtil.isEmpty(email))
            throw new ExException(ExError.E_EMAIL_EMPTY);

        if (StringUtil.isEmpty(pwd))
            throw new ExException(ExError.E_PWD_EMPTY);

        if (StringUtil.isEmpty(full_name))
            full_name = "";

        if (StringUtil.isEmpty(username))
            username = "";

        // checks if email is already registered
        Map<String, Object> user = userDao.getUserByEmail(email);

        if (user != null)
            throw new ExException(ExError.E_ALREADY_REGISTERED);

        userDao.updateUser(id, email, pwd, full_name, username);
    }

    public User getUser(String api_key) {
        User user = new User();
        Map<String, Object> userMap = userDao.getUserByApiKey(api_key);
        user.id = userMap.get("id").toString();
        user.email = userMap.get("email").toString();
        user.passwd = userMap.get("passwd").toString();
        user.full_name = userMap.get("full_name").toString();
        user.username = userMap.get("username").toString();
        user.api_key = userMap.get("api_key").toString();
        user.isInst = Boolean.parseBoolean(userMap.get("isInst").toString());
        return user;
    }

    public User getUserByID(Long user_id) {
        User user = new User();
        Map<String, Object> userMap = userDao.getUser(user_id);
        user.id = userMap.get("id").toString();
        user.email = userMap.get("email").toString();
        user.passwd = userMap.get("passwd").toString();
        user.full_name = userMap.get("full_name").toString();
        user.username = userMap.get("username").toString();
        user.api_key = userMap.get("api_key").toString();
        user.isInst = Boolean.parseBoolean(userMap.get("isInst").toString());
        return user;
    }

    /**
     * this method adds the consume info of the user
     * @param userID id of the user who consumes
     * @param recipeID id of the recipe that user consumes
     * @param calendar calendar that contains the date
     * @return true if success, false if there is an error
     */
    public boolean consume(Long userID,Long recipeID,Calendar calendar){
        return userDao.consume(userID,recipeID,calendar);
    }

    /**
     * returns daily consumption list of a user, given that day
     * @param userID id of the user
     * @param calendar calendar that contains the date
     * @return Arraylist of recipes that consumed that day
     * @throws ExException
     */
    public ArrayList<Recipe> getDailyConsumption(Long userID,Calendar calendar) throws ExException{
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        List<Map<String,Object>> list = userDao.getDailyConsumption(userID,calendar);
        for(Map<String,Object> entry : list){
            Recipe r = new Recipe();
            Long id = Long.parseLong(entry.get("recipeID").toString());
            r = recipeDao.getRecipe(id);
            r.id = id;
            recipes.add(r);
        }
        return recipes;
    }
    public UserDao getUserDao() {
        return userDao;
    }
}

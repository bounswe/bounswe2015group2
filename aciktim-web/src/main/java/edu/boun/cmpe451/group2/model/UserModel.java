package edu.boun.cmpe451.group2.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.google.gson.*;
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
        if (StringUtil.isEmpty(user.email))
            throw new ExException(ExError.E_EMAIL_EMPTY);

        if (StringUtil.isEmpty(user.passwd))
            throw new ExException(ExError.E_PWD_EMPTY);

        if (StringUtil.isEmpty(user.full_name))
            user.full_name = "";


        // checks if email is already registered
        Map<String, Object> userM = userDao.getUserByEmail(user.email);

        if (userM != null)
            throw new ExException(ExError.E_ALREADY_REGISTERED);

        userDao.addUser(user);

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

        userDao.updateUser(id,email, pwd, full_name, username);
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
    public UserDao getUserDao() {
        return userDao;
    }
}

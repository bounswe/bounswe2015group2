package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@Scope("request")
public class HomeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;

    /**
     * Index Controller function.
     *
     * @param model - object that stores data to be rendered by view
     * @return view
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

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

    @RequestMapping(value = {"/login"})
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletResponse response,
            ModelMap model) {

        System.out.println(email);
        System.out.println(password);
        try {
            System.out.println(email);
            System.out.println(password);
            String session_id = userModel.login(email, password);
            System.out.println(session_id);
            System.out.println(email);
            System.out.println(password);
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
    public String viewUser(ModelMap model) {



        return "profile-view";
    }

    @RequestMapping(value = {"/edituser"})
    public String editUser(ModelMap model) {



        return "profile-edit";
    }

    @RequestMapping(value = {"/addRecipe"})
    public String addRecipe(ModelMap model) {



        return "addrecipe";
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

}
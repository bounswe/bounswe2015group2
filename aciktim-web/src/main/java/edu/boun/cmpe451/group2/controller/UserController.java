package edu.boun.cmpe451.group2.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("request")
public class UserController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;


    @RequestMapping(value = {"/signup"})
    public String signup(ModelMap model) {
        model.put("type", "NORMAL");
        return "user-views/sign_up";
    }

    @RequestMapping(value = "/login" , method=RequestMethod.POST)
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletResponse response,
            ModelMap model) {

        try {
            String session_id = userModel.login(email, password);
            Cookie cookie = new Cookie("session_id", session_id);
            response.addCookie(cookie);

            UserModel user = userModel.getUser(session_id);

            model.put("full_name", user.full_name);
            model.put("bad_attempt","false");

        } catch (Exception e) {
            // Invalid Credentials
            model.put("full_name", "");
            model.put("bad_attempt","true");

            e.printStackTrace();
        }
        return "user-views/home_index";
    }

    @RequestMapping(value = {"/logout"})
    public String logout(
            HttpServletResponse response,
            ModelMap model) {

        Cookie cookie = new Cookie("session_id", "");

        response.addCookie(cookie);

        model.put("full_name", "");

        return "user-views/home_index";
    }

//    @RequestMapping(value = {"/users"})
//    public String viewUser(
//            ModelMap model,
//            @CookieValue(value="session_id", defaultValue = "") String session_id) {
//
//        if (session_id.equals("")) {
//            model.put("full_name", "");
//        }
//        else {
//            UserModel user = userModel.getUser(session_id);
//            model.put("full_name", user.full_name);
//            model.put("email", user.email);
//        }
//
//        return "profile";
//    }

    @RequestMapping(value = {"/edituser"})
    public String editUser(ModelMap model) {
        return "user-views/profile_edit";
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

        return "user-views/sign_up";
    }



}
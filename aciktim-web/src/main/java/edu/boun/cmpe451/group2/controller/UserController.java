package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.model.RecipeModel;
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
    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;

    //##########################################
    //######## AUTHENTICATION & AUTHORIZATION
    //##########################################

    @RequestMapping(value = {"user/signup"})
    public String usersignup(ModelMap model) {
        model.put("type", "NORMAL");
        model.put("content_bar_selection" , "none");
        return "user-views/sign_up";
    }

    @RequestMapping(value = "user/login" , method=RequestMethod.POST)
    public String userlogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletResponse response) {

        try {
            String session_id = userModel.login(email, password);
            Cookie cookie = new Cookie("session_id", session_id);

            cookie.setPath("/aciktim/");
            response.addCookie(cookie);
            return "redirect:../recipes/?bad_a=false";

//            model.put("bad_attempt","false");
//            User user = userModel.getUser(session_id);
//            model.put("full_name", user.full_name);



        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:../recipes/?bad_a=true";
            // Invalid Credentials
//            model.put("full_name", "");
//            model.put("bad_attempt","true");


        }
//        model.put("content_bar_selection","recipes");

//        return "user-views/recipes";
    }

    @RequestMapping(value = {"user/logout"})
    public String userlogout(
            HttpServletResponse response,
            ModelMap model) {

        Cookie cookie = new Cookie("session_id", "");
        cookie.setPath("/aciktim/");
        response.addCookie(cookie);

        return "redirect:../index";
//        model.put("full_name", "");
//        model.put("content_bar_selection","recipes");
//
//        return "user-views/recipes";
    }

    @RequestMapping(value = {"user/add"})
    public String useradd(
            @RequestParam String email,
            @RequestParam String confirm_email,
            @RequestParam String password,
            @RequestParam String confirm_password,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam (required = false, defaultValue = "false") Boolean is_institution,

            ModelMap model) {

        System.out.println(is_institution);
        System.out.println(password);
        System.out.println(confirm_password);

        if (!email.equals(confirm_email) || !password.equals(confirm_password)) {
            model.put("type", "ERROR");
        }

        System.out.println("oops!");

        try {
            User user = new User();
            user.setFull_name(first_name.concat(" ").concat(last_name));
            user.setEmail(email);
            user.setPasswd(password);
            user.setIsInst(is_institution);
            userModel.signup(user);

            model.put("type", "SUCCESS");
        } catch (Exception e) {
            System.out.println("wut?");
            e.printStackTrace();
            model.put("type", "ERROR");
        }
        model.put("content_bar_selection" , "none");
        return "user-views/sign_up";
    }



    //#####################
    //######## VIEW USERS
    //#####################

    @RequestMapping(value = {"/restaurants"})
    public String restaurants(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            model.put("full_name", "");
        }
        else {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("email", user.email);
        }

        model.put("content_bar_selection" , "restaurants");
        return "user-views/restaurants";
    }


    @RequestMapping(value = {"user/view"})
    public String userview(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            model.put("full_name", "");
        }
        else {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("user_id" , user.id);
            try {
                model.put("recommendations", recipeModel.getRecommendations(user));
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/index";
            }
        }

        model.put("content_bar_selection" , "profile");
        return "user-views/profile";
    }

    //##########################################
    //######## USER'S DAILY CONSUMPTION
    //##########################################


}
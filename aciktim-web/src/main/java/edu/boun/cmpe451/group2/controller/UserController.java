package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.client.Menu;
import edu.boun.cmpe451.group2.client.User;
import edu.boun.cmpe451.group2.model.RecipeModel;
import edu.boun.cmpe451.group2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit.http.POST;

import java.util.ArrayList;
import java.util.List;

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

        model.put("restaurants", userModel.getRestaurants());

        model.put("content_bar_selection" , "restaurants");
        return "user-views/restaurants";
    }


    /**
     * Views users profile
     * @param model
     * @param session_id
     * @return
     */
    @RequestMapping(value = {"user/view"})
    public String userview(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) { // not logged is is NOT OK
            model.put("full_name", "");
            return "redirect:/index";
        }else { // Now we're talking
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("user_id" , user.id);
            try {
                model.put("recommendations", recipeModel.getRecommendations(user));
                // TODO uncomment the following 3 commands when back-end methods are ready
                //model.put("likes" , userModel.getLikes(user));
                //model.put("likes" , userModel.getDislikes(user));
                //model.put("likes" , userModel.getAllergies(user));

                String[] likes = {"dessert" , "tomato", "gluten-free"};
                String[] dislikes = {"broccoli" , "cabbage"};
                String[] allergies = {"gluten" , "tomato"};

                model.put("likes" , tagify(likes));
                model.put("dislikes" , tagify(dislikes));
                model.put("allergies" , tagify(allergies));

            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/index";
            }
        }

        model.put("content_bar_selection" , "profile");
        return "user-views/profile";
    }

    /**
     * Saves (i.e. updates) the preference of the user
     * @param likes Liked tags as String[]
     * @param dislikes Disliked tags as String[]
     * @param allergies Allergy tags as String[]
     * @param session_id Session from cookies
     * @return Redirection to profile page
     */
    @RequestMapping(value = {"user/preferences/save"}, method = RequestMethod.POST)
    public String userpreferencessave(
            @RequestParam(required = true) String[] likes,
            @RequestParam(required = true) String[] dislikes,
            @RequestParam(required = true) String[] allergies,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            return "redirect:/index";
        }else{
            User user = userModel.getUser(session_id);
            // TODO here do somthing like following when back-end implements the method
            //model.updatePreferences(user.id, likes , dislikes, allergies);
            return "redirect:/user/view";
        }
    }

    private String tagify(String[] tags){
        String rv="";
        int count = 0;
        for(String tag : tags){
            if (count == 0 ) rv = tag;
            else rv += ","+tag;
            count++;
        }
        return rv;
    }

}


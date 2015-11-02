package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String index(ModelMap model) {


        return "home_index";
    }

    @RequestMapping(value = {"/signup"})
    public String signup(ModelMap model) {

        model.put("type", "NORMAL");

        return "sign-up";
    }

    @RequestMapping(value = {"/users"})
    public String viewUser(ModelMap model) {



        return "profile-view";
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
package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("request")
public class HomeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;

    @RequestMapping(value = {"/", "/index", "/recipes"})
    public String index(ModelMap model, @CookieValue(value="session_id", defaultValue = "-1") String session_id) {

        if (!session_id.equals("-1")) {
            UserModel user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
        }else{
            model.put("full_name", "");
        }
        model.put("bad_attempt", "false");
        model.put("content_bar_selection" , "recipes");
        return "user-views/recipes";
    }


}
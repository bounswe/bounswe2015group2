package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.model.UserModel;
import edu.boun.cmpe451.group2.model.RecipeModel;
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

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
@Scope("request")
public class HomeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model, @CookieValue(value="session_id", defaultValue = "-1") String session_id) {

        if (!session_id.equals("-1")) {
            UserModel user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
        }else{
            model.put("full_name", "");
        }
        model.put("bad_attempt" ,"false");

        return "user-views/home_index";
    }


}
package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.client.User;
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
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:recipes";
    }
}
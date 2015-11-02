package edu.boun.cmpe451.group2.controller;

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



        return "sign-up";
    }

}
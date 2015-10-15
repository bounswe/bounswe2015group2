package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.model.AciktimModel;
import edu.boun.cmpe451.group2.model.AciktimUsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@Scope("request")
public class AciktimController {

    /**
     * This is model object for business logic.
     * It injected to this class by @autowired annotation
     */
    @Autowired
    private AciktimModel aciktimModel = null;

    @Autowired
    private AciktimUsersModel aciktimUserModel = null;

    /**
     * Index Controller function.
     * when user connects to our site, then this function starts first.
     * <p>
     * request path : ../aciktim or .../aciktim/index
     *
     * @param model - object that stores data to be rendered by view
     * @return
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        // gets users from model.
        List<Map<String, Object>> users = aciktimUserModel.getUsers();

        // sends users List to view.
        model.put("users", users);

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return "index";
    }
}
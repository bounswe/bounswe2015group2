package edu.boun.cmpe451.group2.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.boun.cmpe451.group2.model.UserModel;

@Controller
@RequestMapping("admin")
@Scope("request")
public class AdminController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;

    /**
     * Index Controller function.
     * when user connects to our site, then this function starts first.
     * request path : ../aciktim/admin or .../aciktim/admin/index
     *
     * @param model - object that stores data to be rendered by view
     * @return view
     */
    @RequestMapping(value = {"", "/index"})
    public String index(ModelMap model) {

        // gets users from model.
        List<Map<String, Object>> users = userModel.getUserDao().getUsers();

        // sends users List to view.
        model.put("users", users);

        // Spring uses InternalResourceViewResolver and return back admin.jsp
        return "admin";
    }

    /**
     * Show page which edit a existing user
     * request path : ../aciktim/user/edit
     *
     * @param id    - user's id to show information
     * @param model - object that stores data to be rendered by view
     * @return view
     */
    @RequestMapping(value = "user/edit")
    public String editUser(@RequestParam(required = false) Long id, ModelMap model) {
        Map<String, Object> user = userModel.getUserDao().getUser(id);

        model.put("user", user);

        return "user_edit";
    }

    /**
     * Updates the user info or add new user. and then redirects to /index page
     * request path : ../aciktim/user/update
     *
     * @param id     - user's id. If this is null then add a new user. otherwise update the user.
     * @param email  - email address
     * @param passwd - password
     * @return view
     */
    @RequestMapping(value = "user/update")
    public String updateUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String passwd,
            @RequestParam(required = false) String full_name,
            @RequestParam(required = false) String username) {

        if (id != null)
            userModel.getUserDao().updateUser(id, email, passwd, full_name, username);
        else
            userModel.getUserDao().addUser(email, passwd);

        return "redirect:/admin/index";
    }

    /**
     * Show page which add a new user
     * request path : ../aciktim/user/new
     *
     * @return
     */
    @RequestMapping(value = "user/new")
    public String newUser() {
        return "user_edit";
    }

    /**
     * delete user from db and redirects to /index page
     * request path : ../aciktim/user/delete
     *
     * @param id
     * @return view
     */
    @RequestMapping(value = "user/delete")
    public String deleteUser(
            @RequestParam(required = false) Long id) {

        userModel.getUserDao().deleteUser(id);

        return "redirect:/admin/index";
    }
}
package com.sensesnet.controller;

import com.sensesnet.controller.exception.ResourceNotFoundException;
import com.sensesnet.model.User;
import com.sensesnet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.List;

import static org.hibernate.jpa.internal.QueryImpl.LOG;


/**
 * controller for user service
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String INDEX = "redirect:/index";

    private static final String USER_LIST = "redirect:/user/list";

    @Autowired
    private UserService userService;


    /**
     * - get user list
     *
     * @param model
     * @return list users by page
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList(ModelMap model)
    {
        List<User> users = userService.getAll();
        if (users.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("users", users);
        return "listUsers";
    }


    /**
     * - return form for add user
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showFormForAdd(@ModelAttribute User user)
    {
        return "addUser";
    }


    /**
     * - remove user
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam("userId") Integer userId)
    {
        userService.removeById(userId);
        return "redirect:/user/list";
    }


    /**
     * - create new user
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user, HttpSession session)
    {
        User userdb = userService.add(user);
        if (userdb != null)
        {
            session.setAttribute("user", user);
            return USER_LIST;
        }
        return INDEX;
    }


    /**
     * after login page
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public String login(HttpSession session, Principal principal)
    {
        String userLogin = principal.getName();
        User userdb = null;
        if (userLogin != null)
        {
            userdb = userService.getUserByLogin(userLogin);
        }
        session.setAttribute("user", userdb);
        log.info("user login, username:{}", userdb.getUserLogin());
        return USER_LIST;
    }


    /**
     * - go to update user page
     *
     * @param theId
     * @param theModel
     * @return
     */
    @RequestMapping(value = "/showFormForUpdate", method = RequestMethod.GET)
    public String showFormForUpdate(@RequestParam("userId") Integer theId,
                                    Model theModel)
    {
        User user = userService.getById(User.class, theId);
        theModel.addAttribute("user", user);
        return "updateForm";
    }
}

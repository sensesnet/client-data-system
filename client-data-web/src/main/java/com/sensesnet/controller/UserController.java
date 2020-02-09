package com.sensesnet.controller;

import com.sensesnet.controller.exception.ResourceNotFoundException;
import com.sensesnet.controller.view.UserView;
import com.sensesnet.model.User;
import com.sensesnet.service.UserRoleService;
import com.sensesnet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private UserRoleService userRoleService;


    /**
     * - get user list
     *
     * @param model
     * @return list users by page
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList(ModelMap model)
    {
//        List<User> users = userService.getAll();
//        if (users.isEmpty())
//        {
//            throw new ResourceNotFoundException();
//        }
//        model.addAttribute("userList", users);
//        return "userList";
        return "redirect:/user/list/1";
    }

    @Value("${app.limit.users}")
    private int maxUsersOnPage;

    /**
     * @param model
     * @param page
     * @return list users by page
     */
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String list(ModelMap model, @PathVariable Long page)
    {
        List<User> userList = userService.getAll();
        long realPage = (int) Math.ceil(userList.size() / maxUsersOnPage) + 1;
        if (page > realPage)
        {
            model.addAttribute("errorMessage", "Page is not found!");
            return "userList";
        }
        List<User> users = userService.getByPage(page);
        if (users.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("userList", users);
        model.addAttribute("links",
                           (long) Math.ceil(userService.getAll().size() / maxUsersOnPage) + 1);
        model.addAttribute("nextPage", ++page);

        return "userList";
    }

    /**
     * - get user list
     *
     * @param userDetail
     * @return list users by page
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model, User userDetail)
    {
        User user = userService.getUserByLogin(userDetail.getUserLogin());
        if (user != null
                & userDetail.getUserPassword().equals(user.getUserPassword()))
        {
            model.addAttribute("user", user);

            if (userRoleService.getRoleById(user.getUserRole()).equals("ADMIN"))
            {
                return "homeAdmin";
            }

            if (userRoleService.getRoleById(user.getUserRole()).equals("USER"))
            {
                return "homeUser";
            }
        }
        model.addAttribute("errorMessage", "User is not found!");
        return "signIn";
    }


    /**
     * - go to sign in page
     *
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String returnSignInForm()
    {
        return "signIn";
    }

    /**
     * - go to sign up page
     *
     * @return
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String returnSignUpForm()
    {
        return "signUp";
    }

    /**
     * - go to contacts page
     *
     * @return
     */
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String returnContactsForm()
    {
        return "contacts";
    }

    /**
     * - go to about page
     *
     * @return
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String returnAboutForm()
    {
        return "about";
    }

    /**
     * - return form for add user
     *
     * @return
     */
    @RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
    public String showFormForAdd(Model theModel)
    {
        theModel.addAttribute("user", new UserView());
        return "userAdd";
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
     * - go to home
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goToHomePage(ModelMap model, HttpSession session)
    {
        List<User> users = userService.getAll();
        User user = (User) session.getAttribute("user");
        if (users.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("userList", users);
        if (userRoleService.getRoleById(user.getUserRole()).equals("ADMIN"))
        {
            return "homeAdmin";
        }

        if (userRoleService.getRoleById(user.getUserRole()).equals("USER"))
        {
            return "homeUser";
        }

        return "logout";
    }


    /**
     * - create new user
     *
     * @param userView
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") UserView userView, HttpSession session)
    {
        User userdb = userService.getUserByLogin(userView.getUserLogin());
        if (userdb != null)
        {
            session.setAttribute("errorMessage", "User [" + userView.getUserLogin() + "] is found.");
            return USER_LIST;
        }
        if (!userView.getUserPassword().equals(userView.getConfirmPassword()))
        {
            session.setAttribute("errorMessage", "User password is not confirmed.");
            return USER_LIST;
        }
        userService.add(
                User.builder()
                        .userLogin(userView.getUserLogin())
                        .userAddress(userView.getUserAddress())
                        .userBirthday(userView.getUserAddress())
                        .userRole(userRoleService.getRoleByName(userView.getUserRole()).getRoleId())
                        .userName(userView.getUserName())
                        .userSurname(userView.getUserSurname())
                        .userPhone(userView.getUserPhone())
                        .userPassword(userView.getUserPassword())
                        .build());
        return USER_LIST;
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
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showFormForUpdate(@RequestParam("userId") Integer theId,
                                    Model theModel)
    {
        User user = userService.getById(User.class, theId);
        theModel.addAttribute("user", user);
        return "userEdit";
    }

    /**
     * - go to update user page
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String showFormForUpdate(@ModelAttribute("user") User user)
    {
        userService.edit(user);
        return USER_LIST;
    }
}

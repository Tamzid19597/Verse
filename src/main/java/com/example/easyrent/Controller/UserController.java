package com.example.easyrent.Controller;

import com.example.easyrent.Model.*;
import com.example.easyrent.Repository.*;
import com.example.easyrent.Service.HttpSessionService;
import com.example.easyrent.Service.NavigationService;
import com.example.easyrent.Service.ProfileService;
import com.example.easyrent.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

//=========Dependency Injection Area Start======//
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    MethodController methodController;
    @Autowired
    SubServiceRepository subServiceRepository;
    @Autowired
    SingleServiceRepository singleServiceRepository;
    @Autowired
    TemporaryHold temporaryHold;
    @Autowired
    DealsRepository dealsRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    NavigationService navigationService;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    @Autowired
    HttpSessionService httpSessionService;
//=========Dependency Injection Area End======//



//=========Registration Area Start======//
    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("user",new User());
        navigationService.navigationValues(model);
        return "registered";
    }

    @PostMapping("/register")
    public RedirectView postRegister(User user, Model model){
        userService.addUser(user);
        profileService.addProfile(user);
        return new RedirectView("/login");
    }
//=========Registration Area End======//


//=========Login Area Start======//
    @GetMapping("/login")
    public String getLogin(Model model){
        navigationService.navigationValues(model);
        model.addAttribute("login",new User());
        return "login";
    }
    @PostMapping("/login")
    public RedirectView postLogin(User user, Model model, HttpSession httpSession){
        TemporaryHold.email=user.email;
        if (userService.loginValidate(user)){
            profileService.setProfileUser(user);
            httpSessionService.storeSession(user,httpSession);
            return new RedirectView("/home");
        }
        return new RedirectView("/login");
    }
    @GetMapping("/logout")
    public RedirectView getLogin(Model model,HttpSession httpSession){
        httpSession.removeAttribute("userEmail");
        httpSession.invalidate();
        return new RedirectView("/login");
    }
//=========Login Area End======//



//=========Profile Area Start======//
    @GetMapping("/profile")
    public String profile(Model model,HttpSession httpSession){
        model.addAttribute("profile",profileService.getProfile(httpSessionService.getUserEmail(httpSession)));
        return "profile";
}
    @GetMapping("/my-profile")
    public RedirectView getProfile(Model model,HttpSession httpSession){
        if (httpSessionService.isValid(httpSession)){
            return new RedirectView("/profile");
        }
        else return new RedirectView("/login");
    }
    @PostMapping("/my-profile")
    public RedirectView postProfile(Profile profile,Model model,HttpSession httpSession){
        if (httpSessionService.isValid(httpSession)){
            return new RedirectView("/profile");
        }
        else return new RedirectView("/login");
    }

    @PostMapping("/my-profile/edit")
    public RedirectView postEdit(Profile profile,Model model,HttpSession httpSession){
        profileService.updateProfile(profile,httpSession);
        userService.updateUser(profile,httpSession);
        navigationService.navigationValues(model);
        return new RedirectView("/my-profile");
    }
//=========Profile Area End======//

}

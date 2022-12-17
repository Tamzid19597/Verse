package com.example.easyrent.Controller;

import com.example.easyrent.Model.*;
import com.example.easyrent.Repository.*;
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
            return new RedirectView("/home");
        }
        return new RedirectView("/login");
    }
//=========Login Area End======//



//=========Profile Area Start======//
    @GetMapping("/profile")
    public String getProfile(Model model){
        Profile profile=new Profile();
        model.addAttribute(profile);
        return "profile";
    }
    @PostMapping("/postProfile")
    public String postProfile(Profile profile,Model model){
        if (temporaryHold.email.equals("default")){
            navigationService.navigationValues(model);
            Login login=new Login();
            model.addAttribute(login);
            return "login";
        }
        else {
            navigationService.navigationValues(model);
            return "profile";
        }

    }

    @PostMapping("/postEdit")
    public String postEdit(Profile profile,Model model){
        int updatep=profileRepository.updateByemail(temporaryHold.email,profile.name,profile.primarynumber,profile.secondarynumber,profile.address,profile.city,profile.country,profile.postalcode,profile.about);

        int updateU=userRepository.updateByemail(temporaryHold.email,profile.primarynumber,profile.address);
        navigationService.navigationValues(model);
        return "profile";
    }
//=========Profile Area End======//

}

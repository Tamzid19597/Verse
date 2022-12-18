package com.example.easyrent.Controller;

import com.example.easyrent.Model.User;
import com.example.easyrent.Repository.ProfileRepository;
import com.example.easyrent.Service.HttpSessionService;
import com.example.easyrent.Service.ProfileService;
import com.example.easyrent.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class InstructorAuthController {
    @Autowired
    UserService userService;
    @Autowired
    HttpSessionService httpSessionService;
    @Autowired
    ProfileService profileService;
    @Autowired
    ProfileRepository profileRepository;
    @GetMapping("/instructor/login")
    public String getLogin(Model model){
        model.addAttribute("user",new User());
        return "instructorLogin";
    }
    @PostMapping("/instructor/login")
    public RedirectView postLogin(Model model, User user, HttpSession httpSession){
      if (userService.loginValidate(user) && userService.getUserRole(user).equals("instructor")){
          return new RedirectView("/instructor/profile");
      }
      return new RedirectView("/instructor/login");
    }
    @GetMapping("/instructor/profile")
    public String getProfile(Model model,HttpSession httpSession){
        if (httpSessionService.isValid(httpSession)){
            model.addAttribute("profile",profileService.getProfileById(profileRepository.findByEmail(httpSessionService.getUserEmail(httpSession)).get(0).id));
            return "instructorProfile";
        }
        else {
            model.addAttribute("user",new User());
            return "instructorLogin";
        }
    }
}

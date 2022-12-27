package com.example.easyrent.Controller;

import com.example.easyrent.Model.User;
import com.example.easyrent.Service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    NavigationService navigationService;
    private static String authorizationRequestBaseUri
            = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/oauth_login")
    public String getLoginPage(Model model) {
        // ...
        navigationService.navigationValues(model);
        model.addAttribute("login",new User());
        return "login";
    }
}

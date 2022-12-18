package com.example.easyrent.Controller;

import com.example.easyrent.Service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
    @Autowired
    NavigationService navigationService;

    @GetMapping("/faq")
    public String getFaq(Model model){
        navigationService.navigationValues(model);
        return "faq";
    }
}

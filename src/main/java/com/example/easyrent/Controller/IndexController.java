package com.example.easyrent.Controller;

import com.example.easyrent.Model.Profile;
import com.example.easyrent.Model.Services;
import com.example.easyrent.Repository.ServicesRepository;
import com.example.easyrent.Service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    NavigationService navigationService;
    @Autowired
    ServicesRepository servicesRepository;
    @GetMapping("/home")
    public String getHome(Model model){
        Profile profile=new Profile();
        List<Services> officialservicesList=new ArrayList<Services>();
        List <Services> sponsoredservicesList=new ArrayList<Services>();
        officialservicesList=servicesRepository.findByType("Authorized");
        sponsoredservicesList=servicesRepository.findByType("Unauthorized");
        model.addAttribute("officialservicesList",officialservicesList);
        model.addAttribute("sponsoredservicesList",sponsoredservicesList);
        navigationService.navigationValues(model);
        model.addAttribute(profile);
        return "home";
    }
}

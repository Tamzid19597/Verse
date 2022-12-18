package com.example.easyrent.Controller;

import com.example.easyrent.Model.Navigation;
import com.example.easyrent.Model.Profile;
import com.example.easyrent.Model.Services;
import com.example.easyrent.Model.SingleService;
import com.example.easyrent.Service.CourseService;
import com.example.easyrent.Service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    CourseService courseService;
    @Autowired
    NavigationService navigationService;

    @PostMapping("/search")
    public RedirectView postSearch(Profile profile, Model model, Navigation navigation){
        return new RedirectView("/search/"+navigation.flag);
    }
    @GetMapping("/search/{keyword}")
    public String searchResult(Model model, @PathVariable String keyword){
        navigationService.navigationValues(model);
        model.addAttribute("officialservicesList",courseService.findByType("Authorized"));
        model.addAttribute("sponsoredservicesList",courseService.findByType("Unauthorized"));
        model.addAttribute("singleServiceList",courseService.findBySub(keyword));
        return "single_services";
    }
}

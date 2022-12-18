package com.example.easyrent.Controller;

import com.example.easyrent.Model.*;
import com.example.easyrent.Repository.*;
import com.example.easyrent.Service.CourseService;
import com.example.easyrent.Service.DealService;
import com.example.easyrent.Service.HttpSessionService;
import com.example.easyrent.Service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

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
    CourseService courseService;
    @Autowired
    NavigationService navigationService;
    @Autowired
    HttpSessionService httpSessionService;
    @Autowired
    DealService dealService;

//=========Dependency Injection Area End======//


//=========Services Area Start======//
@PostMapping("/courses/{subCategories}")
public String postNavigation(Model model,@PathVariable String subCategories){
    navigationService.navigationValues(model);
    model.addAttribute("singleServiceList",courseService.findBySub(subCategories));
    return "single_services";
}
    @GetMapping("/courses")
    public String getServices(Model model){
        navigationService.navigationValues(model);
        model.addAttribute("officialservicesList",courseService.findByType("Authorized"));
        model.addAttribute("sponsoredservicesList",courseService.findByType("Unauthorized"));
        return "services";
    }
    @PostMapping("/courses/{categoryType}/{categoryName}")
    public String postServices(Model model, @PathVariable String categoryType,@PathVariable String categoryName){
        navigationService.navigationValues(model);
        model.addAttribute("subServiceList",courseService.findByTypeAndService(categoryType,categoryName));
        return "sub_services";

    }
    @PostMapping("/courses/{categoryType}/{categoryName}/{subcategoryName}")
    public String postSubService(Navigation navigation,Model model,@PathVariable String categoryType,@PathVariable String categoryName,@PathVariable String subcategoryName){
        navigationService.navigationValues(model);
        model.addAttribute("singleServiceList",courseService.findByTypeAndSubService(categoryType,subcategoryName));
        return "single_services";
    }
    @PostMapping("/courses/{categoryType}/{categoryName}/{subcategoryName}/{courseId}")
    public String postSingleService(Navigation navigation,Model model,@PathVariable String categoryType,@PathVariable String categoryName,@PathVariable String subcategoryName,@PathVariable String courseId){
        methodController.navigationValues(model);
        model.addAttribute("topicList",courseService.getAllTopic(Integer.parseInt(courseId)));
        model.addAttribute("singleservice",courseService.findCourseById(Integer.parseInt(courseId)));
        model.addAttribute("similarserviceList",courseService.getSimilarCourses(Integer.parseInt(courseId)));
        return "service_confirm";
    }
    @GetMapping("/enrolled-courses/{id}")
    public String getDeal(Model model,@PathVariable String id){
        model.addAttribute("cancel","cancel");
        model.addAttribute("change","change");
        model.addAttribute("dealsList",dealService.getAllDeals(userRepository.findById(Integer.parseInt(id)).get().getEmail()));
        navigationService.navigationValues(model);
        model.addAttribute("user",new User());
        return "deals";
    }
    @PostMapping("/courses/enroll/{courseId}")
    public RedirectView postServiceconfirm(Navigation navigation, Model model, HttpSession httpSession, @PathVariable String courseId){
        if (httpSessionService.isValid(httpSession)){
            dealService.addDeals(Integer.parseInt(courseId),httpSessionService.getUserEmail(httpSession));
            return new RedirectView("/enrolled-courses/"+userRepository.findByEmail(httpSessionService.getUserEmail(httpSession)).get().getId());
        }
        else {
            return new RedirectView("/login");
        }
    }
    @PostMapping("/enrolled-courses")
    public String postYourdeals(Model model,HttpSession httpSession){
        model.addAttribute("cancel","cancel");
        model.addAttribute("change","change");
        model.addAttribute("dealsList",dealService.getAllDeals(httpSessionService.getUserEmail(httpSession)));
        navigationService.navigationValues(model);
        model.addAttribute("user",new User());
        return "deals";
    }

    @PostMapping("/enrolled-courses/cancel/{courseId}")
    public String postCancelDeal(Model model,HttpSession httpSession,@PathVariable String courseId){
        dealService.deleteDeal(httpSessionService.getUserEmail(httpSession),courseId);
        model.addAttribute("cancel","cancel");
        model.addAttribute("change","change");
        model.addAttribute("dealsList",dealService.getAllDeals(httpSessionService.getUserEmail(httpSession)));
        methodController.navigationValues(model);
        model.addAttribute("user",new User());
        return "deals";
    }

    @PostMapping("/profile/enrolled-courses/{courseId}")
    public String postEnrollCourse(Model model,HttpSession httpSession,@PathVariable String courseId){
        if (!httpSessionService.isValid(httpSession)){
            navigationService.navigationValues(model);
            model.addAttribute("user",new User());
            return "login";
        }
        List<Topic>topicList=courseService.getAllTopic(Integer.parseInt(courseId));
        if (!dealService.isPaid(httpSessionService.getUserEmail(httpSession),courseId)){
            navigationService.navigationValues(model);
            model.addAttribute("topicList",courseService.restrictTopic(topicList));
            model.addAttribute("singleservice",dealService.getDeal(httpSessionService.getUserEmail(httpSession),courseId));
            model.addAttribute("user",new User());
            return "enrolled_courses";
        }
        else {
            model.addAttribute("topicList",topicList);
            model.addAttribute("singleservice",dealService.getDeal(httpSessionService.getUserEmail(httpSession),courseId));
            model.addAttribute("user",new User());
            navigationService.navigationValues(model);
            return "paid_courses";
        }

    }
    @GetMapping("/profile/enrolled-courses/{courseId}")
    public String getEnrollCourse(Model model,HttpSession httpSession,@PathVariable String courseId){
        if (!httpSessionService.isValid(httpSession)){
            navigationService.navigationValues(model);
            model.addAttribute("user",new User());
            return "login";
        }
        List<Topic>topicList=courseService.getAllTopic(Integer.parseInt(courseId));
        if (!dealService.isPaid(httpSessionService.getUserEmail(httpSession),courseId)){
            navigationService.navigationValues(model);
            model.addAttribute("topicList",courseService.restrictTopic(topicList));
            model.addAttribute("singleservice",dealService.getDeal(httpSessionService.getUserEmail(httpSession),courseId));
            model.addAttribute("user",new User());
            return "enrolled_courses";
        }
        else {
            model.addAttribute("topicList",topicList);
            model.addAttribute("singleservice",dealService.getDeal(httpSessionService.getUserEmail(httpSession),courseId));
            model.addAttribute("user",new User());
            navigationService.navigationValues(model);
            return "paid_courses";
        }

    }
//=========Services Area End======//
}

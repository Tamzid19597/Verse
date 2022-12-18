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
    @GetMapping("/deals")
    public String getDeal(Model model,HttpSession httpSession){
        String cancel="cancel";
        String change="change";
        model.addAttribute("cancel",cancel);
        model.addAttribute("change",change);
        model.addAttribute("dealsList",dealService.getAllDeals(httpSessionService.getUserEmail(httpSession)));
        navigationService.navigationValues(model);
        model.addAttribute("user",new User());
        return "deals";
    }
    @PostMapping("/courses/enroll/{courseId}")
    public RedirectView postServiceconfirm(Navigation navigation, Model model, HttpSession httpSession, @PathVariable String courseId){
        if (httpSessionService.isValid(httpSession)){
            dealService.addDeals(Integer.parseInt(courseId),httpSessionService.getUserEmail(httpSession));
            return new RedirectView("/enrolled-courses");
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
    public String postCanceldeal(Model model,HttpSession httpSession,@PathVariable String courseId){
        dealService.deleteDeal(httpSessionService.getUserEmail(httpSession),courseId);
        model.addAttribute("cancel","cancel");
        model.addAttribute("change","change");
        model.addAttribute("dealsList",dealService.getAllDeals(httpSessionService.getUserEmail(httpSession)));
        methodController.navigationValues(model);
        model.addAttribute("user",new User());
        return "deals";
    }

    @PostMapping("/enrolled-courses/{courseId}")
    public String postEnrollCourse(Model model,HttpSession httpSession,@PathVariable String courseId){
        List<Topic>topicList=courseService.getAllTopic(Integer.parseInt(courseId));
        if (!dealService.isPaid(httpSessionService.getUserEmail(httpSession),courseId)){
            methodController.navigationValues(model);
            model.addAttribute("topicList",courseService.restrictTopic(topicList));
            model.addAttribute("singleservice",singleServiceRepository.findById(Integer.parseInt(courseId)));
            model.addAttribute("user",new User());
            return "enrolled_courses";
        }
        else {
            model.addAttribute("topicList",topicList);
            model.addAttribute("singleservice",singleServiceRepository.findById(Integer.parseInt(courseId)));
            model.addAttribute("user",new User());
            return "paid_courses";
        }

    }
    @PostMapping("/payment")
    public String Payment(Model model,Navigation navigation){
        Payment payment=new Payment();
        methodController.navigationValues(model);
        model.addAttribute("sid",navigation.flag);
        model.addAttribute(payment);
        return "payment";
    }

    @PostMapping("/payment/confirm/{courseId}")
    public RedirectView postPayment(Model model,Payment payment,HttpSession httpSession,@PathVariable String courseId){
        dealService.updatePayment(httpSessionService.getUserEmail(httpSession),courseId,payment);
        return new RedirectView("/enrolled-courses/"+courseId);
    }
//=========Services Area End======//
}

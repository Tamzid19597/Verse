package com.example.easyrent.Controller;

import com.example.easyrent.Model.Navigation;
import com.example.easyrent.Model.Payment;
import com.example.easyrent.Repository.UserRepository;
import com.example.easyrent.Service.DealService;
import com.example.easyrent.Service.HttpSessionService;
import com.example.easyrent.Service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class PaymentController {
    @Autowired
    NavigationService navigationService;
    @Autowired
    DealService dealService;
    @Autowired
    HttpSessionService httpSessionService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/payment")
    public String Payment(Model model, Navigation navigation){
        Payment payment=new Payment();
        navigationService.navigationValues(model);
        model.addAttribute("sid",navigation.flag);
        model.addAttribute(payment);
        return "payment";
    }

    @PostMapping("/payment/confirm/{courseId}")
    public RedirectView postPayment(Model model, Payment payment, HttpSession httpSession, @PathVariable String courseId){
        dealService.updatePayment(httpSessionService.getUserEmail(httpSession),courseId,payment);
        return new RedirectView("/profile/enrolled-courses/"+courseId);
    }
}

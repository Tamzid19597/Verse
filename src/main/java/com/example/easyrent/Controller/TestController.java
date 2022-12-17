package com.example.easyrent.Controller;

import com.example.easyrent.Model.Login;
import com.example.easyrent.Model.Services;
import com.example.easyrent.Model.Test;
import com.example.easyrent.Repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
/*@Author:Tamzid Khan
  @####1959###752##02%9

  Query:
  INSERT INTO `subservices` (`id`, `type`, `service`, `subservice`, `img`) VALUES (NULL, 'Authorized', 'Programming Languages', 'Java', 'images/java.jpg');

  INSERT INTO `single_services` (`id`, `sid`, `type`, `service`, `subservice`, `name`, `description`, `cost`, `topics`, `detail`, `link`, `img`, `imgB`) VALUES (NULL, 'PL0001', 'Authorized', 'Programming Languages', 'Java', 'Java In-Depth: Become a Complete Java Engineer!', 'Comprehensive Java programming course integrated with design principles, best practices & instructor-led Java EE project', '11000tk.', 'Introduction~Why Java is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', 'Here we will discuss about course plan.~Explanation of the importance of Java Development.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.', 'https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo', 'images/javaAd.jpg', 'images/javaAd.jpg');

  UPDATE `single_services` SET `sid` = 'MD001', `topics` = 'Introduction~Why Android Development is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', `detail` = 'Here we will discuss about course plan.~Explanation of the importance of Android Development.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.' WHERE `single_services`.`id` = 7;

    INSERT INTO `single_services` (`id`, `sid`, `type`, `service`, `subservice`, `name`, `description`, `cost`, `topics`, `detail`, `link`, `img`, `imgB`) VALUES (NULL, 'PL0002', 'Authorized', 'Programming Languages', 'Java', 'Spring Boot Tutorial for Beginners (Java Framework)', 'Spring 5: Learn Spring 5 Core, AOP, Spring MVC, Spring Security, Spring REST, Spring Boot 2, Thymeleaf, JPA & Hibernate', '8999tk.', 'Introduction~Why Java Dpring framework is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', 'Here we will discuss about course plan.~Explanation of the importance of Java Development.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.', 'https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ', 'images/javaSp.png', 'images/javaSp.png');

 */


@Controller
public class TestController {
    @Autowired
    ServicesRepository servicesRepository;
    @GetMapping("/test")
    public String getTest(Model model){
        Test test=new Test();
        model.addAttribute(test);
        return "test";

}
@PostMapping("/postTest")
public String  postTest(Test test,Model model){
    System.out.println(test.t);
    Test test1=new Test();
    model.addAttribute("test",test1);
    return "test";
}
    @PostMapping("/postTest1")
    public String  postTest1(Test test,Model model){
            Login login=new Login();
            model.addAttribute(login);
            System.out.println(test.t);
            return "login";

    }


}



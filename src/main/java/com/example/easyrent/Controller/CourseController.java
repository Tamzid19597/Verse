package com.example.easyrent.Controller;

import com.example.easyrent.Model.*;
import com.example.easyrent.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

//=========Dependency Injection Area End======//


//=========Services Area Start======//
    @GetMapping("/services")
    public String getServices(Model model){
        List<Services> officialservicesList=new ArrayList<Services>();
        List <Services> sponsoredservicesList=new ArrayList<Services>();
        officialservicesList=servicesRepository.findByType("Authorized");
        sponsoredservicesList=servicesRepository.findByType("Unauthorized");
        methodController.navigationValues(model);
        model.addAttribute("officialservicesList",officialservicesList);
        model.addAttribute("sponsoredservicesList",sponsoredservicesList);
        return "services";
    }
    @PostMapping("/postServices")
    public String postServices(Navigation navigation, Model model){
        List<SubService>subServiceList=new ArrayList<SubService>();
//        System.out.println(navigation.flag+navigation.type);
        subServiceList=subServiceRepository.findByTypeNService(navigation.type,navigation.flag);
        methodController.navigationValues(model);
        model.addAttribute("subServiceList",subServiceList);
        return "sub_services";

    }
    @PostMapping("/postSubservice")
    public String postSubservice(Navigation navigation,Model model){
        List<SingleService>singleServiceList=new ArrayList<SingleService>();
        System.out.println(navigation.flag+navigation.type);
        singleServiceList=singleServiceRepository.findByTypeNSub(navigation.type,navigation.flag);
        methodController.navigationValues(model);
        model.addAttribute("singleServiceList",singleServiceList);
        return "single_services";
    }
    @PostMapping("/postSingleservice")
    public String postSingleservice(Navigation navigation,Model model){
        List<SingleService>singleServiceList=new ArrayList<SingleService>();
        List<SingleService>singleServicesimilar=new ArrayList<SingleService>();
        List<SingleService>similarservices=new ArrayList<SingleService>();
        List<Topic>topicList=new ArrayList<Topic>();

        System.out.println(navigation.flag+navigation.type);
        singleServiceList=singleServiceRepository.findBysid(navigation.flag);
        singleServicesimilar=singleServiceRepository.findBySub(singleServiceList.get(0).subservice);
        for (int k=0;k<singleServicesimilar.size();k++){
            if (k<4){
                similarservices.add(singleServicesimilar.get(k));
            }
            else {
                break;
            }
        }
        String [] topic=singleServiceList.get(0).getTopics().split("~");
        String [] detail=singleServiceList.get(0).getDetail().split("~");
        for (int i=0;i<topic.length;i++){
            Topic topic1=new Topic();
            topic1.topic=topic[i];
            topic1.detail=detail[i];
            topicList.add(topic1);
        }

        methodController.navigationValues(model);
        model.addAttribute("topicList",topicList);
        model.addAttribute("singleservice",singleServiceList.get(0));
        model.addAttribute("similarserviceList",similarservices);
        return "service_confirm";
    }
    @PostMapping("/postServiceconfirm")
    public String postServiceconfirm(Navigation navigation,Model model){
        temporaryHold.service=navigation.flag;
        if (temporaryHold.email.equals("default")){
            Login login=new Login();
            methodController.navigationValues(model);
            model.addAttribute(login);
            return "login";
        }
        else {
            User user=new User();
            List<User>userList=new ArrayList<User>();
            userList=userRepository.findByEmail(temporaryHold.email);
            List<SingleService>singleServices=new ArrayList<SingleService>();
            singleServices=singleServiceRepository.findBysid(navigation.flag);
            Deals deals=new Deals();
            deals.sid=navigation.flag;
            deals.email=userList.get(0).email;
            deals.name=userList.get(0).name;
            deals.number=userList.get(0).number;
            deals.address=userList.get(0).address;
            deals.status="Pending";
            deals.payinfo="notYet";
            dealsRepository.save(deals);
            List<Deals>dealsList=new ArrayList<Deals>();
            dealsList=dealsRepository.findByEmail(temporaryHold.email);
            List<Dummy>dummyList=new ArrayList<Dummy>();
            for (int i=0;i<dealsList.size();i++){
                Dummy dummy=new Dummy();
                singleServices=singleServiceRepository.findBysid(dealsList.get(i).sid);
                dummy.name=singleServices.get(0).name;
                dummy.description=singleServices.get(0).description;
                dummy.cost=singleServices.get(0).cost;
                dummy.imgB=singleServices.get(0).imgB;
                dummy.sid=dealsList.get(i).sid;
                dummy.status=dealsList.get(i).status;
                dummy.type=singleServices.get(0).type;
                dummy.address=dealsList.get(i).address;
                dummyList.add(dummy);
            }
            String cancel="cancel";
            String change="change";
            model.addAttribute("cancel",cancel);
            model.addAttribute("change",change);
            model.addAttribute("dealsList",dummyList);
            methodController.navigationValues(model);
            return "deals";
        }
    }
    @PostMapping("/postYourdeals")
    public String postYourdeals(Navigation navigation,Model model){
        List<SingleService>singleServices=new ArrayList<SingleService>();
        List<Deals>dealsList=new ArrayList<Deals>();
        dealsList=dealsRepository.findByEmail(temporaryHold.email);
        List<Dummy>dummyList=new ArrayList<Dummy>();
        for (int i=0;i<dealsList.size();i++){
            Dummy dummy=new Dummy();
            singleServices=singleServiceRepository.findBysid(dealsList.get(i).sid);
            dummy.name=singleServices.get(0).name;
            dummy.description=singleServices.get(0).description;
            dummy.cost=singleServices.get(0).cost;
            dummy.imgB=singleServices.get(0).imgB;
            dummy.sid=dealsList.get(i).sid;
            dummy.status=dealsList.get(i).status;
            dummy.type=singleServices.get(0).type;
            dummy.address=dealsList.get(i).address;
            dummyList.add(dummy);
        }
        String cancel="cancel";
        String change="change";
        model.addAttribute("cancel",cancel);
        model.addAttribute("change",change);
        model.addAttribute("dealsList",dummyList);
        methodController.navigationValues(model);
        return "deals";
    }

    @PostMapping("/postCanceldeal")
    public String postCanceldeal(Navigation navigation,Model model){
        int f = dealsRepository.deleteByEmailNsid(temporaryHold.email, navigation.flag);
        List<SingleService>singleServices=new ArrayList<SingleService>();
        List<Deals>dealsList=new ArrayList<Deals>();
        dealsList=dealsRepository.findByEmail(temporaryHold.email);
        List<Dummy>dummyList=new ArrayList<Dummy>();
        for (int i=0;i<dealsList.size();i++){
            Dummy dummy=new Dummy();
            singleServices=singleServiceRepository.findBysid(dealsList.get(i).sid);
            dummy.name=singleServices.get(0).name;
            dummy.description=singleServices.get(0).description;
            dummy.cost=singleServices.get(0).cost;
            dummy.imgB=singleServices.get(0).imgB;
            dummy.sid=dealsList.get(i).sid;
            dummy.status=dealsList.get(i).status;
            dummy.type=singleServices.get(0).type;
            dummy.address=dealsList.get(i).address;
            dummyList.add(dummy);
        }
        String cancel="cancel";
        String change="change";
        model.addAttribute("cancel",cancel);
        model.addAttribute("change",change);
        model.addAttribute("dealsList",dummyList);
        methodController.navigationValues(model);
        return "deals";
    }


    @PostMapping("/postInfo")
    public String postChangeaddress(Updateinfo updateinfo,Model model){
        int update=dealsRepository.setByAddressNnumber(temporaryHold.email,updateinfo.flag,updateinfo.number,updateinfo.address);
        List<SingleService> singleServices = new ArrayList<SingleService>();
        List<Deals> dealsList = new ArrayList<Deals>();
        dealsList = dealsRepository.findByEmail(temporaryHold.email);
        List<Dummy> dummyList = new ArrayList<Dummy>();
        for (int i = 0; i < dealsList.size(); i++) {
            Dummy dummy = new Dummy();
            singleServices = singleServiceRepository.findBysid(dealsList.get(i).sid);
            dummy.name = singleServices.get(0).name;
            dummy.description = singleServices.get(0).description;
            dummy.cost = singleServices.get(0).cost;
            dummy.imgB = singleServices.get(0).imgB;
            dummy.sid = dealsList.get(i).sid;
            dummy.status = dealsList.get(i).status;
            dummy.type = singleServices.get(0).type;
            dummy.address = dealsList.get(i).address;
            dummyList.add(dummy);
        }
        String cancel = "cancel";
        String change = "change";
        model.addAttribute("cancel", cancel);
        model.addAttribute("change", change);
        model.addAttribute("dealsList", dummyList);
        methodController.navigationValues(model);
        return "deals";
    }

    @PostMapping("/postEnrollCourse")
    public String postEnrollCourse(Navigation navigation,Model model){
        List<SingleService>singleServiceList=new ArrayList<SingleService>();
        List<SingleService>singleServicesimilar=new ArrayList<SingleService>();
        List<SingleService>similarservices=new ArrayList<SingleService>();
        List<Topic>topicList=new ArrayList<Topic>();

        List<SingleService>singleServices=new ArrayList<SingleService>();
        List<Deals>dealsList=new ArrayList<Deals>();
        dealsList=dealsRepository.findByEmail(temporaryHold.email);
        List<Dummy>dummyList=new ArrayList<Dummy>();
        for (int i=0;i<dealsList.size();i++){
            Dummy dummy=new Dummy();
            singleServices=singleServiceRepository.findBysid(dealsList.get(i).sid);
            dummy.name=singleServices.get(0).name;
            dummy.description=singleServices.get(0).description;
            dummy.cost=singleServices.get(0).cost;
            dummy.imgB=singleServices.get(0).imgB;
            dummy.sid=dealsList.get(i).sid;
            dummy.status=dealsList.get(i).status;
            dummy.type=singleServices.get(0).type;
            dummy.address=dealsList.get(i).address;
            dummyList.add(dummy);
        }


        System.out.println(navigation.flag+navigation.type);
        singleServiceList=singleServiceRepository.findBysid(navigation.flag);
        String [] topic=singleServiceList.get(0).getTopics().split("~");
        String [] detail=singleServiceList.get(0).getDetail().split("~");
        String [] link=singleServiceList.get(0).getLink().split("~");
        for (int i=0;i<topic.length;i++){
            Topic topic1=new Topic();
            topic1.topic=topic[i];
            topic1.detail=detail[i];
            topic1.link=link[i];
            topicList.add(topic1);
        }
        User user=new User();
        model.addAttribute(user);
        methodController.navigationValues(model);

        if (dummyList.get(0).status.equals("Pending")){
            for (int i=0;i<topicList.size();i++){
                if(i>2){
                    topicList.get(i).topic=topicList.get(i).topic+"(Please confirm payment to see the original video)";
                    topicList.get(i).link="https://www.youtube.com/embed/0o-br3BHeXk?rel=0&amp;controls=0&amp;modestbranding=1";
                }
            }
            model.addAttribute("topicList",topicList);
            model.addAttribute("singleservice",dummyList.get(0));
            return "enrolled_courses";
        }
        else {
            model.addAttribute("topicList",topicList);
            model.addAttribute("singleservice",dummyList.get(0));

            return "paid_courses";
        }

    }
    @PostMapping("Payment")
    public String Payment(Model model,Navigation navigation){
        Payment payment=new Payment();
        methodController.navigationValues(model);
        model.addAttribute("sid",navigation.flag);
        model.addAttribute(payment);
        return "payment";
    }

    @PostMapping("postPayment")
    public String postPayment(Model model,Payment payment,Navigation navigation){
        String information="Name: "+payment.name+" "+"Number: "+payment.number+" "+"CVV "+payment.cvv+" "+"Expiry "+payment.expiry;
        String status="paid";
        int noting=dealsRepository.updateBystatusAndPayment(status,information);

        List<SingleService>singleServiceList=new ArrayList<SingleService>();
        List<SingleService>singleServicesimilar=new ArrayList<SingleService>();
        List<SingleService>similarservices=new ArrayList<SingleService>();
        List<Topic>topicList=new ArrayList<Topic>();

        List<SingleService>singleServices=new ArrayList<SingleService>();
        List<Deals>dealsList=new ArrayList<Deals>();
        dealsList=dealsRepository.findByEmail(temporaryHold.email);
        List<Dummy>dummyList=new ArrayList<Dummy>();
        for (int i=0;i<dealsList.size();i++){
            Dummy dummy=new Dummy();
            singleServices=singleServiceRepository.findBysid(dealsList.get(i).sid);
            dummy.name=singleServices.get(0).name;
            dummy.description=singleServices.get(0).description;
            dummy.cost=singleServices.get(0).cost;
            dummy.imgB=singleServices.get(0).imgB;
            dummy.sid=dealsList.get(i).sid;
            dummy.status=dealsList.get(i).status;
            dummy.type=singleServices.get(0).type;
            dummy.address=dealsList.get(i).address;
            dummyList.add(dummy);
        }


        System.out.println(navigation.flag+navigation.type);
        singleServiceList=singleServiceRepository.findBysid(payment.sid);
        String [] topic=singleServiceList.get(0).getTopics().split("~");
        String [] detail=singleServiceList.get(0).getDetail().split("~");
        String [] link=singleServiceList.get(0).getLink().split("~");
        for (int i=0;i<topic.length;i++){
            Topic topic1=new Topic();
            topic1.topic=topic[i];
            topic1.detail=detail[i];
            topic1.link=link[i];
            topicList.add(topic1);
        }
        User user=new User();
        model.addAttribute(user);
        methodController.navigationValues(model);
        methodController.navigationValues(model);
        model.addAttribute("topicList",topicList);
        model.addAttribute("singleservice",dummyList.get(0));


        return "paid_courses";
    }
//=========Services Area End======//
}

package com.example.easyrent.Service;

import com.example.easyrent.Model.Services;
import com.example.easyrent.Model.SingleService;
import com.example.easyrent.Model.SubService;
import com.example.easyrent.Model.Topic;
import com.example.easyrent.Repository.ServicesRepository;
import com.example.easyrent.Repository.SingleServiceRepository;
import com.example.easyrent.Repository.SubServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CourseService {
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    SubServiceRepository subServiceRepository;
    @Autowired
    private SingleServiceRepository singleServiceRepository;

    public List<Services> findByType(String type){
        return servicesRepository.findByType(type);
    }
    public List<SingleService>findBySub(String subService){
        return singleServiceRepository.findBySub(subService);
    }
    public List<SubService> findByTypeAndService(String categoryType,String categoryName){
        return subServiceRepository.findByTypeNService(categoryType,categoryName);
    }
    public List<SingleService> findByTypeAndSubService(String subcategoryType, String subcategoryName){
        return singleServiceRepository.findByTypeNSub(subcategoryType,subcategoryName);
    }
    public SingleService findCourseById(int id){
        return singleServiceRepository.findById(id).get();
    }
    public List<SingleService> getSimilarCourses(int id){
        List<SingleService>similarServices=new ArrayList<>();
        List<SingleService>singleServiceSimilar=singleServiceRepository.findBySub(singleServiceRepository.findById(id).get().service);
        for (int k=0;k<singleServiceSimilar.size();k++){
            if (k<4){
                similarServices.add(singleServiceSimilar.get(k));
            }
            else {
                break;
            }
        }
        return similarServices;
    }
    public List<Topic> getAllTopic(int id){
        List<Topic>topicList=new ArrayList<>();
        String [] topic=singleServiceRepository.findById(id).get().getTopics().split("~");
        String [] detail=singleServiceRepository.findById(id).get().getDetail().split("~");
        String [] link=singleServiceRepository.findById(id).get().getLink().split("~");
        for (int i=0;i<topic.length;i++){
            Topic topic1=new Topic();
            topic1.topic=topic[i];
            topic1.detail=detail[i];
            topic1.link=link[i];
            topicList.add(topic1);
        }
        return topicList;
    }
    public List<Topic> restrictTopic(List<Topic> topicList){
        for (int i=0;i<topicList.size();i++){
            if(i>2){
                topicList.get(i).topic=topicList.get(i).topic+"(Please confirm payment to see the original video)";
                topicList.get(i).link="https://www.youtube.com/embed/0o-br3BHeXk?rel=0&amp;controls=0&amp;modestbranding=1";
            }
        }
        return topicList;
    }
    public List<SingleService> getAllCourse(){
        return singleServiceRepository.findAll();
    }
    public CourseService(SingleServiceRepository singleServiceRepository){
        this.singleServiceRepository=singleServiceRepository;
    }
}

package com.example.easyrent.Service;

import com.example.easyrent.Model.*;
import com.example.easyrent.Repository.DealsRepository;
import com.example.easyrent.Repository.SingleServiceRepository;
import com.example.easyrent.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DealsRepository dealsRepository;
    @Autowired
    SingleServiceRepository singleServiceRepository;
    public void addDeals(int id,String email){
        User user=userRepository.findByEmail(email).get();
        Deals deals=new Deals();
        deals.sid=Integer.toString(id);
        deals.email=user.email;
        deals.name=user.name;
        deals.number=user.number;
        deals.address=user.address;
        deals.status="Pending";
        deals.payinfo="notYet";
        dealsRepository.save(deals);
    }
    public List<Dummy> getAllDeals(String email){
        List<Deals>dealsList=new ArrayList<Deals>();
        dealsList=dealsRepository.findByEmail(email);
        List<Dummy>dummyList=new ArrayList<Dummy>();
        for (int i=0;i<dealsList.size();i++){
            Dummy dummy=new Dummy();
            SingleService singleServices=singleServiceRepository.findById(Integer.parseInt(dealsList.get(i).getSid())).get();
            dummy.name=singleServices.name;
            dummy.description=singleServices.description;
            dummy.cost=singleServices.cost;
            dummy.imgB=singleServices.imgB;
            dummy.sid=dealsList.get(i).sid;
            dummy.status=dealsList.get(i).status;
            dummy.type=singleServices.type;
            dummy.address=dealsList.get(i).address;
            dummyList.add(dummy);
        }
        return dummyList;
    }
    public Dummy getDeal(String email,String sid){
        Deals deals=dealsRepository.findByEmailNsid(email, sid).get(0);
        Dummy dummy=new Dummy();
        SingleService singleServices=singleServiceRepository.findById(Integer.parseInt(deals.getSid())).get();
        dummy.name=singleServices.name;
        dummy.description=singleServices.description;
        dummy.cost=singleServices.cost;
        dummy.imgB=singleServices.imgB;
        dummy.sid=deals.sid;
        dummy.status=deals.status;
        dummy.type=singleServices.type;
        dummy.address=deals.address;
        return dummy;
    }
    public void deleteDeal(String email,String sid){
        dealsRepository.deleteByEmailNsid(email,sid);
    }
    public boolean isPaid(String email,String sid){
        Deals deal=dealsRepository.findByEmailNsid(email,sid).get(0);
        if (deal.status.equals("Pending")){
            return false;
        }
        else return true;
    }
    public void updatePayment(String email, String sid, Payment payment){
        Deals deal=dealsRepository.findByEmailNsid(email,sid).get(0);
        deal.status="Paid";
        deal.payinfo="Name: "+payment.name+" "+"Number: "+payment.number+" "+"CVV "+payment.cvv+" "+"Expiry "+payment.expiry;;
        dealsRepository.save(deal);
    }
}

package com.example.easyrent.Service;

import com.example.easyrent.Model.Profile;
import com.example.easyrent.Model.User;
import com.example.easyrent.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    HttpSessionService httpSessionService;

    private User profileUser;
    public void addProfile(User user){
        Profile profile=new Profile();
        profile.name=user.name;
        profile.primarynumber=user.number;
        profile.email=user.email;
        profile.address=user.address;
        profileRepository.save(profile);
    }
    public User getProfileUser(){
        return this.profileUser;
    }
    public void setProfileUser(User user){
        this.profileUser=user;
    }
    public void updateProfile(Profile profile, HttpSession httpSession){
        profileRepository.updateByemail(httpSessionService.getUserEmail(httpSession),profile.name,profile.primarynumber,profile.secondarynumber,profile.address,profile.city,profile.country,profile.postalcode,profile.about);
    }
    public Profile getProfile(String email){
        return profileRepository.findByEmail(email).get(0);
    }
    public Profile getProfileById(int id){
        return profileRepository.findById(id).get();
    }
}

package com.example.easyrent.Service;

import com.example.easyrent.Model.Profile;
import com.example.easyrent.Model.User;
import com.example.easyrent.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

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
}

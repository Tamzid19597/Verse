package com.example.easyrent.Service;

import com.example.easyrent.Model.Profile;
import com.example.easyrent.Model.User;
import com.example.easyrent.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    HttpSessionService httpSessionService;

    public void addUser(User user){
        userRepository.save(user);
    }
    public boolean loginValidate(User user){
        User user1=userRepository.findByEmail(user.email).get();
        if (user1!=null){
            if (user1.password.equals(user.password)){
                return true;
            }
        }
        return false;
    }
    public void updateUser(Profile profile, HttpSession httpSession){
        userRepository.updateByemail(httpSessionService.getUserEmail(httpSession),profile.primarynumber,profile.address);
    }
    public String getUserRole(User user){
        return userRepository.findByEmail(user.email).get().role;
    }

    public UserService(UserRepository userRepository)
    {
        // this keyword refers to current instance
        this.userRepository = userRepository;
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}

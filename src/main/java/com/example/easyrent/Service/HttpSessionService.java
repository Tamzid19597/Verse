package com.example.easyrent.Service;

import com.example.easyrent.Model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class HttpSessionService {
    public void storeSession(User user, HttpSession httpSession){
        httpSession.setAttribute("userEmail",user.email);
    }
    public boolean isValid(HttpSession httpSession){
        if (httpSession.getAttribute("userEmail")!=null){
            return true;
        }
        return false;
    }
    public String getUserEmail(HttpSession httpSession){
        return (String) httpSession.getAttribute("userEmail");
    }
}

package com.example.easyrent.Service;

import com.example.easyrent.Model.Navigation;
import com.example.easyrent.Model.Profile;
import com.example.easyrent.Model.TemporaryHold;
import com.example.easyrent.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigationService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    TemporaryHold temporaryHold;
    @Autowired
    HttpSessionService httpSessionService;

    public void navigationValues(Model model){
        model.addAttribute("navigation",new Navigation());
        List<Profile> profileList=profileRepository.findByEmail(temporaryHold.email);
        profileList.add(new Profile());
        if (profileList.size()>1){
            model.addAttribute("profile",profileList.get(0));
            System.out.println(profileList.get(0).getName());
        }
        //-------Web Development-------//
        String JavaScripts="JavaScripts";
        model.addAttribute("JavaScripts",JavaScripts);
        String React="React";
        model.addAttribute("React",React);
        String CSS="CSS";
        model.addAttribute("CSS",CSS);
        String HTML5="HTML5";
        model.addAttribute("HTML5",HTML5);
        String PHP="PHP";
        model.addAttribute("PHP",PHP);
        String Django="Django";
        model.addAttribute("Django",Django);
        String Angular="Angular";
        model.addAttribute("Angular",Angular);
        //------Web Development end-----//

        //-------Data Science-------//
        String Python="Python";
        model.addAttribute("Python",Python);
        String MachineLearning="MachineLearning";
        model.addAttribute("MachineLearning",MachineLearning);
        String DeepLearning="DeepLearning";
        model.addAttribute("DeepLearning",DeepLearning);
        String AI="AI";
        model.addAttribute("AI",AI);
        String R="R";
        model.addAttribute("R",R);
        String NLP="NLP";
        model.addAttribute("NLP",NLP);
        //------Data Science end-----//

        //-------Mobile Development-------//
        String Flutter="Flutter";
        model.addAttribute("Flutter",Flutter);
        String ReactNative="ReactNative";
        model.addAttribute("ReactNative",ReactNative);
        String Android="Android";
        model.addAttribute("Android",Android);
        String IOS="IOS";
        model.addAttribute("IOS",IOS);
        String weldingmachine="Swift";
        model.addAttribute("Swift",weldingmachine);
        //------Mobile Development end-----//

        //-------Programming Languages-------//
        String Java="Java";
        model.addAttribute("Java",Java);
        String PythonPL="Python";
        model.addAttribute("Python",PythonPL);
        String C="C#";
        model.addAttribute("CSharp",C);
        String CPlus="C++";
        model.addAttribute("CPlus",CPlus);
        String JavaScript="JavaScript";
        model.addAttribute("JavaScript",JavaScript);
        //------Programming Languages end-----//

        //-------Graphic Design-------//
        String Photoshop="Photoshop";
        model.addAttribute("Photoshop",Photoshop);
        String AdobeLightroom="AdobeLightroom";
        model.addAttribute("AdobeLightroom",AdobeLightroom);
        String ColorGrading="ColorGrading";
        model.addAttribute("ColorGrading",ColorGrading);
        String DaVinciResolve="DaVinciResolve";
        model.addAttribute("DaVinciResolve",DaVinciResolve);
        String Animation="Animation";
        model.addAttribute("Animation",Animation);
        String Cinematography="Cinematography";
        model.addAttribute("Cinematography",Cinematography);
        //------Graphic Design end-----//

    }
}

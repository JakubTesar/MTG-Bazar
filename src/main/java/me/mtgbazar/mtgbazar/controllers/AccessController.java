package me.mtgbazar.mtgbazar.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import me.mtgbazar.mtgbazar.data.repositories.UserRepository;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.UserAccessDTO;
import me.mtgbazar.mtgbazar.models.service.access.AccessService;
import me.mtgbazar.mtgbazar.models.service.access.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/access")
public class AccessController {

    @Autowired
    AccessService accessService;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/register")
    public String renderRegisterForm(@ModelAttribute UserAccessDTO userDTO) throws IOException {
        return "access/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserAccessDTO userDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes
    ) throws IOException, DuplicateEmailException {
        if (result.hasErrors())
            return renderRegisterForm(userDTO);
       try {
           accessService.registerUser(userDTO);
           return "redirect:access/login";
       } catch (DuplicateEmailException uaeEx) {
          redirectAttributes.addFlashAttribute("message", "An account for that username/email already exists.");
         return renderRegisterForm(userDTO);
       }

    }

    @GetMapping("/login")
    public String renderLoginForm(@ModelAttribute UserAccessDTO userDTO) {return "/access/login";}

//    @PostMapping("/login")
//    public String login(@ModelAttribute("username") String username,
//                        @ModelAttribute("password") String password,
//                        HttpServletRequest req) throws ServletException {
//        req.login(username, password);
//        return "redirect:../cards";
//    }

    @PostMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:../cards";
    }


//    @GetMapping("/logout")
//    public String logout() {
//        return "redirect:../cards";
//    }
}

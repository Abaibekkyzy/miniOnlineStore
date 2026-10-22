package org.example.minionlinestore.controller;
import org.example.minionlinestore.dto.UserDTO;
import org.example.minionlinestore.model.User;
import org.example.minionlinestore.service.ProductService;
import org.example.minionlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    private ProductService productService;

    @GetMapping(value="/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value="/sign-in-page")
    public String signinPage() { return "signin"; }

    @GetMapping(value="/sign-up-page")
    public String signupPage() { return "signup"; }

    @GetMapping(value="/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage() { return "profile"; }

    @GetMapping(value="/403-page")
    public String accessDeniedpage(){ return "403"; }

    @GetMapping(value="/update-password-page")
    @PreAuthorize("isAuthenticated()")
    public String updatePasswordPage(){
        return "update-password";
    }

    @PostMapping(value="/to-sign-up")
    public String toSignUp(@RequestParam(name="user_email") String email,
                           @RequestParam(name="user_password") String password,
                           @RequestParam(name="user_repeat_password") String repeatPassword,
                           @RequestParam(name="user_full_name") String fullName){

        if(password.equals(repeatPassword)){
             UserDTO user = new UserDTO();
             user.setEmail(email);
             user.setPassword(password);
             user.setFullName(fullName);
             UserDTO newUser = userService.addUser(user);
            System.out.println(user.getFullName());
            if(newUser!=null){
                return "redirect:/sign-up-page?success";
            }else{
                return "redirect:/sign-up-page?emailerror";
            }
        }else{
            return "redirect:/sign-up-page?passworderror";
        }

    }

    @GetMapping(value = "/product-details")
    public String productDetails() {
        return "product-details";
    }

    @PostMapping(value="/to-update-password")
    public String toUpdatePassword(@RequestParam(name="user_old_password") String oldPassword,
                                   @RequestParam(name="user_new_password") String newPassword,
                                   @RequestParam(name="user_repeat_new_password") String repeatNewPassword){

        if(newPassword.equals(repeatNewPassword)){

            User user = userService.updatePassword(newPassword, oldPassword);
            if(user!=null){
                return "redirect:/update-password-page?success";
            }else{
                return "redirect:/update-password-page?oldpassworderror";
            }

        }else{
            return "redirect:/update-password-page?passwordismismatch";
        }

    }

}

package com.security.jwtpractice;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    private String test(){
        return "It is Test";
    }



    @Secured({"ROLE_USER"})
    @GetMapping("/user")
    public SecurityMessage user(){
        return SecurityMessage.builder()
                .message("user page")
                .auth(SecurityContextHolder.getContext().getAuthentication()).build();

    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public SecurityMessage admin(){
        return SecurityMessage.builder()
                .message("admin page")
                .auth(SecurityContextHolder.getContext().getAuthentication()).build();

    }
}

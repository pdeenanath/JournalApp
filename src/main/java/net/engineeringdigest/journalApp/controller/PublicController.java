//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.UserEntity;
//import net.engineeringdigest.journalApp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/public")
//public class PublicController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/create-user")
//    public void createUser(@RequestBody UserEntity user){
//        userService.saveEntry(user);
//    }
//}

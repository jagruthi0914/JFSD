package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SpringController {
  @Autowired
  DAO dao;
  
  //http://localhost:8080/welcome?name=jagruthi
  @GetMapping("/welcome")
  public String fun1(@RequestParam("name") String name) {
    return "Welcome " + name;
  }
  
  @PostMapping("/user")
  public String fun2(@RequestBody User user) {
    dao.insert(user);
    return "server response" + user;
  }
  
  @GetMapping("/all")
  public List<User> fun3() {
    return dao.retrieveAll();
  }
  
  //http://localhost:8080/email?email=xyz1@gmail.com
  @GetMapping("/email")
  public User fun4(@RequestParam("email") String emial) {
    return dao.findUser(emial);
  }
  
  @DeleteMapping("/delete")
  public String fun5(@RequestParam("email") String email) {
    return dao.deleteUser(email);
  }
  
  @PutMapping("/update")
  public String fun6(@RequestBody User user) {
    return dao.updateUser(user);
  }
  
  @GetMapping("/page")
  public List<User> fun7(@RequestParam("p") int page,@RequestParam("l") int limit ){
    System.out.println("controller");
    return dao.page(page, limit);
  }
  
  @PostMapping("/login")
  public User fun8(@RequestBody User user) {
    User user2 = dao.findUser(user.getEmail());
    if(user2 != null) {
      if(user2.getPassword().equals(user.getPassword())) {
        return user2;
      }
      else {
        return user;
      }
    }
    return user;
  }
  
}
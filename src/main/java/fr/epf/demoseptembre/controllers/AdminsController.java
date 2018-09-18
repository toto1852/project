package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.Admin;
import fr.epf.demoseptembre.models.User;
import fr.epf.demoseptembre.persistence.AdminDao;
import fr.epf.demoseptembre.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Controller
public class AdminsController {

  private final AdminDao adminDao;

  public AdminsController(AdminDao adminDao) {
    this.adminDao = adminDao;
  }

/*
  //Afficher la liste des membres (page non utilisée)
  @GetMapping("/users-list")
  public  String getUsers (Model model) {
    model.addAttribute("data", userDao.findAll());
    return "users-list";
  }

  //Afficher le formulaire de re d'un membre
  @GetMapping("/user")
  public String addUsersPage (Model model) {
    model.addAttribute("user", new User());
    return "user";
  }


  //sauvegarde le formulaire register d'un membre
  @PostMapping("user")
  public String addUsers (Model model, User usr){
    userDao.save(usr);
    return "redirect:/";
  }
  */

  // afficher la page de login
  @GetMapping("/login")
  public String loginPage (Model model) {
    model.addAttribute("admin", new Admin());
    return "login";
  }


  //apres avoir appuyé sur le bouton ok --> login
  @PostMapping("login")
  public String login (Model model, Admin adm){
    if (isUserConnected(adm)){
      return "redirect:/"; //success
    }
    else{
      return "error-login";
    }
  }

  private Boolean isUserConnected (Admin adm){
    for (Admin a : adminDao.findAll()){
      if (adm!= null && adm.getUserName()!= null && adm.getUserName().equals(a.getUserName())){
        if (adm!= null && adm.getPassWord()!= null && adm.getPassWord().equals(a.getPassWord())){
          adm.setId(a.getId());
          return true;
        }
      }
    }
    return false;
  }

  // afficher la page de register
  @GetMapping("/register")
  public String registerPage (Model model) {
    model.addAttribute("admin", new Admin());
    return "register";
  }


  //apres avoir appuyé sur le bouton ok --> register (creer un nouvel admin)
  @PostMapping("register")
  public String register (Model model, Admin adm){
    adminDao.save(adm);
    return "redirect:/";
  }








  
}

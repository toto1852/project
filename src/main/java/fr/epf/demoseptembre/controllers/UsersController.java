package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.Promotion;
import fr.epf.demoseptembre.models.User;
import fr.epf.demoseptembre.persistence.PromotionDao;
import fr.epf.demoseptembre.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Controller
public class UsersController {

  private final UserDao userDao;
  private final PromotionDao promotionDao;

  UsersController(UserDao userDao, PromotionDao promotionDao) {
    this.userDao = userDao;
    this.promotionDao = promotionDao;
  }



  //Afficher la liste des membres (page non utilisée)
  @GetMapping("/users-list")
  public  String getUsers (Model model) {
    model.addAttribute("data", userDao.findAll());
    return "users-list";
  }

  //Afficher le formulaire d'ajout d'un membre
  @GetMapping("/user")
  public String addUsersPage (Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("data3", promotionDao.findAll());
    return "user";
  }

  // afficher le formulaire de modification d'un membre
  @GetMapping("/user/modify/{id}")
  public String modifyUserForm(@PathVariable(value="id") int id, Model model) {
    model.addAttribute("user", userDao.findById(id));
    userDao.deleteById(id);
    return "user-modify";
  }

  // afficher le formulaire de suppression d'un membre
  @GetMapping("/user/delete/{id}")
  public String deleteUserForm(@PathVariable(value="id") int id, Model model) {
    model.addAttribute("user", userDao.findById(id));
    userDao.deleteById(id);
    return "redirect:/";
  }

  //sauvegarde le formulaire de création d'un membre
  @PostMapping("user")
  public String addUsers (Model model, User usr){
    userDao.save(usr);
    return "redirect:/";
  }



  }

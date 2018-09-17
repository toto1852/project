package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.User;
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
  
  public UsersController(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Ceci sera mappé sur l'URL '/users'.
   *    * C'est le routeur de Spring MVC qui va détecter et appeler directement cette méthode.
   *    * Il lui fournira un "modèle", auquel on pourra rajouter des attributs.
   *    * Ce modèle sera ensuite forwardé à une page web (dans resources/templates).
   *    * Le nom de la template est retourné par la fonction. Ici, elle appelle donc le template "users".
   *    * @param model le modèle
   * @return
   */

  //Afficher l'index et la liste des membres
  /*@GetMapping("/")
  public String getIndex(Model model) {
    model.addAttribute("data", userDao.findAll());
    return "index";
  }*/

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

package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.User;
import fr.epf.demoseptembre.models.Event;
import fr.epf.demoseptembre.models.Promotion;
import fr.epf.demoseptembre.persistence.EventDao;
import fr.epf.demoseptembre.persistence.UserDao;
import fr.epf.demoseptembre.persistence.PromotionDao;
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
public class GlobalController {

  private final UserDao userDao;
  private final EventDao eventDao;
  private final PromotionDao promotionDao;

  public GlobalController(UserDao userDao, EventDao eventDao, PromotionDao promotionDao) {
    this.userDao = userDao;
    this.eventDao = eventDao;
    this.promotionDao = promotionDao;
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
  @GetMapping("/")
  public String getIndex(Model model) {
    model.addAttribute("data1", userDao.findAll());
    model.addAttribute("data2", eventDao.findAll());
    model.addAttribute("data3", promotionDao.findAll());
    //model.addAttribute("total1", userDao);
    return "index";
  }

  
}

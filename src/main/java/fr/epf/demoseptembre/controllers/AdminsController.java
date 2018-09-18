package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.Admin;
import fr.epf.demoseptembre.models.Promotion;
import fr.epf.demoseptembre.models.User;
import fr.epf.demoseptembre.persistence.AdminDao;
import fr.epf.demoseptembre.persistence.EventDao;
import fr.epf.demoseptembre.persistence.PromotionDao;
import fr.epf.demoseptembre.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Controller
public class AdminsController {

    private final AdminDao adminDao;
    private final UserDao userDao;
    private final EventDao eventDao;
    private final PromotionDao promotionDao;

    public AdminsController(AdminDao adminDao, UserDao userDao, EventDao eventDao, PromotionDao promotionDao) {
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.promotionDao = promotionDao;
    }

    //Afficher la page d'accueil
    @GetMapping("/homepage")
    public String getHomepage(Model model) {
        model.addAttribute("data1", userDao.findAll());
        model.addAttribute("data2", eventDao.findAll());
        List<Promotion> list = new ArrayList<>();
        if (promotionDao.findAll() != null)
            promotionDao.findAll().forEach(list::add);

        model.addAttribute("data3", list);
        return "homepage";
    }

    // afficher la page de login
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }


    //apres avoir appuyé sur le bouton ok --> login
    @PostMapping("login")
    public RedirectView login(Model model, Admin adm) {
        if (isUserConnected(adm)) {
            return new RedirectView("homepage");
            //return getHomepage(model); //success
        } else {
            return new RedirectView("error-login");
        }
    }

    private Boolean isUserConnected(Admin adm) {
        for (Admin a : adminDao.findAll()) {
            if (adm != null && adm.getUserName() != null && adm.getUserName().equals(a.getUserName())) {
                if (adm != null && adm.getPassWord() != null && adm.getPassWord().equals(a.getPassWord())) {
                    adm.setId(a.getId());
                    return true;
                }
            }
        }
        return false;
    }

    // afficher la page de error pour se connecter
    @GetMapping("/error-login")
    public String errorLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "error-login";
    }

    // afficher la page de register
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "register";
    }


    //apres avoir appuyé sur le bouton ok --> register (creer un nouvel admin)
    @PostMapping("register")
    public String register(Model model, Admin adm) {
        adminDao.save(adm);
        return "homepage";
    }


}

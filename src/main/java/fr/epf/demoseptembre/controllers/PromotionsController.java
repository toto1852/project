package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.Promotion;
import fr.epf.demoseptembre.persistence.PromotionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PromotionsController {

    private final PromotionDao promotionDao;

    public PromotionsController(PromotionDao promotionDao) {
        this.promotionDao = promotionDao;
    }


    @GetMapping("/promotion")
    public String addPomotionPage (Model model) {
        Promotion promo = new Promotion();
        model.addAttribute(promo);
        return "promotion";
    }

    @PostMapping("promotion")
    public String addPromotion (Model model, Promotion promo){
        promotionDao.save(promo);
        return "redirect:/";
    }
}

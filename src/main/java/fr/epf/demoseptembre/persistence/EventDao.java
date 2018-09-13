package fr.epf.demoseptembre.persistence;

import fr.epf.demoseptembre.models.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Repository
public interface PromotionDao extends CrudRepository<Promotion, Integer> {
  
}

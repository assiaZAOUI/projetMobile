package ma.ac.fsts.rsi.tp_spring_boot.Repositories;

import ma.ac.fsts.rsi.tp_spring_boot.Entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}

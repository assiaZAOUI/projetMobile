package ma.ac.fsts.rsi.tp_spring_boot.Services;

import ma.ac.fsts.rsi.tp_spring_boot.Entities.Produit;
import ma.ac.fsts.rsi.tp_spring_boot.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService{

    @Autowired
    ProduitRepository repositoryProduit ;

    public List<Produit> getProduits(){
        return repositoryProduit.findAll();
    }
    public Optional<Produit> getProduitById(Long id){
        return repositoryProduit.findById(id);
    }

    public Produit addProduit(Produit p){
        return repositoryProduit.save(p);
    }

    public boolean deleteProduitByID(Long id){
        repositoryProduit.deleteById(id);
        return true;
    }

    public Produit updateProduit(Long id, String description,double prix, int quantity){
        Produit p= getProduitById(id).get();
        p.setDescription(description);
        p.setPrix(prix);
        p.setQuantity(quantity);
        addProduit(p);
        return p;
    }


}

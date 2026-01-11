package ma.ac.fsts.rsi.tp_spring_boot.Controllers;


import ma.ac.fsts.rsi.tp_spring_boot.Entities.Produit;
import ma.ac.fsts.rsi.tp_spring_boot.Repositories.ProduitRepository;
import ma.ac.fsts.rsi.tp_spring_boot.Services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @GetMapping("/produits")
    //@ResponseBody
    public String getAllProduits(Model model) {
        List<Produit> produits = produitService.getProduits();
        model.addAttribute("produits", produits);
        return "produits";
    }

    @PostMapping("/produits")
    @ResponseBody
    public Produit addProduit(@RequestBody Produit produit) {
        return produitService.addProduit(produit);
    }

    @GetMapping("/produits/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        produitService.deleteProduitByID(id);
        return "redirect:/produits";
    }

    @GetMapping("/produits/edit/{id}")
    public String editProduit(@PathVariable Long id, Model model) {
        Produit produit = produitService.getProduitById(id).get();
        model.addAttribute("produit", produit);
        return "editProduit";
    }

    @GetMapping("/produits/addNewProduct")
    public String editProduit(Model model) {
        model.addAttribute("produit", new Produit());
        return "editProduit";
    }

    @PostMapping("/produits/add")
    public String addProduit(
                              @RequestParam String description,
                              @RequestParam double prix,
                              @RequestParam int quantity) {

        Produit p = new Produit(description,quantity,prix);

        produitService.addProduit(p);
        return "redirect:/produits";
    }


    @PostMapping("/produits/update/{id}")
    public String updateProduit(
            @PathVariable Long id,
            @RequestParam String description,
            @RequestParam double prix,
            @RequestParam int quantity) {

        produitService.updateProduit(id, description, prix, quantity);
        return "redirect:/produits";
    }

}

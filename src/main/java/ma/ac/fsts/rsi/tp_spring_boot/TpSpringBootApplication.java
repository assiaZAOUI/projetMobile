package ma.ac.fsts.rsi.tp_spring_boot;

import ma.ac.fsts.rsi.tp_spring_boot.Entities.Produit;
import ma.ac.fsts.rsi.tp_spring_boot.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpSpringBootApplication implements CommandLineRunner {


    @Autowired
    ProduitRepository produitRepository;
    public static void main(String[] args) {
        SpringApplication.run(TpSpringBootApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

        Produit p1 = new Produit("Pc", 2, 5000);
        Produit p2 = new Produit("Imprimante", 5, 830);
        Produit p3 = new Produit("Souris", 25, 20);
        produitRepository.save(p1);
        produitRepository.save(p2);
        produitRepository.save(p3);

        produitRepository.findAll().stream().forEach(System.out::println);
    }
}
